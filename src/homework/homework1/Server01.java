package homework.homework1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
A. 接收Client01.java送來的兩個亂數，並於螢幕上顯示這兩個亂數：
『Server01: 兩個亂數: X, Y』
B. 計算這兩個亂數和、差、積、商，並顯示於螢幕上：
『Server01: 兩個亂數的和: sum』
『Server01: 兩個亂數的差: sub』
『Server01: 兩個亂數的積: mul』
『Server01: 兩個亂數的商: div』
C. 透過網路將這四個計算出來的數字送回到Client端，然後顯示
『Server01: 程式結束』
 */

public class Server01 {

	public static void main(String[] args) {
		// 設定接收亂數的變數及計算結果的變數
		int firstNumber, secondNumber;
		int sum, sub, mul;
		double div;
		// 設定ServerSocket
		try (ServerSocket server = new ServerSocket(54321);) {
			System.out.println("Server01: 等待連線");
			// 開啟Socket以及連線InputStream
			try (Socket socket = server.accept();
					InputStream is = socket.getInputStream();
					DataInputStream dis = new DataInputStream(is);
					OutputStream os = socket.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);) {
				// 透過DataInputStream的方法readInt()從InputStream讀取Client傳過來的兩個亂數
				firstNumber = dis.readInt();
				secondNumber = dis.readInt();
				System.out.println("Server01: 兩個亂數: " + firstNumber + ", " + secondNumber);
				// 計算和、差、積、商
				sum = firstNumber + secondNumber;
				sub = firstNumber - secondNumber;
				mul = firstNumber * secondNumber;
				div = (double)firstNumber / (double)secondNumber;
				System.out.println("Server01: 兩個亂數的和: " + sum);
				System.out.println("Server01: 兩個亂數的差: " + sub);
				System.out.println("Server01: 兩個亂數的積: " + mul);
				System.out.println("Server01: 兩個亂數的商: " + div);
				// 將和、差、積、商回傳
				dos.writeInt(sum);
				dos.writeInt(sub);
				dos.writeInt(mul);
				dos.writeDouble(div);
				System.out.println("Server01: 程式結束");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
