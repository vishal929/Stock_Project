



//class for performing operations to analyze stock data in the CompanyFile object that represents the company filing
//

/* ratios or statistics that I might want to incorporate: \\
	1) quick ratio (acid test ratio) balance sheet----> (Cash and equivalents+Marketable Securities+Accounts Receivable)/Current Liabilities


*/	

//i will give option for user to get individual statistics and provide a general statistics file that will calculate the statistics for every year and compile them in a file

public class AnalysisLibrary{



	public static double getQuickRatio(CompanyFile filing){
		//method to get quick ratio from some filing
		BalanceSheet balanceSheet = filing.getBalanceSheet();
		double cash = balanceSheet.getCash();
		double accountsReceivable = balanceSheet.getAccountsReceivable();
		double marketableSecurities = balanceSheet.getMarketableSecurities();
		double currentLiabilities = balanceSheet.getNetCurrentLiabilities();

		double quickRatio=(cash+accountsReceivable+marketableSecurities)/currentLiabilities;

		return quickRatio;

	}


	public static double getWorkingCapitalRatio(CompanyFile filing){
		//method to get working capital ratio from some filing
		BalanceSheet balanceSheet = filing.getBalanceSheet();
		double currentAssets =balanceSheet.getNetCurrentAssets(); 
		double currentLiabilities= balanceSheet.getNetCurrentLiabilities();

		double workingCapitalRatio = (currentAssets/currentLiabilities);
		
		return workingCapitalRatio;
	}

	public static double getDebtEquityRatio(CompanyFile filing){
		//method to get debtequity ratio
		//totaldebt/book value=debt equity ratio
		BalanceSheet balanceSheet = filing.getBalanceSheet();
		double totalDebt=balanceSheet.getShortPortionOfLongDebt() + balanceSheet.getLongDebt() + balanceSheet.getOtherLongDebt();
		double bookValue=balanceSheet.getNetAssets()-balanceSheet.getNetLiabilities();
		return totalDebt/bookValue;
	}


	public static double getEarningsPerShare(CompanyFile filing){
		//method to get earnings per share
		//net income/ number of common stock shares outstanding
		IncomeStatement is = filing.getIncomeStatement();
		return is.getNetIncomePerShare();
	}







}
