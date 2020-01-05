



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
		long cash = balanceSheet.getCash();
		long accountsReceivable = balanceSheet.getAccountsReceivable();
		long marketableSecurities = balanceSheet.getMarketableSecurities();
		long currentLiabilities = balanceSheet.getNetCurrentLiabilities();
		long shortNote = balanceSheet.getShortNotesReceivable();
		System.out.println("The sum of AR+MS+Cash+SNote is:"+(cash+accountsReceivable+marketableSecurities+shortNote));
		System.out.println("Current liabilities is:"+currentLiabilities);

		double quickRatio=(cash+accountsReceivable+marketableSecurities+shortNote)/ (double) currentLiabilities;

		return quickRatio;

	}


	public static double getWorkingCapitalRatio(CompanyFile filing){
		//method to get working capital ratio from some filing
		BalanceSheet balanceSheet = filing.getBalanceSheet();
		long currentAssets =balanceSheet.getNetCurrentAssets(); 
		long currentLiabilities= balanceSheet.getNetCurrentLiabilities();

		double workingCapitalRatio = (currentAssets/ (double) currentLiabilities);
		
		return workingCapitalRatio;
	}

	public static double getDebtEquityRatio(CompanyFile filing){
		//method to get debtequity ratio
		//totaldebt/book value=debt equity ratio
		BalanceSheet balanceSheet = filing.getBalanceSheet();
		long totalDebt=balanceSheet.getShortPortionOfLongDebt() + balanceSheet.getLongDebt() + balanceSheet.getOtherLongDebt();
		long bookValue=balanceSheet.getNetAssets()-balanceSheet.getNetLiabilities();
		return  (double) totalDebt/bookValue;
	}


	public static double getEarningsPerShare(CompanyFile filing){
		//method to get earnings per share
		//net income/ number of common stock shares outstanding
		IncomeStatement is = filing.getIncomeStatement();
		long netEarnings=is.getNetIncome();
		long weightedCommon =is.getCommonSharesOutstanding();
		return (double) netEarnings/weightedCommon;
	}







}
