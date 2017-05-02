package br.lsegala.springcomelasticsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void deveFazerUploadArquivo() {
		ClassPathResource classPathResource = new ClassPathResource("/teste.pdf", getClass());
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", classPathResource);
		ResponseEntity<String> response = this.restTemplate.postForEntity("/importadorpdf/", map, String.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualToIgnoringCase("arquivo importado com sucesso");
	}

}
