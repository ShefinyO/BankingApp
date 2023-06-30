package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Prize;
import com.ab.repositories.PrizeRepository;

@Service
public class PrizeServiceImpl implements PrizeService{
	
	@Autowired
	PrizeRepository prizeRepository;

	@Override
	public Prize createPrize(Prize p) {
		
		Prize newP = prizeRepository.save(p);
		
		return newP;
	}
	
	

}
