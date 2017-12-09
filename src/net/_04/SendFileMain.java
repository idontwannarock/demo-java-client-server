package net._04;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
此方法適用傳送單一或多個檔案
 */

public class SendFileMain {
	public static final int PORT_NO = 12345;

	public static void main(String[] args) {
		try {
			File folder = new File("//Users//wangchenghao//Documents//北科Java007//Java練習//source");
			File file1 = new File(folder, "rt.jar");
			File file2 = new File(folder, "myData.gif");
			SendFileServer server = new SendFileServer();
			Socket socket = server.setupServer(PORT_NO);
			server.sendFile(file1);
			server.sendFile(file2);
			socket.close();
			server.closeServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
