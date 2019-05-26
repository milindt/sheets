package com.milindt.sheets.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SheetsServiceTest {

    private SheetsService service;

    @BeforeEach
    void setUp() {
        service = new SheetsService();
    }

    @Test
    void addSharings_validatesSelections() {
        
    }
}
