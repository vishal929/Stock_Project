//income statement object that stock data object has
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;





public class IncomeStatement{


	private long netSales;
	private long costOfSales;
	private long grossProfit;
	private long RAndDExpense;
	private long SGAndAExpense;
	private long EBT;
	private long interestExpense;
	private long netIncome;
	private long commonSharesOutstanding;


	private long provisionForIncomeTax;
	private long incomeFromDiscontinuedOperations; 

	


	

	//hashmap of operations
	private HashMap<String,Method> description;

	//initializing Hashmap in constructor
	public IncomeStatement() throws NoSuchMethodException{
		this.description=new HashMap<String,Method>();

		//filling HashMap with String,Method pairs
		description.put("Revenues",IncomeStatement.class.getMethod("setNetSales",long.class));
		description.put("CostOfRevenue",IncomeStatement.class.getMethod("setCostOfSales",long.class));
		description.put("GrossProfit",IncomeStatement.class.getMethod("setGrossProfit",long.class));
		description.put("ResearchAndDevelopmentExpense",IncomeStatement.class.getMethod("setRAndDExpense",long.class));
		description.put("SellingGeneralAndAdministrativeExpense",IncomeStatement.class.getMethod("setSGAndAExpense",long.class));
		//look at note above
		//FIGURE THIS sTuFF OUT TOO
		description.put("IncomeLossFromContinuingOperationsBeforeIncomeTaxesExtraordinaryItemsNoncontrollingInterest",IncomeStatement.class.getMethod("setEBT",long.class));
		description.put("IncomeLossFromContinuingOperationsBeforeIncomeTaxesMinorityInterestAndIncomeLossFromEquityMethodInvestments",IncomeStatement.class.getMethod("setEBT",long.class));

		description.put("InterestExpense",IncomeStatement.class.getMethod("setInterestExpense",long.class));
		description.put("ProfitLoss",IncomeStatement.class.getMethod("setNetIncome",long.class));
		
		description.put("WeightedAverageNumberOfSharesOutstandingBasic",IncomeStatement.class.getMethod("setCommonSharesOutstanding",long.class));

		description.put("IncomeTaxExpenseBenefit",IncomeStatement.class.getMethod("setProvisionForIncomeTax",long.class));			
		description.put("IncomeLossFromDiscontinuedOperationsNetOfTaxAttributableToReportingEntity",IncomeStatement.class.getMethod("setIncomeFromDiscontinuedOperations",long.class));			
		description.put("IncomeLossFromDiscontinuedOperationsNetOfTax",IncomeStatement.class.getMethod("setIncomeFromDiscontinuedOperations",long.class));			

















	}

	//method to add values to the IncomeStatement
	public void addItem(String line,int year) throws IllegalAccessException , InvocationTargetException {
		if (this.description.size()==0) {
			return;
		} else {
			String context = XMLParser.getContext(line);
			if (!this.description.containsKey(context)){
				return;
			} else {
				String date=XMLParser.getDate(line);
				if (XMLParser.sameYear(date,year)){
					//then year matches what the user needs so we can store the data
					long data = XMLParser.getData(line);
					Method toDo=this.description.get(context);
					toDo.invoke(this,data);
					//removing context from hashmap so the value cannot be changed
					this.description.remove(context);
				}
			}
		}
	}


	//getter setter methods 
	
	public void setNetSales(long netSales) {
		this.netSales=netSales;
	}

	public long getNetSales() {
		return this.netSales;
	}

	public void setCostOfSales(long costOfSales) {
		this.costOfSales=costOfSales;
	}

	public long getCostOfSales() {
		return this.costOfSales;
	}

	public void setGrossProfit(long grossProfit) {
		this.grossProfit=grossProfit;
	}

	public long getGrossProfit() {
		return this.grossProfit;
	}

	public void setRAndDExpense(long RAndDExpense) {
		this.RAndDExpense=RAndDExpense;
	}

	public long getRAndDExpense() {
		return this.RAndDExpense;
	}

	public void setSGAndAExpense(long SGAndAExpense) {
		this.SGAndAExpense=SGAndAExpense;
	}

	public long getSGAndAExpense() {
		return this.SGAndAExpense;
	}
	




	public void setEBT(long EBT) {
		this.EBT=EBT;
	}

	public long getEBT() {
		return this.EBT;
	}

	public void setInterestExpense(long interestExpense) {
		this.interestExpense=interestExpense;
	}

	public long getInterestExpense(){
		return this.interestExpense;
	}



	public void setNetIncome(long netIncome) {
		this.netIncome=netIncome;
	}

	public long getNetIncome() {
		return this.netIncome;
	}
	
	public long getCommonSharesOutstanding(){
		return commonSharesOutstanding;
	}	

	public void setCommonSharesOutstanding(long commonShares){
		commonSharesOutstanding=commonShares;
	}

	


	public void setProvisionForIncomeTax(long provisionForIncomeTax){
		this.provisionForIncomeTax=provisionForIncomeTax;
	}

	public long getProvisionForIncomeTax(){
		return this.provisionForIncomeTax;
	}

	public void setIncomeFromDiscontinuedOperations(long incomeFromDiscontinuedOperations){
		this.incomeFromDiscontinuedOperations=incomeFromDiscontinuedOperations;
	}

	public long getIncomeFromDiscontinuedOperations(){
		return this.incomeFromDiscontinuedOperations;
	}



}
