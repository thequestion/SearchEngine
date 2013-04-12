package com.engine.mapreduce;

import java.util.*;

import com.engine.constant.Constant;
import com.engine.file.IndexFile;
import com.engine.parser.Page;
import com.engine.parser.PageParser;

public class Mapper extends MapReduceBase implements IMapper{
	

	@Override
	public void startMapper(){
		constructTermIndexes();
	}
	public void constructTermIndexes(){
		PageParser parser = new PageParser();
		parser.prepareReadPage();
		while (parser.hasNextPage()) {
			Page page = parser.getNextPage();
			if( page.getLength()> 0){
				List<InvertedIndex> indexes = formInvertedIndexOfPage(page);
				Map<Integer, List<InvertedIndex>> partitions = partition(indexes);
				for(Integer i:partitions.keySet()){
					IndexFile handler = new IndexFile( Constant.INDEX_FILE_NAME + i );
					handler.writeLinesToFile(partitions.get(i));
				}
				//trace
				System.out.println(page.getPageId());
			}
		}
		parser.finalizeReadPage();
	}
	
	private Map<Integer, List<InvertedIndex>> partition(List<InvertedIndex> indexes){
		Map<Integer, List<InvertedIndex>> partitions = new HashMap<Integer, List<InvertedIndex>>();
		int size = indexes.size();
		for(int i = 0; i < size; i++){
			String term = indexes.get(i).getTerm();
			if(term == null || term.equals("")) System.out.println(indexes.get(i));
			Integer key = term.charAt(0) % Constant.INDEX_FILE_SIZE;
			if(partitions.get(key) == null){
				List<InvertedIndex> list = new ArrayList<InvertedIndex>();
				list.add(indexes.get(i));
				partitions.put(key, list);
			}else{
				partitions.get(key).add(indexes.get(i));
			}
		}
		return partitions;
	}

	
	private List<InvertedIndex> formInvertedIndexOfPage(Page page){
		int docId = page.getPageId();
		String url = page.getURL();
		List<InvertedIndex> indexes = new ArrayList<InvertedIndex>();
		Map<String, List<String>> positionMap = new HashMap<String, List<String>>();
		List<String> words = page.getWords();
		int size = words.size();
		for(int i = 0; i < size; i++){
			String term = words.get(i);
			if(positionMap.containsKey(term)){
				positionMap.get(term).add(Integer.toString(i));
			}else{
				List<String> positions = new ArrayList<String>();
				positions.add(Integer.toString(i));
				positionMap.put(term, positions);
			}
		}
		for(String term:positionMap.keySet()){
			InvertedIndex index = new InvertedIndex(term, docId, url, 
					positionMap.get(term).size(), positionMap.get(term));
			indexes.add(index);
		}
		return indexes;
	}
	

}
