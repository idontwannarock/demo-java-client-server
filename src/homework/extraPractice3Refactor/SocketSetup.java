package homework.extraPractice3Refactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketSetup {
	private String IP;
	private int PORT_NO;
	private ServerSocket server;
	private Socket socket;
	
	// constructor for server side
	public SocketSetup(int PORT_NO) {
		super();
		this.PORT_NO = PORT_NO;
		try {
			this.server = new ServerSocket(PORT_NO);
			System.out.println("Server03: 等待Client端連線");
			this.socket = server.accept();
		} catch (IOException e) {
			System.out.println("Server03: 建立連線失敗，錯誤訊息：" + e.getMessage());
		}
		System.out.println("Server03: 已建立與Client端連線");
	}

	// constructor for client side
	public SocketSetup(String IP, int PORT_NO) {
		super();
		this.IP = IP;
		this.PORT_NO = PORT_NO;
		try {
			this.socket = new Socket(IP, PORT_NO);
		} catch (UnknownHostException e) {
			System.out.println("Client03: 連線Server端失敗，錯誤訊息：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Client03: 連線Server端失敗，錯誤訊息：" + e.getMessage());
		}
		System.out.println("Client03: 已建立與Server端連線");
	}
	
	public void closeServerSocket() {
		try {
			this.socket.close();
			this.server.close();
		} catch (IOException e) {
			System.out.println("Server03: 關閉Socket失敗，錯誤訊息：" + e.getMessage());
		}
		System.out.println("Server03: 已成功關閉連線");
	}
	
	public void closeClientSocket() {
		try {
			this.socket.close();
		} catch (IOException e) {
			System.out.println("Client03: 關閉Socket失敗，錯誤訊息：" + e.getMessage());
		}
		System.out.println("Client03: 已成功關閉連線");
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public int getPORT_NO() {
		return PORT_NO;
	}

	public void setPORT_NO(int PORT_NO) {
		this.PORT_NO = PORT_NO;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
