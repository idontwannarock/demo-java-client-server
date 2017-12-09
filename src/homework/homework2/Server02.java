package homework.homework2;

import java.io.DataInputStream;
//import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
A. 接收Client02.java送來的N個亂數，並於螢幕上顯示這些亂數：
『Server02: 收到的亂數: X1, X2,..., XN』
B. 計算這N個亂數的個數、最大、最小、總和、平均，並顯示於螢幕上：
『Server02: 亂數的個數: num』
『Server02: 最大的亂數: max』
『Server02: 最小的亂數: min』
『Server02: 亂數的總和: sum』
『Server02: 亂數的平均: avg』
C. 將這五個計算出來的數字封裝在DataBean物件內，然後透過網路將此物件寫回給Client端
D. 於螢幕上顯示
『Server02: 程式結束』
 */

public class Server02 {

	public static void main(String[] args) {
		// 宣告個數、最大、最小、總和、平均的變數
		int num = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, sum = 0;
		double avg;
		try {
			ServerSocket server = new ServerSocket(54321);
			System.out.println("Server02: 等待連線");
			Socket socket = server.accept();
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			// 用ArrayList接收亂數
			List<Integer> randoms = new ArrayList<>();
			while (true) {
				try {
					randoms.add(dis.readInt());
				} catch (Exception e) {
					socket.shutdownInput();
					break;
				}
			}
			System.out.print("Server02: 接收到的亂數為 ");
			for (int i : randoms) {
				System.out.print(i + " ");
			}
			System.out.println("");
			// 計算亂數的個數、最大、最小、總和、平均
			num = randoms.size();
			for (int i : randoms) {
				num++;
				int currentNumber = i;
				if (currentNumber >= max) {
					max = currentNumber;
				}
				if (currentNumber <= min) {
					min = currentNumber;
				}
				sum += currentNumber;
			}
			avg = (double) sum / num;
			System.out.println("Server02: 亂數的個數: " + num);
			System.out.println("Server02: 最大的亂數: " + max);
			System.out.println("Server02: 最大的亂數: " + min);
			System.out.println("Server02: 亂數的總和: " + sum);
			System.out.println("Server02: 亂數的平均: " + avg);
			// 建立DataBean物件，並回傳給Client
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			DataBean db = new DataBean(num, max, min, sum, avg);
			oos.writeObject(db);
			System.out.println("Server02: 程式結束");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
