package com.engine.rank;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import com.engine.rank.GoogleResults.Result;
import com.google.gson.Gson;

public class GoogleSearch {

	private String query;

	public GoogleSearch(String query){
		this.query = query;
	}
	
	public List<Result> getReults(){
		GoogleResults result =  null;
		try {
			result = createSearch();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.getResponseData().getResults();

	}
	
	private GoogleResults createSearch() throws UnsupportedEncodingException, IOException{
		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String charset = "UTF-8";

	    URL url = new URL(google + URLEncoder.encode(query, charset) + "&rsz=8");
	    Reader reader = new InputStreamReader(url.openStream(), charset);
	    
	    return new Gson().fromJson(reader, GoogleResults.class);

	    
	}
}
