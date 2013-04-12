/**
 * 
 */
package com.engine.searcher;

import java.util.List;

import com.engine.parser.IPage;

/**
 * This is the interface of search function
 *
 */
public interface ISearch {
	//return the list of URLs 
	public List<IPage> search(String query, int size);
	public String getTile(String url);
}
