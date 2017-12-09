package net._04;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReceiveFileClient {
	Socket socket = null;

	public Socket connect(String ip, int portNo) throws IOException {
		socket = new Socket(ip, portNo);
		return socket;
	}

	public void closeSocket() throws IOException {
		socket.close();
	}

	public void receiveFile(File folder) throws IOException {
		InputStream is1 = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is1);
		long size;
		// 先接收Server要傳送的檔案大小跟檔名
		size = dis.readLong();
		String fileName = dis.readUTF();
		InputStream is2 = socket.getInputStream();
		// 建立要接收的File物件
		File file = new File(folder, fileName);
		try (FileOutputStream fos = new FileOutputStream(file);) {
			// 再開始接收檔案
			byte[] b = new byte[8192];
			int len = 0;
			while (true) {
				len = is2.read(b, 0, (int)Math.min(b.length, size));
				fos.write(b, 0, len);
				size -= len;
				// 檔案傳完就break
				if (size <= 0) break;
			}
			System.out.println("Client: 檔案" + file.getName() + "讀取完畢...");
		}
	}
}
