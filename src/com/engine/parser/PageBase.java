package com.engine.parser;


public class PageBase {
	int id;
	
	public PageBase(int id){this.id = id;}
	public int getPageId(){return this.id;};
	
//	protected String getContent(){
//		DirectoryFile handler = new DirectoryFile(Constant.DIRECTORY_FILE_NAME);
//		PageFile pageHandler = new PageFile(Constant.PAGE_FILE_NAME+id);
//		List<String> lines = handler.readAllDirectory();
//		int contentLength = 0;
//		for(String s:lines){
//			Directory dir = new Directory(s);
//			if(dir.getDocId() == id)
//				contentLength = dir.getContentLentgh();
//		}
//		return pageHandler.readPage(contentLength);	
//	}
}
