package com.engine.searcher;

import java.io.IOException;
import java.util.List;

import com.engine.parser.IPage;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ISearch search = new SearchEngine();
		List<IPage> results = search.search("software engineering", 5);
		System.out.println(results.get(2).getURL());
		System.out.println(results.get(2).getPageId());
		System.out.println(results.get(2).getContent().substring(0,20));
		System.out.println(search.getTile(results.get(2).getURL()));
		
	}

}
