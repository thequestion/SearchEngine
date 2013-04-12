package com.engine.file;

import java.io.IOException;


public class IndexFile extends FileHandler{

	public IndexFile(String name) {
		super(name);
	}

	public boolean clearFileContent(){
		if(!file.canExecute())
			file.setExecutable(true);
		if(file.exists()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;
		return true;
	}

}
