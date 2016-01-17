package com.txsec.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.txsec.main.Settings;

/**
 * Autor TxSec
 */
public class FileUtil {
	
	public static void createDirectory(){
		if(!pathExists()){
			try {
				Files.createDirectories(Settings.confDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static boolean pathExists(){
		if(Files.exists(Settings.confDir)){
			return true;
		}
		return false;
	}
	
	public static boolean fileExists(String filename){
		Path path = Paths.get(Settings.confDir+"\\"+filename);
		if(Files.exists(path)){
			return true;
		}
		return false;
	}
	
	public static void fileCreate(String fileName){
		File file = new File(Settings.pathString+"\\"+fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	public static void writeFile(String fileName,byte[] message) {
		if(fileExists(fileName)){
			Path path = Paths.get(Settings.confDir+"/"+fileName);
			try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path,StandardOpenOption.CREATE,StandardOpenOption.APPEND))){
				out.write(message, 0, message.length);
				out.write("\r\n".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			//Create the file...
			fileCreate(fileName);
			//Write to it...
			writeFile(fileName,message);
		}
		
	}
	
	public static String[] readAllFiles(){
		String info = "";
		StringBuilder build = new StringBuilder();
		String array[] = null;
		Path path = Paths.get(Settings.confDir+"/");
		File f = new File(path.toString());
		File[] files = f.listFiles();
		for(int i = 0; i < files.length;i++){
			path = Paths.get(Settings.confDir+"/"+files[i].getName());
			if(fileExists(files[i].getName())){
				byte[] buffer = new byte[1024];
				char c;
				try (InputStream in = new BufferedInputStream(Files.newInputStream(path))){
					in.read(buffer);
					for(int j = 0; j < buffer.length;j++){
					c = (char)buffer[j];
					if(isLetterOrDigit(c)){
					build.append(c);
					}
					}
					info = build.toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
				array = info.split("\\*|\\r?\\n");
				}
		}
		return array;
	}
	
	private static boolean isLetterOrDigit(char c) {
	    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||   (c >= '0' && c <= '9') ||  (c == ' ')  || (c == '-')  || (c == '*')
	    		 || (c == ':') || (c == '\n') || (c == '\r');
	}
	
	
	public static String[] readFile(String fileName){
		String info = "";
		StringBuilder build = new StringBuilder();
		String array[] = null;
		if(fileExists(fileName)){
		Path path = Paths.get(Settings.confDir+"/"+fileName);
		byte[] buffer = new byte[1024];
		char c;
		try (InputStream in = new BufferedInputStream(Files.newInputStream(path))){
			in.read(buffer);
			for(int i = 0; i < buffer.length;i++){
			c = (char)buffer[i];
			build.append(c);
			}
			info = build.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		array = info.split("\\*|\\r?\\n");
		} else{
			System.out.println("Not found");
			
		}
		return array;
	}

}
