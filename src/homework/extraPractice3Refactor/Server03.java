package homework.extraPractice3Refactor;

/*
讀取目標資料夾內N個檔案，再傳給Client
 */

public class Server03 {
	static final int PORT_NO = 12345;
	static final String sourceFolderName = 
			"/Users/wangchenghao/Programming/github-repositories/HTML_CSS_practice/images/washDrawing";

	public static void main(String[] args) {
		// 建立FileSender物件fs
		FileTransporter ft = new FileTransporter(PORT_NO, sourceFolderName);
		// 開始讀取檔案並傳送給Client
		ft.sendFiles();
	}

}
