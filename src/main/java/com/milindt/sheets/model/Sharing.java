package com.milindt.sheets.model;

import java.util.List;

public class Sharing {

    private String test;
    private List <String> selections;

    public Sharing() {
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
    }

    public List <String> getSelections() {
        return selections;
    }
}
