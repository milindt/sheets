package com.milindt.sheets.controller;

import com.milindt.sheets.model.Sharing;
import com.milindt.sheets.service.SheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sheets")
public class SheetsController {

    @Autowired
    private SheetsService sheetsService;

    @PostMapping(value = "/sharing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sharing> addSharing(@RequestBody Sharing sharing) {
        return ResponseEntity.ok(sheetsService.addSharing(sharing));
    }

    @GetMapping(value = "/sharings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List <Sharing>> getAllSharings() {
        return ResponseEntity.ok(sheetsService.getAllSharings());
    }

}
