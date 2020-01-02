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



//PROJECT STRUCTURE:
//Driver File: runs the whole project
//THIS FILE: SERVES PURPOSES OF CONNECTION TO XBRL COMPONENTS AND WILL CHECK IF THAT YEARS DATA HAS BEEN COLLECTED ALREADY (LOOKING THROUGH FILES IN SYSTEM PROJECT FILES)
//XMLPARSING FILE: WILL PARSE AND COLLECT INFORMATION FROM XML FILE
//LIBRARY FILE: WILL DO OPERATIONS WITH DATA THAT HAS BEEN COLLECTED FROM THE PARSING FILE



//IDEA: include Binary Search Tree or hash map of tickers that we have already added to some file so we can quickly check the data if the user chooses

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Method;

import java.io.*;
import java.util.Scanner;
import java.util.Calendar;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class parseEdgar{

	

	public static boolean checkDirectory(String ticker,int year,boolean[] exists) {
		Logger logger = LogManager.getLogger("logger");
		//there will be directories of tickers in the directory "companyFilings"
		//first step is to check if companyFilings directory exists, and if not, then one will be created and the user will be told that data is not available and prompt user for data gathering
		logger.info("Checking saved company filings for the requested ticker and year...");
		

		String filingsDirectory = "./companyFilings";
		File filings = new File(filingsDirectory);
		System.out.println(filings.getAbsolutePath());

		try{
			if (filings.mkdirs()){
				//then the filing didnt exist
				logger.info("successfully created company filings directory at "+ filings.getAbsolutePath()+"! Will continue to gather data...");
			} else {
				//then the filing already exists
				logger.info("found the company filings directory at" +filings.getAbsolutePath()+"! Will continue to gather data...");
			}
		} catch(Exception e){
			logger.fatal("was not able to find or create company filings directory at "+ filings.getAbsolutePath()+"! Process will now abort!");
			return false;
		}

		//Now we need to check if the company's ticker file exists
		String tickerDirectory=filingsDirectory+"/"+ticker;
		File tickerFilings = new File(tickerDirectory);

		try{
			if (tickerFilings.mkdirs()){
				//then the ticker dir did not exist
				logger.info("Created a ticker directory at" + tickerFilings.getAbsolutePath()+" ! Will proceed with gathering data...");

			} else {
				//then the ticker dir exists
				logger.info("Found the ticker directory at" + tickerFilings.getAbsolutePath()+ " Will proceed with gathering data...");
			}
		} catch(Exception e){
				logger.fatal("Not able to find or create the ticker directory at" +tickerFilings.getAbsolutePath()+ " Process will abort!");
				return false;
		}

		
		//then the ticker directory does exist and we can check if the data for the requested year is saved
		//we can search for the file with relative path
		File searched = new File(tickerDirectory+"/"+ticker+""+year+".yml");
		if (searched.exists()){
			//FILE ALREADY EXISTS NO NEED TO CONTINUE PROCESS
			logger.info("Filing found at " + searched.getAbsolutePath()+ "! No need to gather data.");
			exists[0]=true;
			return false;

		} else {
			//then the file does not exist and we can continue to gather data
			logger.error("Filing data not found. Prompting user for further input...");
			return true;

		}


			
		
		

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

	//function to make sure year is valid for parsing xml files
	public static boolean xmlValidYear(int year){
		if (year <2009 || year>Calendar.getInstance().get(Calendar.YEAR)){
			return false;
		} else {
			return true;
		}
	}

	//this method will download company data if not already present on the system
	public static boolean download(String ticker, int year,boolean[] exists) throws IOException , IllegalAccessException , NoSuchMethodException , InvocationTargetException{
		Logger logger = LogManager.getLogger("logger");
		ticker=makeValid(ticker);
		if (ticker.equals("")) {
			logger.fatal("User provided a ticker that led to an empty value of ticker! The process will now abort.");
			System.out.println("User provided a ticker that led to an empty value of ticker! The process will now abort.");
			return false;

		}
		
		if (!xmlValidYear(year)) {
			logger.error("Year that user has inputted is invalid! Aborting process!");
			System.out.println("Year is invalid! Stopping process!");
			return false;
		}
		

		logger.info("User has requested to fetch or check info about ticker: "+ticker);

		//now we need to check the edgar site for the specific ticker
		//if the ticker leads to no ticker site, we need to log that and then stop
		//if the ticker leads to ticker site that we have already done, it needs to compare the years to see if our data is out of date or not
		//if the ticker leads to a new ticker site, then we write the data to a YAML file for the year
		
//		try{

			logger.info("Connecting to Edgar for "+year+" annual filing for "+ticker);

			//getting parsed html site from Edgar using Jsoup
			
			Document toCheck=Jsoup.connect("https://sec.gov/cgi-bin/browse-edgar?CIK="+ticker)
				.get();

			logger.info("Successfully connected to Edgar database! Now parsing data...");
			
			//now we have loaded the html document and parsed it using JSoup
//			System.out.println(toCheck.location());
			Elements desired =toCheck.getElementsByTag("DIV");
//			System.out.println(toCheck.body());
			if (desired.get(0).text().equals("No matching Ticker Symbol.")) {
				logger.error("Ticker does not exist. Aborting Process.");
				System.out.println("The ticker does not exist! Aborting Process");
				return false;
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
				//WILL FIGURE OUT HOW TO ACTUALLY POST LATER
				/*
				toCheck=Jsoup.connect("https://sec.gov/cgi-bin/browse-edgar?CIK="+ticker)
					.data("action","getcompany")
					.data("CIK",ticker)
					.data("type","10-k")
					.post();
				System.out.println(toCheck);
				*/
				toCheck=Jsoup.connect("https://sec.gov/cgi-bin/browse-edgar?action=getcompany&CIK="+ticker+"&type=10-k").get();
				//after posting, toCheck now has the html for the listings of 10-k filings
				//now, I need to check to see if the current year's 10-k is available
				//the first listing will be the earliest, 
				//I will get the div of the listing by ID and then see if its children are empty or not
				Element firstListing = toCheck.getElementById("seriesDiv");
				Element table = firstListing.child(0);
				Elements rows = table.getElementsByTag("TR");
				if (rows.size()==1) {
					//then we only have the header and no 10-k exists
					System.out.println("NO FILING");
					logger.info("No 10-K filing exists yet for this ticker.Stopping the process.");
					return false;
				} else {
					//then the 10-k filings are nonempty
										
					//need to iterate through rows and determine if the year exists
					for (int i=1;i<rows.size();i++) {
						String date = rows.get(i).child(3).text();
						String filingYear ="";
						for (int j=0;j<4;j++) {
							filingYear+=""+date.charAt(j);
						}
						int yearToCompare=Integer.parseInt(filingYear);
						if (year==yearToCompare) {
							//then we found the filing we want
							Element link = rows.get(i).getElementById("documentsbutton");
							String toConnect="https://sec.gov"+link.attr("href");
							logger.info("Accessing filings at "+toConnect);
							if (checkDirectory(ticker,year,exists)){

								return parseData(toConnect,year,ticker);
							} else {
								logger.fatal("Something went wrong with checking directories. Will abort now!");
								return false;
							}
							


						} else if (year>yearToCompare) {
							//then there is no filing yet for the year we want
							logger.info("Annual statement for requested year does not exitst yet! Aborting process!");
							return false;

						}

					}
					
					
				}
				//for compilation
				return true;
			}


	/*	} catch (Exception e) {
			//if exception happens that means connection cannot be established
			//Jsoup.connect will throw an IOException if it is unable to connect to the url
			System.out.println("ABORT");
			logger.fatal("Could not establish connection to Edgar! Aborting operation.");

		}
		*/
	}


	public static boolean parseData(String link,int year,String ticker) throws IOException , IllegalAccessException , InvocationTargetException , NoSuchMethodException{
		Logger logger = LogManager.getLogger("logger");
		//BIG IDEA:
		//make hashtables for each financial document with expected data
		//connect to xml file on sec site
		//create a parser to search only for us-gaap tags
		//extract the data and then abort the process once all data is extracted
		//WILL NEED TO USE JAVA IO TO read xml file
		//need to use Jsoup to parse 10-k site


//		try{
			logger.info("Starting process to connect to XML file.");
			Document toCheck = Jsoup.connect(link).get();
			//getting table with embedded links
			Elements table = toCheck.getElementsByClass("tableFile");
			//if there is only one tableFile element, then no xml file exists 
			if (table.size()==1) {
				System.out.println("No XBLR filing exists! Maybe the filing is before 2009. Process will abort!");
				logger.info("No XBLR filing could be found! Maybe the filing was filed before 2009. Process will abort!");
				logger.info("Ended process to connect to XML file.");
				return false;
			} else {
				//then there is an xml filing that we will need to parse
				//I will call this from my financial XBLR parser Library
				//will need to call this function with the URL of the xml file
				Elements links = table.get(1).getElementsByTag("a");
				//below is the link to the xml file we need to parse
				URL toParse = new URL("https://www.sec.gov"+links.get(0).attr("href"));
				System.out.println(toParse);
				logger.info("Accessing xml file at "+toParse);
				//need to parse data in this URL using xml parse method
				//will pass year and ticker until they get recorded in YAML file
				return XMLParser.getData(toParse,year,ticker);

			}
/*		} catch (Exception e) {
			logger.fatal("Could not establish connection to Edgar! Aborting process!");
			System.out.println("Could not establish connection to Edgar! Aborting process!");
		}
		*/
	}



}
