package com.milindt.sheets;

import com.milindt.sheets.model.Constants;
import com.milindt.sheets.model.Sheets;
import com.milindt.sheets.model.SheetsValidationStratergy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SheetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheetsApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public SheetsValidationStratergy getSheetsValidationStratergy() {
		return new SheetsValidationStratergy() {
			@Override
			public List<String> getValidSheetValues() {
				return Stream.of(Sheets.values())
						.map(Objects::toString)
						.collect(Collectors.toList());
			}

			@Override
			public String getValidSelectionRegex() {
				return Constants.SHEET_NAMES_REGULAR_EXPRESSION.getValue();
			}
		};
	}

}
