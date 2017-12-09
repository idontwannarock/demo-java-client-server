package net._01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket01 {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			ServerSocket server = new ServerSocket(54321);
			System.out.println("Server: 準備接受遠方電腦連線請求");
			Socket socket = server.accept();
			System.out.println("Server: 遠方電腦已連線成功");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			int n = 12345, m = 8000;
			dos.writeInt(n);
			dos.writeInt(m);
			System.out.println("Server: 已送出整數1 " + n);
			System.out.println("Server: 已送出整數2 " + m);
			
			ObjectInputStream ois = new ObjectInputStream(is);
			DataContainer dc = (DataContainer) ois.readObject();
			System.out.println("Server: 已讀Client送回整數1跟2的和" + dc.sum);
			System.out.println("Server: 已讀Client送回整數1跟2的差" + dc.sub);
			System.out.println("Server: 已讀Client送回整數1跟2的積" + dc.pro);
			System.out.println("Server: 已讀Client送回整數1跟2的商" + dc.div);
			System.out.println("Server: 已讀Client送回整數1跟2的餘數" + dc.mod);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("請按任意鍵繼續");
			br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
