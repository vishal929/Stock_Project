//importing logger


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


//this is a class to basically run the app

public class Driver{

public static void main(String[] args){
	Logger logger = LogManager.getLogger("logger");
	Scanner reader = new Scanner(System.in);

	logger.info("Initializing the project and welcoming the user");

	System.out.println("Hello! Welcome to my Stock Project!);

	while(true){
		System.out.println("Please select an option below:");
		System.out.println("1) Get Stock Data");
		System.out.println("2) Run analysis on Stock Data");
		String option = reader.nextLine().trim();
		if (option.equals("1")){
			//then the user wants to collect stock data
			System.out.println("Please enter the ticker and then enter the year!");
			String ticker = reader.nextLine().trim().toUpperCase();
			String year = reader.nextLine().trim();
			logger.info("Initializing process to get stock data, if it does not already exist.");
			if (stockData(ticker,year)){
				//then data was successfully written
			} else {
				//then something fatal happened and we needed to abort the process
			}
		} else if (option.equals("2")){
			//then the user wants to run an analysis on some stock data
			System.out.println("Please enter the ticker below!");
			String ticker = reader.nextLine().trim().toUpperCase();
			logger.info("Initializing process to run an analysis of stock data, if it exists.");
			analysis(ticker);	
		} else {
			//then we cannot comprehend what the user wrote
			System.out.println("I am sorry, I did not catch that! Please enter your choice again with either 1 or 2");
			logger.error("Could not comprehend the user's option in the driver! The user entered---->"+option);
		}
	}
}


	public static boolean stockData(String ticker, int year){
		//this will just run the parseEdgar getstockdata function
	}

	public static void analysis(String ticker){
		//will make use of library functions
	}


}
