package com.txsec.main;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class to save all the program settings.
 * @author TX-SEC.
 *
 */
public class Settings {
	
	
	public static final String TITLE = "SchedulePro";
	public static final String DIRECTORY = "SchedulePro";
	/**
	 * Directory for our Schedule files(Local Mode - NOT DB).
	 */
	public static final String pathString = System.getProperty("user.home")+"\\."+DIRECTORY;
	public static final Path confDir = Paths.get(System.getProperty("user.home")+"\\."+DIRECTORY);
	public static final double VERSION = 0.3;
	public static final String WEBSITE = "http://localhost:8088/schedulepro/file";

}
