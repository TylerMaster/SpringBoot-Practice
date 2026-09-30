package com.example.nobsv2.catfact;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.nobsv2.Query;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

	private final RestTemplate restTemplate;
	private final String url = "https://catfact.ninja/fact";
	private final String MAX_LENGTH = "max_length";
	
	public CatFactService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		
	}
	
	
	@Override
	public ResponseEntity<CatFactDTO> execute(Integer input){
		URI uri = UriComponentsBuilder
				.fromUriString(url)
				.queryParam(MAX_LENGTH, input)
				.build()
				.toUri();
		
		
		//headers
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		
		//handle cat fact error response
		
		try {
			
			
			ResponseEntity<CatFactResponse> response = restTemplate
					.exchange(uri, HttpMethod.GET, entity, CatFactResponse.class);
			CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());
			return ResponseEntity.ok(catFactDTO);
		} catch (Exception exception) {
			//can throw your own custom exception (handler in previous video)
			throw new RuntimeException("Cat Facts API is down");
			
		}
		
	
		
		/*CatFactResponse response = restTemplate.getForObject("https://catfact.ninja/fact", CatFactResponse.class);
		CatFactDTO catFactDTO = new CatFactDTO(response.getFact());*/
		
	
	}
}
