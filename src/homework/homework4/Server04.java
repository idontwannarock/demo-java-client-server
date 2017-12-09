package homework.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
A. 讀取Client04.java送來的字串
B. 於螢幕上顯示此字串
『Server04: 客戶端送來的字串為XXXXXXXXXX』
C. 於螢幕上顯示
『Server04: 程式結束』
 */

public class Server04 {

	public static void main(String[] args) {
		String receivedString;
		try (ServerSocket server = new ServerSocket(54321);) {
			System.out.println("Server04: 等待連線");
			try (Socket socket = server.accept();
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				receivedString = br.readLine();
				System.out.println("Server04: 客戶端送來的字串為 " + receivedString);
				System.out.println("Server04: 程式結束");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
