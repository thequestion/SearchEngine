package com.engine.parser;

import java.util.List;

import com.engine.constant.Constant;
import com.engine.file.*;

public class PageParser {
	public PageParser(){
		dirFile = new DirectoryFile(Constant.DIRECTORY_FILE_NAME);
		
	}
	
	public void prepareReadPage(){
		directories = dirFile.readAllDirectory();
		flag = 0;
	}
	
	public boolean hasNextPage(){
		return flag < directories.size();
	}

	public void finalizeReadPage(){
		directories.clear();
	}
	public Page getNextPage(){
		Page page = null;

		Directory directory = new Directory(directories.get(flag));
		directory.setInfo();
		int id = directory.getDocId();
		int length = directory.getContentLentgh();
		if(length > 0){
			String pageFileName = Constant.PAGE_FILE_NAME + id;
			PageFile pageFile = new PageFile(pageFileName);
			pageFile.initReader();
			String content = pageFile.readPage(length);
			page = new Page(directory, content);
			pageFile.closeHandler();
		}
		flag++;
		return page;
	}
	/*
	public Page getNextPage(){
		Page page = null;

		Directory line = new Directory(directories.get(flag));
		
		while(!line.validateLine()){
			flag++;
			line = new Directory(directories.get(flag));
		}
		
		line.setInfo();
		
		int contentLength = line.getContentLentgh()-1;
		if(contentLength > 0){
			String content = pageFile.readPage(contentLength);
			page = new Page(line, content);
		}
		
		flag++;
		return page;
	}*/
	
	private List<String> directories;
	private DirectoryFile dirFile;
	
	private int flag;
}
