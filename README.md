# [PRD] 선착순 할인 쿠폰 발급 시스템

## 1. 프로젝트 개요 (Overview)
* **목적:** 특정 이벤트 기간 동안 한정된 수량의 할인 쿠폰을 사용자에게 발급한다.
* **배경:** 트래픽이 폭증하는 '오픈 런' 상황에서 시스템 다운 없이 안정적으로 트래픽을 처리하고 정확한 수량의 쿠폰을 발급해야 한다.
* **핵심 과제:** 다중 서버 환경에서의 동시성 제어(Race Condition 방어) 및 대기열(Queue) 또는 비동기 처리를 통한 DB 부하 분산.

---

## 2. 보편적인 언어 (Ubiquitous Language)
> 도메인 전문가와 개발자가 동일하게 사용할 핵심 용어

* **Coupon (쿠폰):** 할인 정책과 유효 기간을 가진 마스터 데이터이며, **Aggregate Root** 역할을 수행한다.
* **Inventory (재고 - Value Object):** 전체 수량과 사용 수량을 관리하며, 스스로의 상태를 검증하는 불변 객체.
* **Issue (발급):** 사용자가 쿠폰을 획득하는 행위.
* **IssuedCoupon (발급 이력 - Entity):** 특정 사용자에게 쿠폰이 할당된 이력. 사용 여부(Status)를 관리한다.
* **Member (회원):** 쿠폰 발급을 요청하는 주체.

---

## 3. 핵심 도메인 모델 설계

### 3.1 Coupon (Aggregate Root)
* **책임:** 비즈니스 규칙의 진입점. 유효 기간 및 재고 차감 명령을 내린다.
* **주요 상태:** `id`, `description`, `inventory(VO)`, `expireDate`.

### 3.2 Inventory (Value Object)
* **책임:** 수량 계산의 원자성 및 불변성 보장.
* **특징:** 모든 변경 연산(`use`, `add`, `remove`)은 기존 필드를 수정하지 않고 새로운 `Inventory` 객체를 생성하여 반환한다.
* **주요 필드:**
    * `totalCount`: 기획된 전체 발행 가능 수량
    * `usedCount`: 현재까지 발급 완료된 수량
* **주요 로직:** * `getRemainCount()`: (total - used) 잔여 수량 계산
    * `hasAvailableStock()`: 잔여 수량 존재 여부 확인
    * `isStarted()`: 발급이 한 건이라도 발생했는지 확인 (수정 방어용)

### 3.3 IssuedCoupon (Entity)
* **책임:** 쿠폰의 소유권 증명 및 사용 상태 관리.
* **주요 필드:**
    * `id`: 고유 식별자
    * `couponId`: 연관된 쿠폰 ID
    * `memberId`: 소유한 회원 ID
    * `status`: 사용 상태 (UNUSED, USED, EXPIRED)
    * `issuedAt`: 발급 시각

---

## 4. 기능 요구사항 (Functional Requirements)
* **FR-1. 쿠폰 조회:** 사용자는 발급 가능한 쿠폰 정보와 현재 남은 수량을 확인할 수 있다.
* **FR-2. 쿠폰 발급 요청:** 사용자는 특정 쿠폰에 대해 발급을 요청할 수 있다.
* **FR-3. 재고 감소:** 쿠폰이 성공적으로 발급되면 잔여 수량은 1 감소한다.
* **FR-4. 중복 발급 방지:** 1명의 사용자는 동일한 쿠폰을 1매만 발급받을 수 있다.
* **FR-5. 발급 종료:** 재고가 0이 되거나 이벤트 기간이 종료되면 발급이 불가능하다.

---

## 5. 비기능 요구사항 (Non-Functional Requirements)

### NFR-1. 정확성 (데이터 정합성 보장)
* **초과 발급 방지:** 1,000개 발행 시, 10,000명이 동시 요청해도 정확히 1,000개만 발급되어야 한다.
* **멱등성 보장:** 동일 사용자의 '따닥' 요청(중복 클릭) 시 1번만 발급되어야 한다.

### NFR-2. 성능 (Throughput & Latency)
* **목표 처리량:** V1(RDB Lock) 1,000 TPS -> V2(Redis) 5,000 TPS 이상.
* **응답 속도:** 평균 200ms 이내 응답.

### NFR-3. 가용성 (High Availability)
* **장애 격리:** 대량 트래픽으로 인한 DB 커넥션 풀 고갈 및 전체 시스템 장애 방지.

---

## 6. 단계별 개발 및 학습 마일스톤

* **Phase 1: 도메인 모델링 및 TDD (현재 진행 중)**
    * 프레임워크 의존성 없는 순수 Java 객체(Entity, VO) 설계.
    * `Inventory`의 불변 연산 로직 Unit Test 검증.
* **Phase 2: RDBMS 비관적/낙관적 락 적용**
    * DB 락을 이용한 동시성 제어 및 JMeter 성능 측정.
* **Phase 3: Redis 기반 분산 락 및 대기열 적용**
    * Redis 도입을 통한 DB 부하 분산 및 대용량 트래픽 최적화.
 
## 7. 인프라 및 예외/장애 대응 설계 (Architecture & Resilience)

### 7.1 비동기 이벤트 기반 아키텍처
* **Decoupling:** RDBMS의 I/O 병목을 방지하기 위해 '재고 차감'과 '이력 저장'을 분리한다.
* **흐름:** Redis를 통해 원자적으로 재고 차감 및 중복 발급 검증을 수행한 후, 성공한 요청에 한해 `CouponIssuedEvent`를 Message Queue(예: AWS SQS)로 발행하여 워커(Worker) 노드가 비동기로 RDB에 Insert 하도록 설계한다.

### 7.2 어뷰징 방지 및 인프라 제어
* **Rate Limiting:** 오픈 런 상황에서의 매크로 및 악의적 공격을 방어하기 위해 API Gateway 계층에서 초당 요청 수(Rate Limit)를 제어한다.

### 7.3 정합성 보정 (Reconciliation)
* **보상 트랜잭션:** Redis 재고는 차감되었으나 RDB 저장이 실패한(Event Lost) 경우를 대비하여, 실패한 메시지는 DLQ(Dead Letter Queue)로 전송하여 재처리 로직을 구성한다.
* 주기적인 배치 워커를 통해 Redis의 발급 카운트와 RDB의 실제 `IssuedCoupon` 로우 수를 대조하여 정합성을 모니터링한다.

### 7.4 관측 가능성 (Observability)
* APM 및 모니터링 툴(CloudWatch, Grafana 등)을 연동하여 JMeter 부하 테스트 시 Active Thread, TPS, Latency, DB 커넥션 풀 상태를 시각화하고 병목 구간을 역추적한다.
