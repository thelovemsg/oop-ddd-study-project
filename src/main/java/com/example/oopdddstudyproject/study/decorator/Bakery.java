package com.example.oopdddstudyproject.study.decorator;

public class Bakery {
    public static void main(String[] args) {
        // 1. 가장 기본적인 빵만 주문
        Cake myCake = new PlainCake();
        System.out.println("주문 1: " + myCake.makeCake());

        System.out.println("\n--- 토핑 추가 시작 ---\n");

        // 2. 기본 빵에 '생크림' 바르기
        // PlainCake을 CreamDecorator로 감싼다.
        myCake = new CreamDecorator(new PlainCake());
        System.out.println("주문 2: " + myCake.makeCake());

        // 3. 생크림 케이크 위에 '과일' 올리기
        // (기본빵+생크림) 상태인 객체를 다시 FruitDecorator로 감싼다.
        myCake = new FruitDecorator(myCake);
        System.out.println("주문 3: " + myCake.makeCake());

        // 4. 마지막으로 전체에 '초콜릿' 코팅하기
        // (기본빵+생크림+과일) 상태인 객체를 다시 ChocolateDecorator로 감싼다.
        myCake = new ChocolateDecorator(myCake);
        System.out.println("주문 4(최종): " + myCake.makeCake());

        System.out.println("\n--- 한 번에 만들기 (체이닝) ---\n");

        // 이렇게 한 줄로도 표현 가능합니다. (안쪽에서부터 실행됨)
        Cake fullOptionCake = new ChocolateDecorator(
                new FruitDecorator(
                        new CreamDecorator(
                                new PlainCake()
                        )
                )
        );
        System.out.println("풀옵션 케이크: " + fullOptionCake.makeCake());
    }
}
