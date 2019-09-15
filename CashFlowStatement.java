//Cash Flow statement object that stock data object has
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;




public class CashFlowStatement{


	private double netIncome;
	private double adjustDepreciation;
	private double adjustInventory;
	private double adjustAccountsReceivable;
	private double netCashFlowFromOps;
	private double cashFromSaleOfPPE;
	private double cashForPurchaseOfEquipment;
	private double netCashFlowFromInvesting;
	private double cashPaidForLoans;
	private double netCashFlowFromFinancing;
	private double netIncreaseInCash;

	//hashmap for matching description to method
	private HashMap<String,Method> description;	

	//constructor to initialize hashmap
	public CashFlowStatement() throws NoSuchMethodException{	
		this.description=new HashMap<String,Method>();
		//adding String method pairs to hashmap
		description.put("NetIncomeLoss",CashFlowStatement.class.getMethod("setNetIncome",double.class));
		description.put("Depreciation",CashFlowStatement.class.getMethod("setAdjustDepreciation",double.class));
		description.put("IncreaseDecreaseInInventories",CashFlowStatement.class.getMethod("setAdjustInventory",double.class));
		description.put("IncreaseDecreaseInReceivables",CashFlowStatement.class.getMethod("setAdjustAccountsReceivable",double.class));
		description.put("NetCashProvidedByUsedInOperatingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromOps",double.class));
		description.put("ProceedsFromSaleOfPropertyPlantAndEquipment",CashFlowStatement.class.getMethod("setCashFromSaleOfPPE",double.class));
		description.put("PaymentsToAcquirePropertyPlantAndEquipment",CashFlowStatement.class.getMethod("setCashForPurchaseOfEquipment",double.class));
		description.put("NetCashProvidedByUsedInInvestingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromInvesting",double.class));
		description.put("RepaymentsOfLongTermDebtAndCapitalSecurities",CashFlowStatement.class.getMethod("setCashPaidForLoans",double.class));
		description.put("NetCashProvidedByUsedInFinancingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromFinancing",double.class));
		description.put("CashCashEquivalentsRestrictedCashAndRestrictedCashEquivalentsPeriodIncreaseDecreaseIncludingExchangeRateEffect",CashFlowStatement.class.getMethod("setNetIncreaseInCash",double.class));
	}

	//logic for adding values to the CashFlowStatement
	public void addItem(String line,int year) throws IllegalAccessException , InvocationTargetException{
		if (this.description.size()==0) {
			return;
		} else {
			String context=XMLParser.getContext(line);
			if (!this.description.containsKey(context)) {
				return;
			} else {
				String date = XMLParser.getDate(line);
				if (XMLParser.sameYear(date,year)) {
					double data=XMLParser.getData(line);
					//then the year matches the users request and we can store the data
					Method toDo = this.description.get(context);
					toDo.invoke(this,data);
					//removing context from hashmap so the value cannot be filled again
					this.description.remove(context);
				} else {
					return;
				}
			}
		}
	}

	//getter and setter methods
	
	public void setNetIncome(double netIncome) {
		this.netIncome=netIncome;
	}

	public double getNetIncome() {
		return this.netIncome;
	}

	public void setAdjustDepreciation(double adjustDepreciation) {
		this.adjustDepreciation=adjustDepreciation;
	}

	public double getAdjustDepreciation() {
		return this.adjustDepreciation;
	}

	public void setAdjustInventory(double adjustInventory) {
		this.adjustInventory=adjustInventory;
	}

	public double getAdjustInventory() {
		return this.adjustInventory;
	}

	public void setAdjustAccountsReceivable(double adjustAccountsReceivable) {
		this.adjustAccountsReceivable=adjustAccountsReceivable;
	}

	public double getAdjustAccountsReceivable() {
		return this.adjustAccountsReceivable;
	}

	public void setNetCashFlowFromOps(double netCashFlowFromOps) {
		this.netCashFlowFromOps=netCashFlowFromOps;
	}

	public double getNetCashFlowFromOps() {
		return this.netCashFlowFromOps;
	}

	public void setCashFromSaleOfPPE(double cashFromSaleOfPPE) {
		this.cashFromSaleOfPPE=cashFromSaleOfPPE;
	}

	public double getCashFromSaleOfPPE() {
		return this.cashFromSaleOfPPE;
	}

	public void setCashForPurchaseOfEquipment(double cashForPurchaseOfEquipment) {
		this.cashForPurchaseOfEquipment=cashForPurchaseOfEquipment;
	}

	public double getCashForPurchaseOfEquipment() {
		return this.cashForPurchaseOfEquipment;
	}

	public void setNetCashFlowFromInvesting(double netCashFlowFromInvesting) {
		this.netCashFlowFromInvesting=netCashFlowFromInvesting;
	}

	public double getNetCashFlowFromInvesting() {
		return this.netCashFlowFromInvesting;
	}

	public void setCashPaidForLoans(double cashPaidForLoans) {
		this.cashPaidForLoans=cashPaidForLoans;
	}

	public double getCashPaidForLoans() {
		return this.cashPaidForLoans;
	}

	public void setNetCashFlowFromFinancing(double netCashFlowFromFinancing) {
		this.netCashFlowFromFinancing=netCashFlowFromFinancing;
	}

	public double getNetCashFlowFromFinancing() {
		return this.netCashFlowFromFinancing;
	}

	public void setNetIncreaseInCash(double netIncreaseInCash) {
		this.netIncreaseInCash=netIncreaseInCash;
	}

	public double getNetIncreaseInCash() {
		return this.netIncreaseInCash;
	}





}
