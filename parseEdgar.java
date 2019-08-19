//purpose of this class is just to parse edgar for a certain stock right now;

import java.io.*;
import java.util.Scanner;

public class parseEdgar{
	 
	public static void main(String[] args) {

	
	//asking user for stock ticker;

	Scanner reader = new Scanner(System.in);

	System.out.println("Please enter the ticker!");


	String ticker = reader.nextLine().toUpperCase();

	System.out.println(ticker);
	
	}



}
