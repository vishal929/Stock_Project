//importing logger


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;


//this is a class to basically run the app
//TO IMPLEMENT: IF USER DOES NOT SPECIFY A YEAR TO GATHER DATA, THEN I WILL GATHER ALL DATA IN XML FORMAT
//IF USER DOES NOT SPECIFY A YEAR TO ANALYZE DATA, THEN I WILL ANALYZE ALL DATA INTO ONE ANALYSIS FILE IN THE TICKERS FOLDER WHERE SIMILAR RATIOS ARE ALL TOGETHER SORTED BY YEAR

public class Driver{

public static void main(String[] args) throws IOException,IllegalAccessException,NoSuchMethodException,InvocationTargetException{
	Logger logger = LogManager.getLogger("logger");
	Scanner reader = new Scanner(System.in);

	logger.info("Initializing the project and welcoming the user");

	System.out.println("Hello! Welcome to my Stock Project!");

	while(true){
		System.out.println("Please select an option below:");
		System.out.println("1) Get Stock Data");
		System.out.println("2) Run analysis on Stock Data");
		System.out.println("9) Exit");
		String option = reader.nextLine().trim();
		if (option.equals("1")){
			//then the user wants to collect stock data
			System.out.println("Please enter the ticker and then enter the year!");
			String ticker = reader.nextLine().trim().toUpperCase();
			String yearInput = reader.nextLine().trim();
			int year = Integer.parseInt(yearInput);	
			logger.info("Initializing process to get stock data, if it does not already exist.");
			boolean[] exists = new boolean[1];
			if (stockData(ticker,year,exists)){
				//then data was successfully written
				System.out.println("Data has been written to the file successfully!");
				logger.info("Successfully completed users request to gather stock data");
			} else {
				//then something fatal happened and we needed to abort the process
				if (exists[0]){
					//then the data we are looking for exists
					System.out.println("The data you are looking for already exists!");
				} else {
					//then the data does not exist or there was an error creating files or connecting to the internet
					System.out.println("Please check the ticker,year, storage space, and internet connection. There was an error in gathering the data, so either the ticker+year combination is invalid, storage space is limited so the file cannot be created, or connection to Edgar could not be established!");
				}
				logger.info("Finished user's request to gather stock data and the data exists or an error occurred.");
			}
		} else if (option.equals("2")){
			//then the user wants to run an analysis on some stock data
			System.out.println("Please enter the ticker below!");
			String ticker = reader.nextLine().trim().toUpperCase();
			logger.info("Initializing process to run an analysis of stock data, if it exists.");
			analysis(ticker);	
		} else if (option.equals("9")){
			System.out.println("Understood! Ending project process...");
			logger.info("Ending project service on user's request.");
			break;
		
		} else {
			//then we cannot comprehend what the user wrote
			System.out.println("I am sorry, I did not catch that! Please enter your choice again with either 1,2 or 9");
			logger.error("Could not comprehend the user's option in the driver! The user entered---->"+option);
		}
	}
}


	public static boolean stockData(String ticker, int year, boolean[] exists) throws IOException,IllegalAccessException,NoSuchMethodException,InvocationTargetException{
		//this will just run the parseEdgar getstockdata function
		return parseEdgar.download(ticker,year,exists);
	}

	public static void analysis(String ticker) throws IOException{
		//will make use of library functions
		//first we will load the companyFile for the year requested
		Logger logger = LogManager.getLogger("logger");
		Scanner reader = new Scanner(System.in);
		//we will just test the quick ratio calculation below
		logger.info("User has requested an analysis of the ticker: "+ticker);
		System.out.println("Please enter the year of filing you wish to calculate the quick ratio for!");
		String year = reader.nextLine().trim();
		String potentialFile ="companyFilings/"+ticker+"/"+ticker+year+".yml"; 
		logger.info("User wants an analysis for the year: "+year);
		File yamlFile = new File(potentialFile);
		System.out.println("Absolute Path:"+yamlFile.getAbsolutePath());
		if (yamlFile.exists()){
			CompanyFile statement = logicYAML.load(yamlFile);
			System.out.println("Quick ratio: "+ AnalysisLibrary.getQuickRatio(statement));
			logger.info("Successfully retrieved analysis for the user!");
		} else {
			logger.fatal("Either the ticker or the year is invalid or no data can be found! Aborting the process!");
			System.out.println("Make sure this data exists! Could not retrieve data to run analysis, please check the data or gather the data if it has not been gathered yet!");
		}
		
	}


}
