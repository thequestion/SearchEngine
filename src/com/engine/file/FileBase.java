package com.engine.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public abstract class FileBase implements IFile{

	public FileBase(String name){
		file = new File(name);
		createNewFile();
	}
	/*
	 * create file if file not exist; if exists, do nothing
	 */
	private void createNewFile(){
		if( !file.exists() )
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public abstract void writeStringToFile(String data);
	public abstract void writeLinesToFile(Collection<?> lines);
	
	protected File file;
}
