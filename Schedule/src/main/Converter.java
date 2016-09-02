package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Converter {

	private static String[] letters = { "M", "D", "C", "L", "X", "V", "I" };	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, String> alienToRoman= new HashMap<String, String> ();
		
		try {
			processInput(args[1]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}


	private static void processInput(String path) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f = new File (path);
		Scanner sc = new Scanner (f);
		String patternRoman="[IVXLCMD]";
		HashMap<String, String> alienToRoman= new HashMap<String, String> ();
		
		while (sc.hasNextLine()){

			String line= sc.next();
			//If it's a Alien to Roman conversion, we add it to the conversion HashMap
			if (line.matches("\\w*\\sis\\s[IVX]")){
				String[] conversion = line.split(" ");
				alienToRoman.put(conversion[0], conversion[2]);
			}
			
			//If it's the value of a Metal
			if (line.matches(".*is\\s(\\d+\\s)+[Cc]redits")){}
			
			//If it's translation from alien to Roman
			if (line.matches("how\\smuch\\sis\\s([a-zA-Z]+\\s)*\\?")){}
			
			//If it's about the price of a quantity of Metal
			if (line.matches("how\\smany\\sCredits\\sis\\s([a-zA-Z]+\\s)*\\?")){}
			
			else System.out.println("I have no idea what you are talking about");
			
		}
		sc.close();
	}

}
