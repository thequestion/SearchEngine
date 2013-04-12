package com.engine.mapreduce;

public class TermIndex{

	public TermIndex(String name, int count, int number){
		this.term = name;
		this.count = count;
		this.bucketNumber = number;
	}
	
	public TermIndex(String line){
		String[] info = line.split(" ");
		this.term = info[0];
		this.count = Integer.parseInt(info[1]);
		this.bucketNumber = Integer.parseInt(info[2]);
	}
	
	public TermIndex(){
		this.term = "";
		this.count = 0;
		this.bucketNumber = 0;
	}
	
	public void incrementCount(){
		this.count++;
	}
	
	public int getCount(){
		return count;
	}
	
	public String getTerm(){
		return term;
	}
	
	public int getBucket(){
		return bucketNumber;
	}
	
	@Override
	public String toString(){
		return this.term + " " + this.count + " " + this.bucketNumber;
	}
	//term name
	private String term;
	//times occured in docs
	private int count;
	//bucket number
	private int bucketNumber;
}
