package homework.extraPractice3;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSenderReader {
	private File folder;
	private Socket socket;
	private int fileNumber;
	private File[] files;
	OutputStream os;
	DataOutputStream dos;
	
	public FileSenderReader(File folder, Socket socket) {
		super();
		this.folder = folder;
		this.socket = socket;
	}
	
	public void send() {
		// 尋找目標資料夾內的檔案及其數量
		findFilesInFolder();
		// 先傳送檔案數目給Client
		sendFileNumber();
		// 發送fileNumber個檔案到Client
		for (int sendRound = 0; sendRound < fileNumber; sendRound++) {
			sendFileNamesAndFiles(sendRound);
		}
	}

	private void findFilesInFolder() {
		// listFiles()方法會將目標folder內所有檔案都存成多個File物件
		// 並將這些File物件存成陣列回傳
		files = folder.listFiles();
		fileNumber = files.length;
	}
	
	private void sendFileNumber() {
		openDataOutputStream();
		try {
			dos.writeInt(fileNumber);
		} catch (IOException e) {
			System.out.println("Server: 傳送檔案數量資訊有問題");
			System.out.println(e.getMessage());
		}
	}
	
	private void openDataOutputStream() {
		openOutputStream();
		dos = new DataOutputStream(os);
	}
	
	private void openOutputStream() {
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("Server: 開啟OutputStream有問題");
			System.out.println(e.getMessage());
		}
	}
	
	private void sendFileNamesAndFiles(int indexOfFile) {
		// 先抓到指定的檔案、大小及名稱
		File file = files[indexOfFile];
		long fileSize = file.length();
		String fileName = file.getName();
		try {
			// 用DataOutputStream傳送檔案大小及名稱給Client
			dos.writeLong(fileSize);
			dos.writeUTF(fileName);
		} catch (IOException e) {
			System.out.println("Server: 傳送檔案大小及名稱有問題");
			System.out.println(e.getMessage());
		}
		// 開始讀檔並寫出檔案
		sendFile(file, fileSize);
	}

	private void sendFile(File file, long fileSize) {
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] buffer = new byte[8192];
			int readIn = 0;
			while (true) {
				readIn = fis.read(buffer, 0, (int)(Math.min(buffer.length, fileSize)));
				os.write(buffer, 0, readIn);
				fileSize -= readIn;
				if (fileSize <= 0) break;
			}
			System.out.println("Server: 檔案" + file.getName() + "讀取並寫出完畢...");
		} catch (FileNotFoundException e) {
			System.out.println("Server: 找不到檔案");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Server: 讀取檔案問題");
			System.out.println(e.getMessage());
		}
	}
	
}
