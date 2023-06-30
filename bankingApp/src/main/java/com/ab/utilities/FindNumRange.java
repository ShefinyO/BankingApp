package com.ab.utilities;

public class FindNumRange {
	
	private int num;
	private int rangeStart;
	private int rangeEnd;
	
	
	public FindNumRange(int num) {
		
		this.num = num;
		
	}
	
	
	public int getRangeStart() {
		return rangeStart;
	}
	public int getRangeEnd() {
		return rangeEnd;
	}
	
	public void setRangeStart() {
		
		int firstNum = Math.floorDiv(num, 1000);
		
		rangeStart = firstNum * 1000; 
		
	}
	
	public void setRangeEnd() {
		
		rangeEnd = rangeStart + 1000;
		
	}
	
	
	
	

}
