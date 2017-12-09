package homework.homework3;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
A. 將"2010-12-20 18:27:36"轉換為long型態的整數：ins.
B. 隨機產生1個介於1-10的亂數，如果此數為偶數，產生一個Cat
物件，Cat c = new Cat("Kitty", ins);
並於螢幕上顯示『Client03: 產生Cat物件』
如果此數為奇數，產生一個Dog物件
Dog d = new Dog("Snoopy", ins);
並於螢幕上顯示『Client03: 產生Dog物件』
C. 將此物件寫出，透過網路送達到Server端，由Server03.java讀取
D. 顯示『Client03: 程式結束』
 */

public class Client03 {

	public static void main(String[] args) throws ParseException {
		// 轉換日期到long ins
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		Date date = sdf.parse("2010-12-20 18:27:36");
		long ins = date.getTime();
		// 隨機產生一個亂數
		int randomIndicator = (int) (Math.random() * 10) + 1;
		Mammal c = null, d = null;
		// 判斷亂數為偶數或奇數來決定要產生Cat或Dog物件
		if ((randomIndicator % 2) == 0) {
			c = new Cat("Kitty", ins);
			System.out.println("Client03: 產生Cat物件");
		} else {
			d = new Dog("Snoopy", ins);
			System.out.println("Client03: 產生Dog物件");
		}
		// 將產生的物件傳送給Server
		try (Socket socket = new Socket("127.0.0.1", 54321);
				OutputStream is = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(is);) {
			if ((randomIndicator % 2) == 0) {
				oos.writeObject(c);
			} else {
				oos.writeObject(d);
			}
			System.out.println("Client03: 程式結束");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
