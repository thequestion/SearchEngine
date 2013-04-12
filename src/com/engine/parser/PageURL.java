package com.engine.parser;

public class PageURL extends PageBase implements IPage{

	public PageURL(int id, String url, String content){
		super(id);
		this.url = url;
		this.content = content;
	}
	
	@Override
	public String getURL(){
		return this.url;
	}	

	@Override
	public String getContent() {
		return this.content;
	}
	
	
	private String url;
	private String content;
	
}
