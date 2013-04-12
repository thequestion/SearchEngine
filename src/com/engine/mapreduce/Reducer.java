package com.engine.mapreduce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.constant.Constant;
import com.engine.file.IndexFile;
import com.engine.file.TermFile;

public class Reducer extends MapReduceBase implements IReducer{
	
	@Override
	public void startReduce() {
		mergeTermIndexes();
	}
	
	public void mergeTermIndexes() {
		TermFile termHandler = new TermFile( Constant.TERM_FILE_NAME );
		for(int i = 0; i < Constant.INDEX_FILE_SIZE; i++){
			IndexFile handler = new IndexFile( Constant.INDEX_FILE_NAME + i );
			List<String> lines = handler.readLinesFromFile();
			Map<String, TermIndex> termMap = new HashMap<String, TermIndex>();
			for(String s:lines){
				InvertedIndex index = new InvertedIndex(s);
				String termName = index.getTerm();
				if(termMap.containsKey(termName)){
					termMap.get(termName).incrementCount();
				}else{
					TermIndex termIndex = new TermIndex(termName, 1, i);
					termMap.put(termName, termIndex);
				}		
			}
			
			termHandler.writeLinesToFile( termMap.values() );
			//trace
			System.out.println(i);
		}
		sortTermIndex(termHandler);
	}
	
	private void sortTermIndex(TermFile handler){
		List<String> lines = handler.readLinesFromFile();
		Collections.sort(lines);
		if(handler.clearFileContent())
			handler.writeLinesToFile( lines );
	}
	
	public void sortPartitions(){
		for(int i = 0; i < Constant.INDEX_FILE_SIZE; i++){
			IndexFile handler = new IndexFile( Constant.INDEX_FILE_NAME + i );
			List<String> lines = handler.readLinesFromFile();
			Collections.sort(lines);
			if(handler.clearFileContent())
				handler.writeLinesToFile(lines);
			//trace
			System.out.println(i);
				
		}
	}
	
	
	private Map<String, List<InvertedIndex>> addInMap(Map<String, List<InvertedIndex>> map,
			String key, InvertedIndex index){
		if(map.containsKey(key)){
			map.get(key).add(index);
		}else{
			List<InvertedIndex> list = new ArrayList<InvertedIndex>();
			list.add(index);
			map.put(key, list);
		}
		return map;
	}

}
