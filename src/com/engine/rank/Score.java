package com.engine.rank;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Hits;

import com.engine.constant.Constant;
import com.engine.file.IndexFile;
import com.engine.file.TermFile;
import com.engine.mapreduce.InvertedIndex;
import com.engine.mapreduce.TermIndex;

public class Score {

	
	
	public TermIndex getTermIndex(List<String> indexes,int start, int end, String input){
		if(start > end)
			return null;
		int mid = start + (end-start)/2;
		String line = indexes.get(mid);
		TermIndex termIndex = new TermIndex(line);
		String term = termIndex.getTerm();
		if(term.compareTo(input) == 0)
			return termIndex;
		else if(term.compareTo(input) > 0)
			return getTermIndex(indexes, start, mid-1, input);
		else
			return getTermIndex(indexes, mid+1, end, input);
	}
	
	
	public void calculateRelavance(TermIndex index){
		int bucketNumber = index.getBucket();
		IndexFile handler = new IndexFile(Constant.INDEX_FILE_NAME + bucketNumber);
		List<String> lines = handler.readLinesFromFile();
		List<ScoredDocument> documents = new ArrayList<ScoredDocument>();
		for(String s:lines){
			InvertedIndex invertedIndex = new InvertedIndex(s);
			String term = invertedIndex.getTerm();
			ScoredDocument document = null;
			int termFrequency = -1;
			if(term.equals(index.getTerm()))
				termFrequency = invertedIndex.getCount();
			double score = calculateScore(termFrequency, index.getCount());
			document = new ScoredDocument(invertedIndex, score);
			documents.add(document);
		}
		IndexFile scoreHandler = new IndexFile(Constant.SCORE_FILE_NAME + bucketNumber);
		scoreHandler.writeLinesToFile(documents);
	}
	
	
	
	private double calculateScore(int termFrequency, int documentFrequency) {
		if(termFrequency > 0 && documentFrequency > 0){
			return Math.log10(1+termFrequency) *
					Math.log10(Constant.CORPUS_SIZE/documentFrequency);		
		}
		return -1;
	}
	
	




	public static void main(String[] args) {	
		TermFile handler = new TermFile(Constant.TERM_FILE_NAME);
		List<String> indexes = handler.readLinesFromFile();
		int count = 0;
		for(String s:indexes){
			Score test = new Score();
			TermIndex term = new TermIndex(s);
			test.calculateRelavance(term);
			//trace
			System.out.println(++count);
		}

	}

	
	

}
