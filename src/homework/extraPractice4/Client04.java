package homework.extraPractice4;

/*
分散式運算一千萬階乘
Client端分解階乘後再分別傳送到多個Server端
Server端接收後計算完，再回傳Client
Client再將所有接收到的答案相乘
 */

public class Client04 {
	static final int numberOfServer = 5;
	static ClientConnector clientConnector;

	public static void main(String[] args) {
		clientConnector = new ClientConnector(numberOfServer);
	}

}
