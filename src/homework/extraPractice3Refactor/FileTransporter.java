package homework.extraPractice3Refactor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileTransporter {
	private String IP;
	private int PORT_NO;
	private SocketSetup server, client;
	private Socket socket;
	private String folderName;
	private File folder;
	private File[] files;
	private int numberOfFiles;
	private OutputStream os;
	private DataOutputStream dos;
	private InputStream is;
	private DataInputStream dis;

	// constructor for server side
	public FileTransporter(int PORT_NO, String sourceFolderName) {
		super();
		this.setPORT_NO(PORT_NO);
		server = new SocketSetup(PORT_NO);
		socket = server.getSocket();

		this.setFolderName(sourceFolderName);
		folder = new File(sourceFolderName);
		FileFinder fileFinder = new FileFinder(folder);
		files = fileFinder.findFiles();
		numberOfFiles = fileFinder.getNumberOfFilesToSend(files);
	}

	// only for server side to find all files in target folder and count the number
	// inner class work only for server side
	private class FileFinder {
		private File folder;
		private File[] files;
		private int numberOfFiles;

		public FileFinder(File folder) {
			super();
			this.folder = folder;
		}

		public File[] findFiles() {
			this.files = folder.listFiles();
			return files;
		}

		public int getNumberOfFilesToSend(File[] files) {
			this.numberOfFiles = files.length;
			return numberOfFiles;
		}
	}

	public void sendFiles() {
		informClientNumberOfFiles();
		// 發送fileNumber個檔案到Client
		for (int round = 0; round < numberOfFiles; round++) {
			sendFileNamesAndLength(round);
			sendFile(round);
		}
		server.closeServerSocket();
	}

	private void informClientNumberOfFiles() {
		openDataOutputStream();
		try {
			dos.writeInt(numberOfFiles);
		} catch (IOException e) {
			System.out.println("Server03: 傳送檔案數量資訊有問題：" + e.getMessage());
		}
	}

	private void sendFileNamesAndLength(int round) {
		// 先抓到指定的檔案、大小及名稱
		File file = files[round];
		long fileSize = file.length();
		String fileName = file.getName();
		try {
			// 用DataOutputStream傳送檔案大小及名稱給Client
			dos.writeLong(fileSize);
			dos.writeUTF(fileName);
		} catch (IOException e) {
			System.out.println("Server03: 傳送檔案大小或名稱有問題：" + e.getMessage());
		}
	}

	private void sendFile(int round) {
		File file = files[round];
		long fileSize = file.length();
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] buffer = new byte[8192];
			int readIn = 0;
			while (true) {
				readIn = fis.read(buffer, 0, (int) (Math.min(buffer.length, fileSize)));
				os.write(buffer, 0, readIn);
				fileSize -= readIn;
				if (fileSize <= 0)
					break;
			}
			System.out.println("Server03: 檔案" + file.getName() + "讀取並寫出完畢...");
		} catch (FileNotFoundException e) {
			System.out.println("Server03: 找不到檔案：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Server03: 讀取檔案問題：" + e.getMessage());
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
			if (IP != null) {
				System.out.print("Client03: 開啟OutputStream有問題：");
			} else {
				System.out.print("Server03: 開啟OutputStream有問題：");
			}
			System.out.println(e.getMessage());
		}
	}

	// constructor for client side
	public FileTransporter(String IP, int PORT_NO, String targetFolderName) {
		super();
		this.IP = IP;
		this.setPORT_NO(PORT_NO);
		client = new SocketSetup(IP, PORT_NO);
		socket = client.getSocket();

		this.setFolderName(targetFolderName);
		folder = new File(targetFolderName);
	}

	public void receiveFiles() {
		getNumberOfFilesFromServer();
		for (int round = 0; round < numberOfFiles; round++) {
			String fileName = getFileNameFromServer();
			long fileSize = getFileLengthFromServer();
			// 開始接收並寫入
			wirteFile(fileName, fileSize);
		}
		client.closeClientSocket();
	}

	private void getNumberOfFilesFromServer() {
		openDataInputStream();
		try {
			numberOfFiles = dis.readInt();
		} catch (IOException e) {
			System.out.println("Client03: 讀取檔案數量資訊有問題：" + e.getMessage());
		}
	}

	private String getFileNameFromServer() {
		// 接收檔案名稱
		String fileName = "";
		try {
			fileName = dis.readUTF();
		} catch (IOException e) {
			System.out.println("Client03: 讀取檔案名稱有問題：" + e.getMessage());
		}
		return fileName;
	}

	private long getFileLengthFromServer() {
		// 接收檔案大小
		long fileSize = 0;
		try {
			fileSize = dis.readLong();
		} catch (IOException e) {
			System.out.println("Client03: 讀取檔案大小有問題：" + e.getMessage());
		}
		return fileSize;
	}

	private void wirteFile(String fileName, long fileSize) {
		File file = new File(folder, fileName);
		// 用FileOutputStream寫入檔案
		try (FileOutputStream fos = new FileOutputStream(file);) {
			byte[] buffer = new byte[8192];
			int readIn = 0;
			while (true) {
				readIn = is.read(buffer, 0, (int) (Math.min(buffer.length, fileSize)));
				fos.write(buffer, 0, readIn);
				fileSize -= readIn;
				if (fileSize <= 0)
					break;
			}
			System.out.println("Client03: 檔案" + file.getName() + "讀取並寫入完畢...");
		} catch (IOException e) {
			System.out.println("Client03: 檔案寫入有問題：" + e.getMessage());
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
			if (IP != null) {
				System.out.print("Client03: 開啟InputStream有問題：");
			} else {
				System.out.print("Server03: 開啟InputStream有問題：");
			}
			System.out.println(e.getMessage());
		}
	}

	public int getPORT_NO() {
		return PORT_NO;
	}

	public void setPORT_NO(int PORT_NO) {
		this.PORT_NO = PORT_NO;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
