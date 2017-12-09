package net04;

import java.io.File;
import java.io.IOException;

public class ReceiveFileMain {

	public static void main(String[] args) {
		File folder = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/target");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		ReceiveFileClient rfc = new ReceiveFileClient();
		try {
			rfc.connect("127.0.0.1", SendFileMain.PORT_NO);
			rfc.receiveFile(folder);
			rfc.receiveFile(folder);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rfc.closeSocket();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
