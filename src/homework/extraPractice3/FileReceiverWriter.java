package homework.extraPractice3;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileReceiverWriter {
	private File folder;
	private Socket socket;
	private InputStream is;
	private DataInputStream dis;
	private int fileNumber;

	public FileReceiverWriter(File folder, Socket socket) {
		super();
		this.folder = folder;
		this.socket = socket;
	}
	
	public void write() {
		// 先從Server處取得此次要接收並寫入的檔案數
		getFileNumbers();
		// 開始依序寫入
		for (int writeRound = 0; writeRound < fileNumber; writeRound++) {
			receiveFileInfoAndWriteFile();
		}
	}
	
	private void receiveFileInfoAndWriteFile() {
		// 接收檔案大小及名稱
		long fileSize;
		String fileName;
		try {
			fileSize = dis.readLong();
			fileName = dis.readUTF();
			// 建構接收讀取到檔案的File物件
			File file = new File(folder, fileName);
			// 開始接收並寫入
			wirteFile(file, fileSize);
		} catch (IOException e) {
			System.out.println("Client: 讀取檔案大小或名稱有問題");
			System.out.println(e.getMessage());
		}
	}
	
	private void wirteFile(File file, long fileSize) {
		// 用FileOutputStream寫入檔案
		try (FileOutputStream fos = new FileOutputStream(file);) {
			byte[] buffer = new byte[8192];
			int readIn = 0;
			while (true) {
				readIn = is.read(buffer, 0, (int)(Math.min(buffer.length, fileSize)));
				fos.write(buffer, 0, readIn);
				fileSize -= readIn;
				if (fileSize <= 0) break;
			}
			System.out.println("Client: 檔案" + file.getName() + "讀取並寫入完畢...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getFileNumbers() {
		openDataInputStream();
		try {
			fileNumber = dis.readInt();
		} catch (IOException e) {
			System.out.println("Client: 讀取檔案數量資訊有問題");
			System.out.println(e.getMessage());
		}
	}
	
	private void openDataInputStream() {
		openInputStream();
		dis = new DataInputStream(is);
	}
	
	private void openInputStream() {
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			System.out.println("Client: 開啟InputStream有問題");
			System.out.println(e.getMessage());
		}
	}
}
