package com.milindt.sheets;

import com.milindt.sheets.model.Sharing;
import com.milindt.sheets.model.Sheets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SheetsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads_andSaveAndGetSharingsWork() {
		Sharing request = new Sharing();
		request.setSelections(Collections.singletonList(Sheets.Actuals.toString()));
		request.setEmailIds(Collections.singletonList("test@test.com"));

		ResponseEntity <Sharing> sharingAddedResponse =
				restTemplate.postForEntity("/sheets/sharing", request, Sharing.class);

		assertThat(sharingAddedResponse.getStatusCode())
				.isEqualTo(HttpStatus.OK);
		assertThat(sharingAddedResponse.getBody())
				.isNotNull();

		ResponseEntity <List> getAllsharingsResponse =
				restTemplate.getForEntity("/sheets/sharings", List.class);
		assertThat(getAllsharingsResponse.getStatusCode())
				.isEqualTo(HttpStatus.OK);
		List sharings = getAllsharingsResponse.getBody();
		assertThat(sharings)
				.isNotNull();
		Object actualSharingMap = sharings.get(0);
		assertThat(actualSharingMap)
				.isInstanceOf(LinkedHashMap.class);
		Object actualSelections = ((Map) actualSharingMap).get("selections");
		assertThat(actualSelections)
				.isNotNull()
				.isInstanceOf(List.class);
		assertThat(((List)actualSelections).get(0))
				.isEqualTo(Sheets.Actuals.toString());
		Object emailIds = ((Map) actualSharingMap).get("emailIds");
		assertThat(emailIds)
				.isNotNull()
				.isInstanceOf(List.class);
		assertThat(((List)emailIds).get(0))
				.isEqualTo("test@test.com");
	}

}
