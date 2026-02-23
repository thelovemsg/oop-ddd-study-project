package com.example.oopdddstudyproject;

import com.example.oopdddstudyproject.common.service.TimeGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FakeTimeGenerator implements TimeGenerator {

    private final long millis;

    @Override
    public long millis() {
        return millis;
    }
}
