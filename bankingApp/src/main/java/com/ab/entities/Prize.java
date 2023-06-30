package com.ab.entities;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Prize {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int prizeId;
	
	String name;
	
	@ManyToMany(mappedBy = "allPrizes")
	private Set<BankAccount> bankAccount;
	
	
	public Prize(String name) {
		
		this.name = name;
		
	}

	public Prize() {
		
	}

	public int getPrizeId() {
		return prizeId;
	}

	public String getName() {
		return name;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Prize [prizeId=" + prizeId + ", name=" + name + "]";
	}
	
	
}
