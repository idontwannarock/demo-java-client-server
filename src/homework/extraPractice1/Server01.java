package homework.extraPractice1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
1.Server產生兩個介於500000.0-1000000.0的浮點數
2.Server將此二浮點數送給Client
3.Client讀取此二浮點數，計算和、差、積、商
4.Client將上述5個資料裝入一個容器物件內，寫回給Server
5.Server讀取此物件，然後顯示物件內的資料
*/

public class Server01 {

	public static void main(String[] args) {
		// 產生兩個5000-10000間的隨機亂數
		double randomNumber1 = Math.random() * 500001 + 500000;
		double randomNumber2 = Math.random() * 500001 + 500000;
		
		try {
			// 產生連線
			ServerSocket server = new ServerSocket(54321);
			System.out.println("Server: 等待連線請求");
			Socket socket = server.accept();
			System.out.println("Server: 遠方電腦已連線成功");
			// 建立讀寫物件is跟os
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// 要傳送不介於0-255之間的數字，需用DataOutputStream傳送
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeDouble(randomNumber1);
			dos.writeDouble(randomNumber2);
			System.out.println("Server: 已送出兩個亂數浮點數 " + randomNumber1 + " 及 " + randomNumber2);
			
			// 接收client計算完回傳物件
			ObjectInputStream ois = new ObjectInputStream(is);
			DataContainer dc = (DataContainer) ois.readObject();
			System.out.println("Server: 計算結果物件已接收");
			System.out.println("Server: 兩浮點數和為 " + dc.sum.toString());
			System.out.println("Server: 兩浮點數差為 " + dc.sub.toString());
			System.out.println("Server: 兩浮點數積為 " + dc.pro.toString());
			System.out.println("Server: 兩浮點數商為 " + dc.div.toString());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
