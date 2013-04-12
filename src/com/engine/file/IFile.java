/**
 * 
 */
package com.engine.file;

import java.util.Collection;

/**
 * @author yuan
 *
 */
public interface IFile {
	public abstract void writeStringToFile(String data);
	public abstract void writeLinesToFile(Collection<?> lines);
}
