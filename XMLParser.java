
//purpose of this java file is to take xml data
//then take that data and serialize it into a YAML file
//purpose of the YAML file is for organization, readability, and flexibility
//any other language can use the yaml file and I can just send YAML files etc.



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;

import java.lang.reflect.InvocationTargetException;

import org.yaml.snakeyaml.Yaml;


public class XMLParser{

	//method to get data from xml and then call the method to parse the data
	public static void getData (URL xmlFile, int year,String ticker) throws IOException , NoSuchMethodException , IllegalAccessException , InvocationTargetException{
		Logger logger = LogManager.getLogger("logger");
	//	try{
			logger.info("Starting the process to parse XML file.");
			BufferedReader xmlLine = new BufferedReader(new InputStreamReader(xmlFile.openStream()));	
			selectFinancialData(xmlLine,year,ticker);


			logger.info("Finished parsing XML file.");	
/*		} catch (Exception e) {
			logger.fatal("Could not connect to Edgar for parsing xml file! Aborting Process!");	
			System.out.println("Could not establish connection! Aborting process!");
		} */
	}

	public static void selectFinancialData(BufferedReader xmlReader, int year,String ticker) throws NoSuchMethodException , IllegalAccessException, IOException, InvocationTargetException{
		Logger logger = LogManager.getLogger("logger");
		logger.info("Filling company financial statements...");
		//initializing financial statements
		//INITIALIZATION OF THESE OBJECTS THROWING SOME EXCEPTION!!!!!!!!!!!
		BalanceSheet balanceSheet = new BalanceSheet();
		IncomeStatement incomeStatement=new IncomeStatement();
		CashFlowStatement cashFlowStatement = new CashFlowStatement();
		//this is the logic for parsing
		String line = xmlReader.readLine();
		while (line!=null) {
			if (line.length()<11) {
				//line isnt long enough to contain us-gaap data so we need to continue the loop
				line=xmlReader.readLine();
			} else if (line.substring(2,11).equals("<us-gaap:")) {
				//then the data is a us-gaap line item and we should store it if it matches hash table items in each filing object

				System.out.println(getContext(line));
				System.out.println(getData(line));
				balanceSheet.addItem(line,year);
				incomeStatement.addItem(line,year);
				cashFlowStatement.addItem(line,year);
				
				line=xmlReader.readLine();

			} else {
				//then the data is not a us-gaap line item

				line=xmlReader.readLine();
			}

		}
		logger.info("successfully filled financial statements!");

		//creating companyfile object which will be the final comprehensive filing object which we will dump to YAML
		CompanyFile company = new CompanyFile();
		company.setTicker(ticker);
		company.setFilingYear(year);
		company.setBalanceSheet(balanceSheet);
		company.setIncomeStatement(incomeStatement);
		company.setCashFlowStatement(cashFlowStatement);
		makeYAML(company);	

	}

	//helper function for getting context of a us-gaap line (i.e "AccountsPayableCurrent", "Assets", etc.)
	public static String getContext(String line) {
		String item="";
		for (int i=11;i<line.length();i++) {
			if (line.charAt(i)==' '){
				break;
			} else {
				item+=""+line.charAt(i);
				}
		}
		return item;
			

	}

	//helper function to extract the date out of the line
	public static String getDate(String line) {
		String date="";
		for (int i=11;i<line.length();i++) {
			if (line.charAt(i)=='\"') {
				for (int j=i+1;j<line.length();j++) {
					if (line.charAt(j)=='\"'){
						break;
					} else {
						date+=""+line.charAt(j);
					}
				}
				break;
			}
		}
		return date;

	}

	//helper function to extract the data (financial information) out of the line
	public static double getData(String line) {
		String num = "";
		for (int i=11;i<line.length();i++) {
			if (line.charAt(i)=='>') {
				for (int j=i+1;j<line.length();j++) {
					if (line.charAt(j)=='<') {
						break;
					} else {
						num+=""+line.charAt(j);
					}
				}
				break;
			}
		}
		try{

			double toReturn=Double.parseDouble(num);
			return toReturn;
		} catch (Exception e) {
			//catching numberformat exception
			return 0;
		}
	} 
	
	//helper function for parsing data
	public static boolean sameYear(String date,int year){
		//checks description of xml line that describes the year and extracts the year as an integer

		for (int i=0;i<date.length();i++) {
			for (int j=i;j<=date.length();j++){
				try {
					int toCompare = Integer.parseInt(date.substring(i,j+1));
						if (toCompare==(year-1)) {
							return true;
						}
				} catch (Exception e) {
					//catching numberFormat exception in the case that string is NaN
				}
			}
		}
		return false;
		
			
	}



	//method to write object to YAML file
	public static void makeYAML(CompanyFile filing){
	Logger logger = LogManager.getLogger("logger");
	logger.info("serializing data into YAML file...");	
	String fileName = ""+filing.getTicker()+""+filing.getFilingYear()+".yml";

	Yaml yaml = new Yaml();
	//testing what the yaml will dump first before making a file
	System.out.println(yaml.dump(filing));
	logger.info("successfully dumped data into YAML serialization!");
	}





}
