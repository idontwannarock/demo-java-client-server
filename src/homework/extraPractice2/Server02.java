package homework.extraPractice2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
2. 從甲電腦讀取單一檔案，送到乙電腦儲存
 */

public class Server02 {

	public static void main(String[] args) {
		String fileName = "";
		File folder = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/source");
		if (!folder.exists()) {
			folder.mkdir();
		}
		long pointToStartSending = 0;
		File sourceFile;
		try (ServerSocket server = new ServerSocket(54321);) {
			System.out.println("Server02: 等待連線");
			Socket socket = server.accept();
			// 讀取Client送來的資訊
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			FileData fd = (FileData) ois.readObject();
			fileName = fd.fileName;
			pointToStartSending = fd.fileSize;
			System.out.println("Server02: 接收訊息成功");
			// 依照Client傳送來的資訊讀取檔案並回傳給Client
			sourceFile = new File(folder, fileName);
			FileInputStream fis = new FileInputStream(sourceFile);
			OutputStream os = socket.getOutputStream();
			fis.skip(pointToStartSending);
			byte[] b = new byte[8192];
			int length = 0;
			while ((length = fis.read(b)) != -1) {
				os.write(b, 0, length);
			}
			System.out.println("Server02: 傳送成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
