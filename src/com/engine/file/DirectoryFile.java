package com.engine.file;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.engine.parser.Directory;

public class DirectoryFile extends FileHandler{

	public DirectoryFile(String directoryFileName) {
		super(directoryFileName);
	}

	public void writeDirectory(Directory dir) {
		try {
			if(!file.canWrite())
				file.setExecutable(true);
			FileUtils.writeStringToFile(file, dir.toString() + "\r\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readAllDirectory(){
		return readLinesFromFile();
	}
}
