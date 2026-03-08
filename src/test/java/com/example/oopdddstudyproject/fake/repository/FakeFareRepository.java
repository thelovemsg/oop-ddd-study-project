package com.example.oopdddstudyproject.fake.repository;

import com.example.oopdddstudyproject.fare.domain.Fare;
import com.example.oopdddstudyproject.fare.infrastructure.FareRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeFareRepository implements FareRepository {
    private final Map<Long, Fare> store = new HashMap<>();

    public void save(Fare fare) {
        store.put(fare.getId(), fare);
    }

    @Override
    public Fare findById(Long id) {
        return store.get(id);
    }
}