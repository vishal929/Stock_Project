//file serves as a library and defition for a company's annual filing





public class CompanyFile{

	private String ticker;
	private int year;
	private BalanceSheet balanceSheet;
	private IncomeStatement incomeStatement;
	private CashFlowStatement cashFlowStatement;



	public CompanyFile(String ticker, int year, BalanceSheet balanceSheet, IncomeStatement incomeStatement, CashFlowStatement cashFlowStatement){
		//initializing stock data object to store data and eventually serialize it to a YAML file in the company folder

		this.ticker=ticker;
		this.year=year;
		this.balanceSheet=balanceSheet;
		this.incomeStatement=incomeStatement;
		this.cashFlowStatement=cashFlowStatement;

	} 


}
