package homework.extraPractice1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;

public class Client01 {

	public static void main(String[] args) {
		// 宣告接收兩個浮點數的BigDecimal物件
		BigDecimal bd1, bd2;
		// 宣告存取和、差、積、商結果的BigDecimal物件
		BigDecimal sum, sub, pro, div;
		try {
			// 建立連線
			Socket socket = new Socket("127.0.0.1", 54321);
			System.out.println("Client: 建立連線成功");
			// 建立讀寫物件is跟os
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// 接收不介於0-255間的整數，需用DataInputStream
			DataInputStream dis = new DataInputStream(is);
			bd1 = new BigDecimal(dis.readDouble());
			bd2 = new BigDecimal(dis.readDouble());
			System.out.println("Client: 兩亂數浮點數已接收完畢，開始計算和、差、積、商");
			// 開始計算和、差、積、商、餘數
			sum = bd1.add(bd2);
			sub = bd1.subtract(bd2);
			pro = bd1.multiply(bd2);
			div = bd1.divide(bd2, 8, BigDecimal.ROUND_HALF_UP);
			// 建立物件存四個BigDecimal結果物件
			ObjectOutputStream oos = new ObjectOutputStream(os);
			DataContainer dc = new DataContainer(sum, sub, pro, div);
			oos.writeObject(dc);
			System.out.println("Client: 計算完成，結果已傳送");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
