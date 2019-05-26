package com.milindt.sheets.controller;

import com.milindt.sheets.model.Sharing;
import com.milindt.sheets.service.SheetsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SheetsController.class)
public class SheetsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SheetsService sheetsService;

    @Test
    void addSharings_invokesSheetsService() throws Exception {
        //Arrange
        given(sheetsService.addSharing(any(Sharing.class)))
                .willReturn(new Sharing());

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/sheets/sharing")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

}
