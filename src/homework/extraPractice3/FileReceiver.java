package homework.extraPractice3;

import java.io.File;

public class FileReceiver {
	String IP;
	int PORT_NO;
	String targetFolderName;
	File folder;
	ClientSocketSetup css;
	FileReceiverWriter frw;
	
	public FileReceiver(String IP, int PORT_NO, String targetFolderName) {
		super();
		this.IP = IP;
		this.PORT_NO = PORT_NO;
		this.targetFolderName = targetFolderName;
		folder = new File(targetFolderName);
		css = new ClientSocketSetup(IP, PORT_NO);
	}
	
	public void receiveFile() {
		// 建立Client端Socket
		css.setupSocket();
		// 開始接收檔案並寫入資料夾
		frw = new FileReceiverWriter(folder, css.socket);
		frw.write();
		// 最後關閉Socket
		css.closeSocket();
	}
}
