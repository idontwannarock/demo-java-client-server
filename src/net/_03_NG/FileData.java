package net._03_NG;

import java.io.Serializable;

public class FileData implements Serializable {
	
	private static final long serialVersionUID = -4054187824639979357L;
	String fileName;
	long fileSize;
	
	public FileData() {
		super();
	}

	public FileData(String fileName, long fileSize) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
}
