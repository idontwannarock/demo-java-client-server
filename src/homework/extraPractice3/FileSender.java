package homework.extraPractice3;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class FileSender {
	int PORT_NO;
	String sourceFolderName;
	File folder;
	ServerSocketSetup sss;
	FileSenderReader fsr;

	public FileSender(int PORT_NO, String sourceFolderName) {
		super();
		this.PORT_NO = PORT_NO;
		this.sourceFolderName = sourceFolderName;
		try {
			folder = new File(sourceFolderName);
			sss = new ServerSocketSetup(PORT_NO);
		} catch (UnknownHostException e) {
			System.out.println("Server: 無法開啟連線");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Server: 無法開啟連線");
			System.out.println(e.getMessage());
		}
	}

	public void sendFile() {
		// 建立Server端Socket
		sss.setupSocket();
		// 開始讀取資料夾內檔案並傳送
		fsr = new FileSenderReader(folder, sss.socket);
		fsr.send();
		// 最後關閉Socket
		sss.closeSocket();
	}

}
