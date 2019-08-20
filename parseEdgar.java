//purpose of this class is just to parse edgar for a certain stock right now;
//eventually I want to store 5 years worth of stock data in yaml files for each stock with the year object and 
//financial data as children of that year
//
//IMPLEMENT LOGGING AND USE JSOUP

import java.io.*;
import java.util.Scanner;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class parseEdgar{
	 
	public static void main(String[] args) {

	
		//asking user for stock ticker;

		Scanner reader = new Scanner(System.in);

		System.out.println("Please enter the ticker!");


		String ticker = reader.nextLine().toUpperCase();

		System.out.println(ticker);

		//establishing url to edgar page with data to parse
		//every stock traded on an exchange has a CIK identifier
		//we will try to get the CIK now 
	
		try {
			URL info = new URL("https://www.sec.gov/cgi-bin/browse-edgar?CIK="+ticker);
			HttpsURLConnection stockSearch= (HttpsURLConnection) info.openConnection();

			BufferedReader read = new BufferedReader(new InputStreamReader(stockSearch.getInputStream()));
			String toRead = read.readLine();

			while (toRead!=null) {
				System.out.println(toRead);
				toRead = read.readLine();
			}

			stockSearch.disconnect();






	
		} catch (Exception e) {
			System.out.println("hi");
		}

	}



}
