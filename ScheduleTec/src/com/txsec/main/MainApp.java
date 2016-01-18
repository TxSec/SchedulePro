package com.txsec.main;

import com.txsec.engine.Engine;
import com.txsec.util.FileUtil;

public class MainApp {

	public static void main(String[] args) {
		Engine engine = new Engine();
		engine.start();
		engine.startGraphics();
		//Hola
	}

}
