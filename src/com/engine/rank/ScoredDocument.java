package com.engine.rank;

import com.engine.mapreduce.InvertedIndex;

public class ScoredDocument extends InvertedIndex implements Comparable<ScoredDocument>{

	public ScoredDocument(InvertedIndex index, double score){
		super(index);
		this.score = score;
	}
	
	
	public double getScore(){
		return score;
	}
	
	public String toString(){
		return super.toString() + " " + score;
	}

	@Override
	public int compareTo(ScoredDocument other) {
		return Double.compare(this.getScore(), other.getScore());
	}
	
	private double score;

}
