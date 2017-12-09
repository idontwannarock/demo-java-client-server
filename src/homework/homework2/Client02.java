package homework.homework2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
A. 隨機產生N個介於1-100的亂數，N為介於5至10的亂數，即5 <= N <= 10
並於螢幕上顯示這些亂數：
『Client02: N個隨機亂數為 X1, X2,..., XN』
B. 將這N個亂數透過網路送達到Server端，由Server02.java讀取
(沒有規定要如何傳送這N個亂數)
C. 接收Server02.java送回的DataBean類別的物件，此類別有五個屬性，並顯示於螢幕上
『Client02: 亂數的個數: num』
『Client02: 最大的亂數: max』
『Client02: 最小的亂數: min』
『Client02: 亂數的總和: sum』
『Client02: 亂數的平均: avg』
D. 顯示
『Client02: 程式結束』
 */

public class Client02 {

	public static void main(String[] args) {
		// 宣告接收物件及個數、最大、最小、總和、平均的變數
		DataBean db = new DataBean();
		int num, max, min, sum;
		double avg;
		// 先亂數產生個數
		int N = (int) (Math.random() * 6) + 5;
		// 再建立儲存亂數的陣列，並用迴圈產生亂數存進陣列
		int[] randoms = new int[N];
		for (int i = 0; i < N; i++) {
			randoms[i] = (int) (Math.random() * 100) + 1;
		}
		System.out.print("Client02: 亂數為: ");
		for (int i : randoms) {
			System.out.print(i + " ");
		}
		System.out.println("");
		// 接著建立與Server連線
		try {
			Socket socket = new Socket("127.0.0.1", 54321);
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			// 將亂數傳給Server
			for (int i = 0; i < N; i++) {
				dos.writeInt(randoms[i]);
			}
			System.out.println("Client02: 傳送成功");
			socket.shutdownOutput();
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			// 接收Client回傳物件
			db = (DataBean) ois.readObject();
			num = db.getNum();
			max = db.getMax();
			min = db.getMin();
			sum = db.getSum();
			avg = db.getAvg();
			System.out.println("Client02: 亂數的個數: " + num);
			System.out.println("Client02: 最大的亂數: " + max);
			System.out.println("Client02: 最大的亂數: " + min);
			System.out.println("Client02: 亂數的總和: " + sum);
			System.out.println("Client02: 亂數的平均: " + avg);
			System.out.println("Client02: 程式結束");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}