package homework.extraPractice3;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketSetup implements SocketSetup {
	String IP;
	int PORT_NO;
	Socket socket;
	
	public ClientSocketSetup(String IP, int PORT_NO) {
		super();
		this.IP = IP;
		this.PORT_NO = PORT_NO;
	}

	@Override
	public Socket setupSocket() {
		try {
			socket = new Socket(IP, PORT_NO);
			System.out.println("Client: 已連上Server，準備接收檔案");
		} catch (UnknownHostException e) {
			System.out.println("Client: 無法開啟連線");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Client: 無法開啟連線");
			System.out.println(e.getMessage());
		}
		return socket;
	}
	
	@Override
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Client: 關閉連線有問題");
			System.out.println(e.getMessage());
		}
		System.out.println("Client: 與Server已斷線");
	}
	
}
