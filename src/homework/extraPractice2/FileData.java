package homework.extraPractice2;

import java.io.Serializable;

public class FileData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146280413262170623L;
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
}
