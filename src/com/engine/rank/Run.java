package com.engine.rank;

import java.util.ArrayList;
import java.util.List;

import com.engine.file.IndexFile;
import com.engine.rank.GoogleResults.Result;



public class Run {

	private static List<String> constructQuery(){
		List<String> results = new ArrayList<String>();
		results.add("mondego");
		results.add("machine learning");
		results.add("software engineering");
		results.add("security");
		results.add("student affairs");
		results.add("graduate courses");
		results.add("Crista Lopes");
		results.add("REST");
		results.add("computer games");
		results.add("information retrieval");
		return results;
	}
	public static void main(String[] args)  {
		List<String> queries = constructQuery();
		for(String s:queries){
			StringBuffer buffer = new StringBuffer();
			buffer.append(s);
			buffer.append(" site:ics.uci.edu");
			GoogleSearch search = new GoogleSearch(buffer.toString());
			IndexFile handler = new IndexFile("google/"+s);
			List<Result> results = search.getReults();
			List<String> content = new ArrayList<String>();
			for(Result r:results){
				if(r.toString().indexOf("pdf") == -1 )
					content.add(r.toString());
			}
			handler.writeLinesToFile(content);
		}
	}


}
