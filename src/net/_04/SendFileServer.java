package net._04;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendFileServer {
	ServerSocket server = null;
	Socket socket = null;

	public Socket setupServer(int portNo) throws IOException {
		server = new ServerSocket(portNo);
		System.out.println("Server: 等待連線");
		socket = server.accept();
		return socket;
	}

	public void closeServer() throws IOException {
		server.close();
	}

	public void sendFile(File file) throws IOException {
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		long size = file.length();
		String fileName = file.getName();
		// 先告訴Client要傳送的檔案大小及檔案名稱
		dos.writeLong(size);
		dos.writeUTF(fileName);
		// 再開始傳送檔案
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[8192];
		int len = 0;
		System.out.println("Server: 開始傳送檔案...");
		while ((len = fis.read(b, 0, (int)Math.min(b.length, size))) != -1) {
			os.write(b, 0, len);
		}
		os.flush();
		System.out.println("Server: 檔案" + file.getName() + "已傳送完畢...");
	}
}
