package com.milindt.sheets.model;

import java.util.List;

public interface SheetsValidationStratergy {

    List <String> getValidSheetValues();

    String getValidSelectionRegex();
}
