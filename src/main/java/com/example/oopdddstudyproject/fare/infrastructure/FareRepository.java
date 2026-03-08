package com.example.oopdddstudyproject.fare.infrastructure;

import com.example.oopdddstudyproject.fare.domain.Fare;

public interface FareRepository {
    Fare findById(Long id);
    void save(Fare fare);
}