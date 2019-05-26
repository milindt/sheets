package com.milindt.sheets.service;

import com.milindt.sheets.exception.InvalidSelectionException;
import com.milindt.sheets.exception.UnknownSheetException;
import com.milindt.sheets.model.Sharing;
import com.milindt.sheets.model.SheetsValidationStratergy;
import com.milindt.sheets.repository.SharingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheetsService {

    @Autowired
    private SheetsValidationStratergy sheetsValidationStratergy;

    @Autowired
    private SharingRepository sharingRepository;

    public Sharing addSharing(Sharing sharing) {
        validateSelections(sharing.getSelections());
        validateSheets(sharing);
        return sharingRepository.save(sharing);
    }

    private void validateSelections(List <String> selections) {
        selections.stream()
                .filter(s -> !s.matches(sheetsValidationStratergy.getValidSelectionRegex()))
                .findAny()
                .ifPresent(InvalidSelectionException::new);
    }

    private void validateSheets(Sharing sharing) {
        sharing.getSelections()
                .forEach(this::validateSelectedSheet);
    }

    private void validateSelectedSheet(String selectedSheet) {
        String sheetName = escapeInvertedComma(extractSheetName(selectedSheet));
        sheetsValidationStratergy.getValidSheetValues()
                .stream()
                .filter(sheetName::equals)
                .findFirst()
                .orElseThrow(UnknownSheetException::new);
    }

    /*private List<String> getValidSheetValues() {
        return Stream.of(Sheets.values())
                .map(Objects::toString)
                .collect(Collectors.toList());
    }*/

    private String escapeInvertedComma(String sheetName) {
       return sheetName.contains("'") ?
               sheetName.split("'")[1] :
               sheetName;
    }

    private String extractSheetName(String s) {
        return s.split("!")[0];
    }

    void setSheetsValidationStratergy(SheetsValidationStratergy sheetsValidationStratergy) {
        this.sheetsValidationStratergy = sheetsValidationStratergy;
    }

    void setSharingRepository(SharingRepository repository) {
        this.sharingRepository = repository;
    }

    public List <Sharing> getAllSharings() {
        return sharingRepository.findAll();
    }

}
