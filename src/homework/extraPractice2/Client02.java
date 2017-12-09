package homework.extraPractice2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
2. 從甲電腦讀取單一檔案，送到乙電腦儲存
 */

public class Client02 {

	public static void main(String[] args) {
		File folder = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/target");
		if (!folder.exists()) {
			folder.mkdir();
		}
		String fileName = "rt.jar";
		File targetFile = new File(folder, fileName);
		long fileSize;
		if (targetFile.exists()) {
			fileSize = targetFile.length();
		} else {
			fileSize = 0;
		}
		FileData fd = new FileData(fileName, fileSize);
		try (Socket socket = new Socket("127.0.0.1", 54321);) {
			// 告訴Server要求傳回的檔案名稱及前一次傳送的進度
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(fd);
			System.out.println("Client02: 訊息傳送成功");
			// 接收傳回的檔案，並append到現存檔案或新建檔案儲存
			InputStream is = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream(targetFile, true);
			byte[] b = new byte[8192];
			int length = 0;
			while ((length = is.read(b)) != -1) {
				fos.write(b, 0 , length);
			}
			System.out.println("Client02: 檔案接收完畢");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}