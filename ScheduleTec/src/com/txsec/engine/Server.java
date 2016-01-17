package com.txsec.engine;

public class Server {

	public static void main(String[] args) {
		String cad = "Diego";
		String nuevaCad = "";
		int life = 3;
		System.out.println("Guess the word");
		for(int i = 0; i < cad.length();i++){
		nuevaCad += " _ ";
		}
		System.out.println(nuevaCad);
		String input = "";
		while(life > 0){
			boolean correct = false;
			int saveNumber = 0;
			input = javax.swing.JOptionPane.showInputDialog("Guess the word");
			if(input.length() == 1){
			for(int i = 0; i < cad.length();i++){
				if(input.equalsIgnoreCase(cad.substring(i, i+1))){
				System.out.println("Correct...");
				saveNumber = i;
				correct = true;
				break;
				} else {
					System.out.println("Incorrect...");
				}
				}
			if(!correct){
				life--;
			} else {
				System.out.println(saveNumber);
				nuevaCad+=input;
			}
			} else {
				System.out.println("Only one character allowed...");
			}
			System.out.println(nuevaCad);
		}
	}

}
