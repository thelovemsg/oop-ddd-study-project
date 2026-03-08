package com.example.oopdddstudyproject.fare.infrastructure;

import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;

import java.util.List;

public interface FarePolicyRepository {
    List<FarePolicy> findByFareId(Long fareId);
    void save(FarePolicy farePolicy);
}