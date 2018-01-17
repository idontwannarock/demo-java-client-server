package homework.extraPractice3Refactor;

/*
接收Server傳來的N個檔案，並寫入資料夾
 */

public class Client03 {
	static final String IP = "127.0.0.1";
	static final int PORT_NO = 12345;
	static final String targetFolder = 
			"/Users/wangchenghao/Documents/北科Java007/Java練習/target";

	public static void main(String[] args) {
		// 建立FileReceiver物件
		FileTransporter ft = new FileTransporter(IP, PORT_NO, targetFolder);
		// 開始接收檔案並寫入資料夾
		ft.receiveFiles();
	}

}
