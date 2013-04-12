package com.engine.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;

public class PageFile extends FileHandler{

	public PageFile(String pageFileName) {
		super(pageFileName);
	}

	public void writePage(String content) {
		writeStringToFile(content);
	}
	
	public void initReader(){
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String readPage(int length){
		char[] buffer = new char[length];
		try {
			IOUtils.readFully(input, buffer, 0, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new StringBuffer().append(buffer).toString();
	}
	
	public void closeHandler(){
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Reader input;
}
