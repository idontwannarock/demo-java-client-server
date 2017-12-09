package net._04;

import java.io.File;

/*
此方法適用傳送單一或多個檔案
 */

public class ReceiveFileMain {

	public static void main(String[] args) {
		try {
			File folder = new File("/Users/wangchenghao/Documents/北科Java007/Java練習/target");
			if (!folder.exists())
				folder.mkdir();
			// 建立ReceiverFileClient物件來接收檔案並存進指定資料夾
			ReceiveFileClient rfc = new ReceiveFileClient();
			rfc.connect("127.0.0.1", SendFileMain.PORT_NO);
			rfc.receiveFile(folder);
			rfc.receiveFile(folder);
			rfc.closeSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
