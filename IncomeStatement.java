//income statement object that stock data object has
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;




public class IncomeStatement{


	private double netSales;
	private double costOfSales;
	private double grossProfit;
	private double RAndDExpense;
	private double SGAndAExpense;
	private double otherIncomeExpense;
	private double additionalExpense;
	private double EBIT;
	private double EBT;
	private double incomeTax;
	private double interestExpense;
	private double netIncome;
	private double commonSharesOutstanding;
	private double netIncomePerShare;

	

	//hashmap of operations
	private HashMap<String,Method> description;

	//initializing Hashmap in constructor
	public IncomeStatement() throws NoSuchMethodException{
		this.description=new HashMap<String,Method>();

		//filling HashMap with String,Method pairs
		description.put("Revenues",IncomeStatement.class.getMethod("setNetSales",double.class));
		description.put("CostOfRevenue",IncomeStatement.class.getMethod("setCostOfSales",double.class));
		description.put("GrossProfit",IncomeStatement.class.getMethod("setGrossProfit",double.class));
		description.put("ResearchAndDevelopmentExpense",IncomeStatement.class.getMethod("setRAndDExpense",double.class));
		description.put("SellingGeneralAndAdministrativeExpense",IncomeStatement.class.getMethod("setSGAndAExpense",double.class));
		//FIGURE OUT other income/EXPENSE/OPERATING iNCOME
		description.put("OtherIncomeAndExpense",IncomeStatement.class.getMethod("setOtherIncomeExpense",double.class));
		//look at note above
		//FIGURE THIS sTuFF OUT TOO
		description.put("IncomeLossFromContinuingOperationsBeforeIncomeTaxesExtraordinaryItemsNoncontrollingInterest",IncomeStatement.class.getMethod("setEBT",double.class));
		description.put("IncomeTaxExpenseBenefit",IncomeStatement.class.getMethod("setIncomeTax",double.class));
		description.put("InterestExpense",IncomeStatement.class.getMethod("setInterestExpense",double.class));
		description.put("NetIncomeLoss",IncomeStatement.class.getMethod("setNetIncome",double.class));
		
		description.put("WeightedAverageNumberOfSharesOutstandingBasic",IncomeStatement.class.getMethod("setCommonSharesOutstanding",double.class));

		description.put("EarningsPerShareBasic",IncomeStatement.class.getMethod("setNetIncomePerShare",double.class));




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
					double data = XMLParser.getData(line);
					Method toDo=this.description.get(context);
					toDo.invoke(this,data);
					//removing context from hashmap so the value cannot be changed
					this.description.remove(context);
				}
			}
		}
	}


	//getter setter methods 
	
	public void setNetSales(double netSales) {
		this.netSales=netSales;
	}

	public double getNetSales() {
		return this.netSales;
	}

	public void setCostOfSales(double costOfSales) {
		this.costOfSales=costOfSales;
	}

	public double getCostOfSales() {
		return this.costOfSales;
	}

	public void setGrossProfit(double grossProfit) {
		this.grossProfit=grossProfit;
	}

	public double getGrossProfit() {
		return this.grossProfit;
	}

	public void setRAndDExpense(double RAndDExpense) {
		this.RAndDExpense=RAndDExpense;
	}

	public double getRAndDExpense() {
		return this.RAndDExpense;
	}

	public void setSGAndAExpense(double SGAndAExpense) {
		this.SGAndAExpense=SGAndAExpense;
	}

	public double getSGAndAExpense() {
		return this.SGAndAExpense;
	}
	
	public void setOtherIncomeExpense(double otherIncomeExpense) {
		this.otherIncomeExpense=otherIncomeExpense;
	}

	public double getOtherIncomeExpense() {
		return this.otherIncomeExpense;
	}


	public void setEBIT(double EBIT) {
		this.EBIT=EBIT;
	}

	public double getEBIT() {
		return this.EBIT;
	}

	public void setEBT(double EBT) {
		this.EBT=EBT;
	}

	public double getEBT() {
		return this.EBT;
	}

	public void setInterestExpense(double interestExpense) {
		this.interestExpense=interestExpense;
	}

	public double getInterestExpense(){
		return this.interestExpense;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax=incomeTax;
	}

	public double getIncomeTax() {
		return this.incomeTax;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome=netIncome;
	}

	public double getNetIncome() {
		return this.netIncome;
	}
	
	public double getCommonSharesOutstanding(){
		return commonSharesOutstanding;
	}	

	public void setCommonSharesOutstanding(double commonShares){
		commonSharesOutstanding=commonShares;
	}

	public double getNetIncomePerShare(){
		return netIncomePerShare;
	}

	public void setNetIncomePerShare(double eps){
		netIncomePerShare=eps;
	}



}
