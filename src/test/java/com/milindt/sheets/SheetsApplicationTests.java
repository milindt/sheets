package com.milindt.sheets;

import com.milindt.sheets.model.Sharing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SheetsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		Sharing request = new Sharing();
		request.setTest("success");
		ResponseEntity <Sharing> sharingAddedResponse =
				restTemplate.postForEntity("/sheets/sharing", request, Sharing.class);

		assertThat(sharingAddedResponse.getStatusCode())
				.isEqualTo(HttpStatus.OK);

		assertThat(sharingAddedResponse.getBody())
				.isNotNull();
	}

}
