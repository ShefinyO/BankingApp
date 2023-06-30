package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entities.Prize;
import com.ab.services.PrizeService;

@RestController
public class PrizeController {
	
	
	@Autowired
	PrizeService prizeService;
	
	
	@PostMapping("/prize")
	public ResponseEntity<Prize> createPrize(@RequestBody Prize p){
		
		Prize newP = prizeService.createPrize(p);
		
		if(newP != null) return new ResponseEntity<Prize>(newP, HttpStatus.CREATED);
		return new ResponseEntity<Prize>(HttpStatus.NO_CONTENT);
		
		
	}
	

}
