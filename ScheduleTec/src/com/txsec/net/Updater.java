package com.txsec.net;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.txsec.main.Settings;


public class Updater {
	
	private HttpURLConnection http;
	private URL url;
	
	public Updater(String weburl){
		try{
		url = new URL(weburl);
		//Connect to the site to grab the update :)
		http = (HttpURLConnection) url.openConnection();
				 http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
                 http.setRequestProperty("Accept-Lenguage", "en-us,en;q=0.5");
                 http.setDoOutput(true);
				BufferedReader buffer = new BufferedReader(new InputStreamReader(http.getInputStream()));
				double version = Double.parseDouble(buffer.readLine());
				//We check if version Matches our current client version.
				if(Settings.VERSION != version){
					launcher();
					//May move soon...
					//javax.swing.JOptionPane.showMessageDialog(null,"New Version Found, Want to update?");
				}
			} catch (IOException e) {
			System.out.println("No hay conexion al servidor web...");
		}
	}
	
	public void launcher(){
		try {
			//Grab the current file...
			url = new URL(Settings.WEBSITE+"/Schedule.jar");
			http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
            http.setRequestProperty("Accept-Lenguage", "en-us,en;q=0.5");
            http.setDoOutput(true);
            InputStream stream = new BufferedInputStream(http.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=stream.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			byte[] response = out.toByteArray();
			File f = new File(System.getProperty("user.home")+"\\Desktop\\Schedule.jar");
			FileOutputStream fileStream = new FileOutputStream(f);
			fileStream.write(response);
			fileStream.close();
            //Execute the new program updater.
			String path = System.getProperty("user.home")+"/Desktop/Schedule.jar";
			String[] command = {
			     "java", 
			     "-jar",
			     path
			};
			ProcessBuilder pb = new ProcessBuilder(command);
			Process p = pb.start();
			//Close the non-updated program.
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
