package net04;

import java.io.File;
import java.io.IOException;

public class RunnableSocket implements Runnable{
	SendFileServer server;
	File file;

	public RunnableSocket(SendFileServer server, File file) {
		super();
		this.server = server;
		this.file = file;
	}

	@Override
	public void run() {
		try {
			this.server.sendFile(this.file);
			System.out.println(this.file + "傳送完畢...");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
