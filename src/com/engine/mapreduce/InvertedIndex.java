package com.engine.mapreduce;

import java.util.*;

import com.engine.constant.Constant;

public class InvertedIndex{
	public InvertedIndex(String line){
		String[] info = line.split(" ");
		this.term = info[0];
		this.docId = Integer.parseInt(info[1]);
		this.url = info[2];
		this.count = Integer.parseInt(info[3]);
		this.positions = parsePositions(info[4]);
	}
	public InvertedIndex(InvertedIndex other){
		this.term = other.term;
		this.docId = other.docId;
		this.url = other.url;
		this.count = other.count;
		this.positions = other.positions;
	}
	public InvertedIndex(String termName, int docId, String url, int count, List<String> positions){
		this.term = termName;
		this.docId = docId;
		this.url = url;
		this.count = count;
		this.positions = positions;
	}
	public String getTerm(){
		return term;
	}
	public int getId(){
		return docId;
	}
	public String getURL(){
		return url;
	}
	public int getCount(){
		return count;
	}
	public List<String> getPositions(){
		return positions;
	}
	
	@Override
	public String toString(){
		return this.term + " " + this.docId + "" + this.url + " "
				+ this.count + " " + this.positions.toString();
	}

	private List<String> parsePositions(String str) {
		return Arrays.asList( str.split(Constant.WORD_FILTER.pattern()));
	}
	
	//term name
	String term;
	
	//document id
	int docId;
	
	//document url
	String url;
	
	//times occur in one document
	int count;
	
	//list of positions
	List<String> positions;


}
