package net._03_NG;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendFileServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("Server: 等待連線請求");
			Socket socket = server.accept();
			System.out.println("Server: 遠方電腦已連線成功");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// 接收client傳來的物件，其中的屬性包括檔案名稱跟檔案長度
			ObjectInputStream ois = new ObjectInputStream(is);
			FileData fd = (FileData) ois.readObject();
			String fileName = fd.getFileName();
			long fileSize = fd.getFileSize();
			// 尋找server端是否有該檔案後，再寫進OutputStream將檔案內容傳送給client端
			File file = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/source", fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			
			// 若client端已有檔案但不完整，則用skip()跳到要續傳的位元組，藉此達到續傳的效果
			fis.skip(fileSize);
			
			byte[] b = new byte[819200];
			int len = 0;
			long ttl = 0;
			while ((len = fis.read(b)) != -1) {
				ttl += len;
				os.write(b, 0, len);
//				if (ttl > 40000000) {
//					System.out.println();
//					break;
//				}
			}
			System.out.println("Server: 此次寫出" + ttl + "位元組");
			
			// 傳送第二個檔案之前，要等Client端回傳已傳送的檔案長度
			System.out.println("Server: 等待客戶端送回位元組數...");
			DataInputStream dis = new DataInputStream(is);
			long size = dis.readLong();
			System.out.println("Server: 客戶端送回讀到的位元組數: " + size);
			
			// 因並非用try with resource，所以要關閉連線，才會傳送-1的訊號給client端
			os.close();
			socket.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("請按任意鍵繼續");
			br.read();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
