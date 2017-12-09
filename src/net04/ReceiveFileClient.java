package net04;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceiveFileClient {
	Socket socket = null;

	public Socket connect(String ip, int portNo) throws UnknownHostException, IOException {
		socket = new Socket(ip, portNo);
		return socket;
	}

	public void closeSocket() throws IOException {
		socket.close();
	}

	public void receiveFile(File folder) throws IOException {
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		long size = dis.readLong();
		String filename = dis.readUTF();
		File file = new File(folder, filename);

		try (FileOutputStream fos = new FileOutputStream(file);) {
			byte[] b = new byte[819200];
			int len = 0;
			while (size > 0) {
				len = is.read(b);
				fos.write(b, 0, len);
				size -= len;
			}
		}
		System.out.println("Client: 檔案: " + filename + "讀取完畢...");
	}
}
