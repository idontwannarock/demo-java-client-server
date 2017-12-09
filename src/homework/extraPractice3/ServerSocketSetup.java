package homework.extraPractice3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketSetup implements SocketSetup {
	int PORT_NO;
	ServerSocket server;
	Socket socket;

	public ServerSocketSetup(int PORT_NO) throws IOException {
		super();
		this.PORT_NO = PORT_NO;
	}
	
	private void listenServerSocket() throws IOException {
		socket = server.accept();
	}

	@Override
	public Socket setupSocket() {
		try {
			server = new ServerSocket(PORT_NO);
			System.out.println("Server: 等待Client端連線");
			listenServerSocket();
		} catch (UnknownHostException e) {
			System.out.println("Server: 無法開啟連線");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Server: 無法開啟連線");
			System.out.println(e.getMessage());
		}
		return socket;
	}

	@Override
	public void closeSocket() {
		try {
			socket.close();
			server.close();
		} catch (IOException e) {
			System.out.println("Server: 關閉連線有問題");
			System.out.println(e.getMessage());
		}
		System.out.println("Server: 與Client已斷線");
	}

}
