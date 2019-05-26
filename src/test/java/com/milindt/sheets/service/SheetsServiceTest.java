package com.milindt.sheets.service;

import com.milindt.sheets.exception.UnknownSheetException;
import com.milindt.sheets.model.Constants;
import com.milindt.sheets.model.Sharing;
import com.milindt.sheets.model.SheetsValidationStratergy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class SheetsServiceTest {

    private SheetsService service;

    private class TestValidationStratergy implements SheetsValidationStratergy {

        @Override
        public List <String> getValidSheetValues() {
            return Arrays.asList("TestValidSheetName",
                    "Sheet Name Without Spaces Single Cell",
                    "SheetNameWithoutSpacesSingleCell",
                    "OnlyTheSheetNameWithoutQuotes",
                    "SheetNameWithQuotesNoSpaces",
                    "Sheet Name With Quotes And Spaces",
                    "Long Sheet Name With Spaces and Range",
                    "SheetNameWithoutSpacesWithRanges");
        }

        @Override
        public String getValidSelectionRegex() {
            return Constants.SHEET_NAMES_REGULAR_EXPRESSION.getValue();
        }
    }

    @BeforeEach
    void setUp() {
        service = new SheetsService();
        service.setSheetsValidationStratergy(new TestValidationStratergy());
    }

    @Test
    @DisplayName("Rejects Selections with sheet name that is not known")
    void addSharings_rejectAnInvalidSheetName() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("TestNotPresent"));
        Throwable throwable = catchThrowable(() -> service.addSharing(sharing));

        assertThat(throwable)
                .isInstanceOf(UnknownSheetException.class);
    }

    @Test
    @DisplayName("Accepts Selections with sheet name that is known")
    void addSharings_acceptsValidSheetNamesWithSelections() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("TestValidSheetName"));
        Sharing addedSharing = service.addSharing(sharing);

        assertThat(addedSharing)
                .isNotNull();
    }

    @Test
    @DisplayName("Accepts `SheetNameWithoutSpacesSingleCell`!A1")
    void addSharings_acceptsValidSelectionWithInvertedCommas() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("'SheetNameWithoutSpacesSingleCell'!A1"));
        assertThat(service.addSharing(sharing))
            .isNotNull();
    }

    @Test
    @DisplayName("Accepts 'Sheet Name With Spaces Single Cell'!A1")
    void addSharings_acceptsValidSelectionWithInvertedCommasAndSpaces() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("'Sheet Name Without Spaces Single Cell'!A1"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }

    @Test
    @DisplayName("Accepts OnlyTheSheetNameWithoutQuotes")
    void addSharings_acceptsValidSelectionOnlyTheSheetNameWithoutQuotes() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("OnlyTheSheetNameWithoutQuotes"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }

    @Test
    @DisplayName("Accepts 'SheetNameWithQuotesNoSpaces'")
    void addSharings_acceptsValidSelectionWithQuotesNoSpaces() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("'SheetNameWithQuotesNoSpaces'"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }

    @Test
    @DisplayName("Accepts 'Sheet Name With Quotes And Spaces'")
    void addSharings_acceptsValidSelectionWithQuotesAndSpaces() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("'Sheet Name With Quotes And Spaces'"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }


    @Test
    @DisplayName("Accepts 'Long Sheet Name With Spaces and Range'!B3:B5")
    void addSharings_acceptsValidSelectionWithLongNameWithQuotesAndSpacesAndRange() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("'Long Sheet Name With Spaces and Range'!B3:B5"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }

    @Test
    @DisplayName("Accepts SheetNameWithoutSpacesWithRanges!B2:B5")
    void addSharings_acceptsValidSelectionWithoutQuotesAndSpacesWithRange() {
        Sharing sharing = new Sharing();
        sharing.setSelections(Collections.singletonList("SheetNameWithoutSpacesWithRanges!B2:B5"));
        assertThat(service.addSharing(sharing))
                .isNotNull();
    }



}
