//purpose of this class is just to parse edgar for a certain stock right now;
//eventually I want to store 5 years worth of stock data in yaml files for each stock with the year object and 
//financial data as children of that year
//
//IMPLEMENT LOGGING AND USE JSOUP
//
//Dependencies:
//logging framework: log42j
//HTML parser: JSOUP
//YAML parser: SnakeYAML



//IDEA: include Binary Search Tree or hash map of tickers that we have already added to some file so we can quickly check the data if the user chooses

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Scanner;
import java.util.Calendar;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class parseEdgar{

	/*
	public class Stock{
		private int year;
		private 
	} 
	*/
	 
	public static void main(String[] args) {
		//the idea is to create a stock object with a bunch of methods and then write and pull the objects via serialization to and from .yaml files and all the processing of information is done in java

		//initializing logger
		Logger logger = LogManager.getLogger(parseEdgar.class);
	
		//asking user for stock ticker;

		Scanner reader = new Scanner(System.in);

		System.out.println("Please enter the ticker!");


		String ticker = reader.nextLine().toUpperCase();

		System.out.println(ticker);

		download(ticker,logger);


		
	}

	//function to make sure user's ticker is valid--> removing punctuation and whiteSpace
	public static String makeValid(String ticker) {
		String finalTicker="";
		for (int i=0;i<ticker.length();i++) {
			if (Character.isLetter(ticker.charAt(i))) {
				finalTicker+=""+ticker.charAt(i);
			}
		}
		finalTicker=finalTicker.toUpperCase();
		return finalTicker;
	}

	//passing logger so that we can log from here
	//this method will download company data if not already present on the system
	public static void download(String ticker,Logger logger) {
		ticker=makeValid(ticker);
		if (ticker.equals("")) {
			logger.fatal("User provided a ticker that led to an empty value of ticker! The process will now abort.");
			System.out.println("User provided a ticker that led to an empty value of ticker! The process will now abort.");
			return;

		}
		

		logger.info("User has requested to fetch or check info about ticker: "+ticker);

		//now we need to check the edgar site for the specific ticker
		//if the ticker leads to no ticker site, we need to log that and then stop
		//if the ticker leads to ticker site that we have already done, it needs to compare the years to see if our data is out of date or not
		//if the ticker leads to a new ticker site, then we write the data to a YAML file for the year
		try{
			URL resource = new URL("https://sec.gov/cgi-bin/browse-edgar?CIK="+ticker);

			logger.info("Connecting to Edgar site for "+ticker);

			//getting parsed html site from Edgar using Jsoup
			
			Document toCheck=Jsoup.connect("https://sec.gov/cgi-bin/browse-edgar?CIK="+ticker)
				.get();

			logger.info("Successfully connected to Edgar database! Now parsing data...");
			
			//now we have loaded the html document and parsed it using JSoup
//			System.out.println(toCheck.location());
			Elements desired =toCheck.getElementsByTag("DIV");
			System.out.println(desired.get(0).text());
//			System.out.println(toCheck.body());
			if (desired.get(0).text().equals("No matching Ticker Symbol.")) {
				logger.error("Ticker does not exist. Aborting Process.");
				System.out.println("The ticker does not exist! Aborting Process");
				return;
			} else {
				//now we have a valid ticker, so we need to pull data from the latest 10-k filing
				//So, we need to fill the filing type textfield in the html form and then submit it in a POST request to the server to get the search results back
				//We have 3 cases: 
				//1: The company has the filing for this year
					//in this case we just pick this years filing
				//2: The company has not yet filed for this year
					//in this case we just select the filing for last year
					//if there is one
					//else we stop the process
				//3: The company is new and does not have any filings yet
					//we stop the program and say that there is no possible filing!

				//first step is to filter for 10-k
				//we will submit a post request with all the required data
				toCheck=Jsoup.connect("https://sec.gov/cgi-bin/browse-edgar?CIK="+ticker)
					.data("action","getcompany")
					.data("CIK",ticker)
					.data("type","10-k")
					.post();
				//after posting, toCheck now has the html for the listings of 10-k filings
				//now, I need to check to see if the current year's 10-k is available
				int year = Calendar.getInstance().get(Calendar.YEAR);
				//the first listing will be the earliest, 
				//I will get the div of the listing by ID and then see if its children are empty or not
				Element firstListing = toCheck.getElementById("seriesDiv");
				Element table = firstListing.child(0);
				Elements rows = table.getElementsByTag("TR");
				if (rows.size()==1) {
					//then we only have the header and no 10-k exists
					logger.info("No 10-K filing exists yet for this ticker.Stopping the process.");
					return;
				} else {
					//then the 10-k filings are nonempty
					Elements dataPoints = table.getElementsByTag("TD");
					String date = dataPoints.get(3).text();
					//need to check if the first 4 chracters of the date match the systems year
					
					String filingYear ="";
					for (int i=0;i<4;i++) {
						filingYear+=""+date.charAt(i);
					}

					int yearToCompare=Integer.parseInt(filingYear);

					if (yearToCompare==year) {
						//then we take the latest 10-k
						//we will make a helper function to store all the data with a url as an input
					} else if ((year-1)!=yearToCompare) {
						//then filings are out of date
						logger.info("No recent 10-k Filing Exists. Maybe the stock has been delisted. Stopping the process.");
						return;
					} else {
						//then the fiscal year hasnt passed yet and we can grab last years data
						//we will make a helper function to store all the data with a url as an input

					}

				}


			}
			


		} catch(Exception e) {
			//if exception happens that means connection cannot be established
			//Jsoup.connect will throw an IOException if it is unable to connect to the url
			System.out.println("hi");
			logger.fatal("Could not establish connection to Edgar! Aborting operation.");
			
		}

	}



}
