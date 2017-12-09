package net._02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReceiverFileClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 12345);
			System.out.println("Client: 連線成功");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			// 建立要透過oos傳送給server的物件fd，包含要求傳回的檔案名稱跟檔案大小
			String fileName = "rt.jar";
			long fileSize = 0;
			File file = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/target", fileName);
			if (file.exists()) {
				fileSize = file.length();
			} else {
				fileSize = 0;
			}
			FileData fd = new FileData(fileName, fileSize);
			oos.writeObject(fd);
			// 接收server傳回的資料並寫入File物件指定的檔案
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("請按任意鍵繼續");
			br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
