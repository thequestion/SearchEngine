package com.engine.file;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHandler extends FileBase{

	public FileHandler(String name){
		super(name);
	}
	/*
	 * 
	 */
	public void writeStringToFile(String data){
		try {
			if(!file.canWrite())
				file.setExecutable(true);
			FileUtils.writeStringToFile(file, data, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 */
	public void writeLinesToFile(Collection<?> lines){
		try {
			if(!file.canWrite())
				file.setExecutable(true);
			FileUtils.writeLines(file, lines, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readLinesFromFile(){
		try {
			if(!file.canRead())
				file.setExecutable(true);
			return FileUtils.readLines(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

