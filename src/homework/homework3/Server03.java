package homework.homework3;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
A. 修改Mammal.java程式，將toString()方法內生日資訊由long型
態的birthday，轉換為"yyyy-MM-dd HH:mm:ss"格式的字串。
需要用到SimpleDateFormat類別的format()方法
B. 讀取Client03.java送來的Mammal物件，判斷它的型別，如果
是Cat物件，於螢幕上顯示『Server03: 讀到Cat物件』，如果是
Dog物件，於螢幕上顯示『Server03: 讀到Dog物件』
C. 於螢幕上顯示『Server03: 』與呼叫該物件toString()的結果。
D. 於螢幕上顯示『Server03: 程式結束』
 */

public class Server03 {

	public static void main(String[] args) {
		Mammal m = null;
		try (ServerSocket server = new ServerSocket(54321);) {
			System.out.println("Server03: 等待連線");
			try (Socket socket = server.accept();
					InputStream is = socket.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);) {
				m = (Mammal) ois.readObject();
				if (m instanceof Cat) {
					System.out.println("Server03: 讀到Cat物件");
				} else if (m instanceof Dog) {
					System.out.println("Server03: 讀到Dog物件");
				}
				System.out.println("Server03: " + m.toString());
				System.out.println("Server03: 程式結束");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
