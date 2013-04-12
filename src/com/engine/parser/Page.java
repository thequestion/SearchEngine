package com.engine.parser;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.engine.constant.Constant;
import com.engine.file.FileHandler;

public class Page implements IPage{
	
	public Page(Directory dir, String content){
		this.directory = dir;
		this.content = content;
	}

	public String getURL() {
		return this.directory.getURL();
	}

	public int getPageId() {
		return this.directory.getDocId();
	}

	public int getLength() {
		return this.directory.getContentLentgh();
	}
	
	public List<String> getWords(){
		return filterStopWords( tokenizeString(content) );
	}
	
	public String getContent() {
		return this.content;
	}
	
	private List<String> tokenizeString(String str){
		List<String> tokens = 
				Arrays.asList( str.split(Constant.WORD_FILTER.pattern()) );
		return tokens;
	}
	
	public List<String> filterStopWords(List<String> words){
		List<String> stopWords = new ArrayList<String>();	
		List<String> filterWords = new ArrayList<String>();
		FileHandler handler = new FileHandler("stopwords");
		stopWords = handler.readLinesFromFile();
		int size = words.size();
		for(int i = 0; i < size; i++){
			String word = words.get(i).toLowerCase().trim();
			if(!stopWords.contains(word) && !word.equals(""))
				filterWords.add(word);
		}
		return filterWords;
	}
	
	private String content;
	private Directory directory;

	
}
