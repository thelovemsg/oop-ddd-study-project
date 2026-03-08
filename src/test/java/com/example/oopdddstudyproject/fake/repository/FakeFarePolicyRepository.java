package com.example.oopdddstudyproject.fake.repository;

import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.infrastructure.FarePolicyRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeFarePolicyRepository implements FarePolicyRepository {
    private final List<FarePolicy> store = new ArrayList<>();

    public void save(FarePolicy policy) {
        store.add(policy);
    }

    @Override
    public List<FarePolicy> findByFareId(Long fareId) {
        return store.stream()
                .filter(p -> p.getFareId().equals(fareId))
                .toList();
    }
}