package net.urlConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTWSEInfo {

	public static void main(String[] args) {
//		String surl = "http://www.twse.com.tw/fund/T86?response=json&date=20171207&selectType=ALL&_=1512703576442\";\n";
		String surl = "https://tw.yahoo.com";
		try {
			URL url = new URL(surl);
			// 與遠方Server建立http連線，以取得URLConnection物件
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 設定是否要讀取或寫出資料
			con.setDoOutput(false);
			con.setDoInput(true); // 預設即為true，表示可以讀取資料，可以取得InputStream
			// 設定表頭資訊
//			con.setRequestProperty("Host", "www.twse.com.tw");
//			con.setRequestProperty("Connection", "keep-alive");
//			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
//			con.setRequestProperty("Referer", "http://www.twse.com.tw/zh/page/trading/fund/T86.html");
//			con.setRequestProperty("Cookie", "JSESSIONID=AD87C439368C8914D5446895D9E5503D; _ga=GA1.3.2090571582.1512703358; _gid=GA1.3.2027106774.1512703358; _gat=1");
			// 設定http方法
			con.setRequestMethod("GET");
			// 開始取資料
			InputStream is = con.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				System.out.println("Server端程式送回的資料 => " + decodedString);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
