package com.milindt.sheets.controller;

import com.milindt.sheets.model.Sharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sheets")
public class SheetsController {



    @PostMapping(value = "/sharing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sharing> addSharing(@RequestBody Sharing sharing) {
        return ResponseEntity.ok(sharing);
    }

}
