
//purpose of this java file is to take xml data
//then take that data and serialize it into a YAML file
//purpose of the YAML file is for organization, readability, and flexibility
//any other language can use the yaml file and I can just send YAML files etc.



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


public class XMLParser{

	//method to get data from xml and then call the method to parse the data
	public void getData (URL xmlFile, int year) {
		Logger logger = LogManager.getLogger("logger");
		try{
			logger.info("Starting the process to parse XML file.");
			BufferedReader xmlLine = new BufferedReader(new InputStream(xmlFile.openStream()));	

			selectFinancialData(xmlLine,year);


			logger.info("Finished parsing XML file.");	
		} catch (Exception e) {
			logger.fatal("Could not connect to Edgar for parsing xml file! Aborting Process!");	
			System.out.println("Could not establish connection! Aborting process!");
		}
	}

	public void selectFinancialData(BufferedReader xmlReader, int year){
		//this is the logic for parsing
		String line = xmlReader.nextLine();
		while (line!=null) {
			if (!line.substring(0,9).equals("<us-gaap:")){
				line=xmlReader.nextLine();
			} else {
				//then the line is a us-gaap line item
				//getting the item name
				String item="";
				int endOfItem=0;
				for (int i=9;i<line.length();i++) {
					if (line.charAt(i)==' '){
						endOfItem=i;
						break;
					} else {
						item=""+line.charAt(i);
					}
				}
				//getting the date description
				//there are 13 characters between the end of item description and the start of the context of the date
				String date="";
				for (int i=endOfItem+13;i<line.length();i++){
					if (line.charAt(i)=='\"') {
						break;
					} else {
						date=""+line.charAt(i);
					}
				}

				//need to check if item matches items we are looking for in our different financial statements 
					//initializing financial statements
				BalanceSheet balanceSheet = new BalanceSheet();
				IncomeStatement incomeStatement=new IncomeStatement();
				CashFlowStatement cashFlowStatement = new CashFlowStatement();
					//parsing data and adding them to financial statements	
				//MAYBE TRY TO USE A HASHMAP TO MATCH XML ITEM TO FINANCIAL STATEMENT ENTRY
				//need to check if data has same year(then we will store the data)		
			}
		}
	}
	
	//helper function for parsing data
	public int extractYear(String date,int year){
		//checks description of xml line that describes the year and extracts the year as an integer

		for (int i=0;i<date.length();i++) {
			for (int j=i;j<=date.length();j++){
				try {
					int toCompare = date.substring(i,j+1)
						if (toCompare==year) {
							return true;
						}
				} catch (Exception e) {
					//catching numberFormat exception in the case that string is NaN
				}
			}
		}
		return false;
		
			
	}

	//helper function for parsing data
	public void toGet(String detail){
		//will determine if the data needs to be stored
		//i.e maybe the library doesnt need intangible assets but it needs accounts receivable
	}

	//method to write object to YAML file
	public void makeYAML(Object stock){

	}





}
