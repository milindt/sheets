package com.milindt.sheets.model;

public enum Constants {
    SHEET_NAMES_REGULAR_EXPRESSION("^('[\\w\\s]+'|[\\w\\s]+)(![A-Z]+[0-9]+)?(:[A-Z]+[0-9]+)?");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
