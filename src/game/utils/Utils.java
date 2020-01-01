package game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	
	public static String loadFileAsString(String path) {

		StringBuilder builder = new StringBuilder(); //allows to add characters to string very easily
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			
			while((line =br.readLine()) != null)
				builder.append(line + "\n"); //after every line in the file create a new line
			br.close();
			
		}catch(IOException e) { //print the error if there is one
			e.printStackTrace();
		}
		
		return builder.toString(); //converting everything to string and returning it
		
	}

	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) { //in case of a letter
			e.printStackTrace();
			return 0;
		}
	}
	
}
