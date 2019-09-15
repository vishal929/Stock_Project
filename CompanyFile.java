//file serves as a library and defition for a company's annual filing





public class CompanyFile{

	private String ticker;
	private int filingYear;
	private BalanceSheet balanceSheet;
	private IncomeStatement incomeStatement;
	private CashFlowStatement cashFlowStatement;



	public CompanyFile() {
		//no arg constructor
	}

	//getter and setter methods
	
	public void setTicker(String ticker) {
		this.ticker=ticker;
	}

	public String getTicker(){
		return this.ticker;
	}

	public void setFilingYear(int filingYear) {
		this.filingYear=filingYear;
	}

	public int getFilingYear() {
		return this.filingYear;
	}

	public void setBalanceSheet(BalanceSheet balanceSheet) {
		this.balanceSheet=balanceSheet;
	}

	public BalanceSheet getBalanceSheet() {
		return this.balanceSheet;
	}

	public void setIncomeStatement(IncomeStatement incomeStatement) {
		this.incomeStatement=incomeStatement;
	}

	public IncomeStatement getIncomeStatement(){
		return this.incomeStatement;
	}

	public void setCashFlowStatement(CashFlowStatement cashFlowStatement) {
		this.cashFlowStatement=cashFlowStatement;
	}

	public CashFlowStatement getCashFlowStatement(){
		return this.cashFlowStatement;
	}
	




}
