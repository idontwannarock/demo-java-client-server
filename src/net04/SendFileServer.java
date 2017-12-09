package net04;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendFileServer {

	ServerSocket server = null;
	Socket socket = null;

	public Socket setupServer(int portNo) throws IOException {
		System.out.println("Server: 等待連線...");
		server = new ServerSocket(portNo);
		socket = server.accept();
		System.out.println("Server: 與Client連線成功...");
		return socket;
	}

	public void closeServer() throws IOException {
		server.close();
	}

	public void sendFile(File file) throws IOException {
		long size = file.length();
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeLong(size);
		dos.writeUTF(file.getName());

		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] b = new byte[819200];
			int len = 0;
			System.out.println("Server: 開始傳送檔案...");
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}
		}
		System.out.println("檔案: " + file + " 傳送完畢");
	}
}
