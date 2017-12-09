package homework.homework1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
A. 隨機產生兩個介於1-100的亂數，並於螢幕上顯示這兩個亂數：
『Client01: 兩個隨機亂數為 X, Y』
B. 將這兩個亂數透過網路送達到Server端，由Server01.java讀取
C. 依序接收Server01.java送回的和(sum)、差(sub)、積(mul)、
商(div)等四個數字，並將收到的數字顯示於螢幕上：
『Client01: 兩個亂數的和: sum』
『Client01: 兩個亂數的差: sub』
『Client01: 兩個亂數的積: mul』
『Client01: 兩個亂數的商: div』
D. 顯示
『Client01: 程式結束』
 */

public class Client01 {

	public static void main(String[] args) {
		// 宣告接收Server回傳和、差、積、商的變數
		int sum, sub, mul;
		double div;
		// 產生兩個亂數整數
		int firstNumber = (int) (Math.random() * 100) + 1;
		int secondNumber = (int) (Math.random() * 100) + 1;
		System.out.println("Client01: 兩個隨機亂數為 " + firstNumber + ", " + secondNumber);
		// 開啟與Server連線
		try (Socket socket = new Socket("127.0.0.1", 54321);
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);) {
			// 透過DataOutputStream的方法傳送給OutputStream
			dos.writeInt(firstNumber);
			dos.writeInt(secondNumber);
			// 接收Server回傳的四個計算結果
			sum = dis.readInt();
			sub = dis.readInt();
			mul = dis.readInt();
			div = dis.readDouble();
			System.out.println("Client01: 兩個亂數的和: " + sum);
			System.out.println("Client01: 兩個亂數的差: " + sub);
			System.out.println("Client01: 兩個亂數的積: " + mul);
			System.out.println("Client01: 兩個亂數的商: " + div);
			System.out.println("Client01: 程式結束");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
