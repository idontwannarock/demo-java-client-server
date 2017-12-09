package net04;

import java.io.File;
import java.io.IOException;

public class SendFileMain {
	public static final int PORT_NO = 12345;

	public static void main(String[] args) {
		File folder = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/source");
		File pic1 = new File(folder, "rt.jar");
		File pic2 = new File(folder, "MyData.gif");

		SendFileServer server = new SendFileServer();
		try {
			server.setupServer(PORT_NO);

			Thread t1 = new Thread(new RunnableSocket(server, pic1));
			t1.start();
			t1.join();
			Thread t2 = new Thread(new RunnableSocket(server, pic2));
			t2.start();
			t2.join();
			
			// server.sendFile(pic1);
			// System.out.println(pic1 + "傳送完畢...");
			// server.sendFile(pic2);
			// System.out.println(pic2 + "傳送完畢...");
			// server.sendFile(pic3);
			// System.out.println(pic3 + "傳送完畢...");
			// server.sendFile(jar1);
			// System.out.println(jar1 + "傳送完畢...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				server.closeServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
