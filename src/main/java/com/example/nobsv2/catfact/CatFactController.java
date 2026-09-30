package com.example.nobsv2.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFactController {

	
	
	private final CatFactService catFactService;
	
	public CatFactController(CatFactService catFactService) {
		this.catFactService = catFactService;
		
	}
	
	@GetMapping("/catfact")
	public ResponseEntity<CatFactDTO> getFact(){
		
		return catFactService.execute(100);
	}
}
