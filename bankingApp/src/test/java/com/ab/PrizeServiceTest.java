package com.ab;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.Modifying;

import com.ab.entities.Prize;
import com.ab.repositories.PrizeRepository;
import com.ab.services.PrizeService;
import com.ab.services.PrizeServiceImpl;

@ExtendWith(MockitoExtension.class)
class PrizeServiceTest {
	
	@Mock
	PrizeRepository prizeRepository;
	
	@InjectMocks
	PrizeServiceImpl prizeService;
	

	@Test
	public void createPrizeTest() {
		
		//Arrange
		
		Prize p = new Prize("Amazon Voucher");
		
		Prize rp = new Prize("Amazon Voucher");
		rp.setPrizeId(1);
		
		when(prizeService.createPrize(p)).thenReturn(rp);
		
		//Act
		
		Prize expectedP = prizeService.createPrize(p);
		
		//Assert
		
		assertEquals(rp, expectedP);
		
		
		
		
	}

}
