package homework.homework4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
A. 定義一個字串陣列如下：
String[] sa = {"MLB/王建民登板 球速飆到150公里",
"中心打線發威貢獻6分-皇家拿下2連勝",
"MLB/王建民登板 球速飆到150公里",
"光芒大破紅襪 結束11連敗"};
B. 隨機取出字串陣列中的一個字串，於螢幕上顯示
『Client04: 隨機字串為XXXXXXXXXXX』
C. 將此字串透過網路送達到Server端，由Server04.java讀取
D. 於螢幕上顯示
『Client04: 程式結束』
 */

public class Client04 {

	public static void main(String[] args) {
		String[] sa = {"MLB/王建民登板 球速飆到150公里",
				"中心打線發威貢獻6分-皇家拿下2連勝",
				"MLB/王建民登板 球速飆到150公里",
				"光芒大破紅襪 結束11連敗"};
		int randomIndex = (int) (Math.random() * sa.length);
		String randomString = sa[randomIndex];
		System.out.println("Client04: 隨機字串為 " + randomString);
		try (Socket socket = new Socket("127.0.0.1", 54321);) {
			System.out.println("Client04: 連線成功");
			try (OutputStream os = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
					BufferedWriter bw = new BufferedWriter(osw);) {
				bw.write(randomString);
				System.out.println("Client04: 程式結束");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
