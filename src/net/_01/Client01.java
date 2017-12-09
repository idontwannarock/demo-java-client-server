package net._01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client01 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 54321);
			System.out.println("Client: 連線成功");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// 因InputStream跟OutputStream只處理0-255
			// 所以要透過DataInputStream/DataOutputStream處理
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			int n = 0, m =0;
			n = dis.readInt();
			m = dis.readInt();
			System.out.println("Client: 接收到Server送來的整數1 " + n);
			System.out.println("Client: 接收到Server送來的整數2 " + m);
			
			int sum = n + m;
			int sub = n - m;
			int pro = n * m;
			int div = n / m;
			int mod = n % m;
			// 裝起來一次傳，不用每個結果都用DataOutputStream分別傳
			// 但要傳送物件要記得讓物件的類別實作Serializable介面
			DataContainer dc = new DataContainer(sum, sub, pro, div, mod);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(dc);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("請按任意鍵繼續");
			br.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
