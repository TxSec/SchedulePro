package com.txsec.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpSend {
	
	private static URL url;
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String args[]) throws IOException{
		try {
			String data = "username=&password=&rememberusername=1";
			url = new URL("http://siveduc2.ittepic.edu.mx");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("User-Agent", USER_AGENT);
			http.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			http.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(http.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			String read;
			while((read = reader.readLine()) != null){
				System.out.println(read);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
