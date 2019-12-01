import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;





//balance sheet object that stock data object has

public class BalanceSheet{
	
	//listing out current assets 
	private double accountsReceivable;
	private double shortNotesReceivable;
	private double cash;
	private double marketableSecurities;
	private double inventoryNet;
	private double inventoryFinished;
	private double inventoryWorkInProcess;
	private double otherCurrentAssets;
	private double netCurrentAssets;

	//listing out long term assets (PPE)
	private double longNotesReceivable;
	private double propertyPlantEquipment;
	private double accumulatedDepreciation;
	private double netPropertyPlantEquipment; 
	private double otherLongAssets;
	private double netAssets;

	//listing out current liabilities
	private double accountsPayable;
	private double shortTermBorrowings;
	private double shortPortionOfLongDebt;
	private double unearnedRevenue;
		//NEED GETTER AND SETTER FOR THESE
	private double employeeRelatedLiabilities;
	private double accruedLiabilities;
	private double netCurrentLiabilities;

	//listing out long Term liabilities
	private double longDebt;
	private double otherLongDebt;
	private double netLiabilities;

	//listing out stockholders equity items
	private double commonStock;
	private double retainedEarnings;
	private double treasuryStock;
	private double netStockHoldersEquity;

	//every BalanceSheet object will have a hashmap with Strings mapped to Functions to insert data to make the process efficient and easy
	
	private HashMap<String,Method> description;

	public BalanceSheet() throws NoSuchMethodException{
		//everything starts out null, but on initialization of the object, we initialize the HashMap
		//putting description for current assets
		this.description= new HashMap<String,Method>();	
		description.put("AvailableForSaleSecuritiesDebtSecuritiesCurrent",BalanceSheet.class.getMethod("setMarketableSecurities",double.class));
		description.put("AccountsReceivableNetCurrent",BalanceSheet.class.getMethod("setAccountsReceivable",double.class));
		description.put("NotesAndLoansReceivableNetCurrent",BalanceSheet.class.getMethod("setShortNotesReceivable",double.class));
		description.put("CashAndCashEquivalentsAtCarryingValue",BalanceSheet.class.getMethod("setCash",double.class));
		description.put("InventoryNet",BalanceSheet.class.getMethod("setInventoryNet",double.class));
		description.put("InventoryFinishedGoodsNetOfReserves",BalanceSheet.class.getMethod("setInventoryFinished",double.class));
		description.put("InventoryWorkInProcessAndRawMaterialsNetOfReserves",BalanceSheet.class.getMethod("setInventoryWorkInProcess",double.class));
		description.put("PrepaidExpenseAndOtherAssetsCurrent",BalanceSheet.class.getMethod("setOtherCurrentAssets",double.class));
		description.put("AssetsCurrent",BalanceSheet.class.getMethod("setNetCurrentAssets",double.class));

		//putting description for long term assets
		description.put("NotesAndLoansReceivableNetNoncurrent",BalanceSheet.class.getMethod("setLongNotesReceivable",double.class));
		description.put("PropertyPlantAndEquipmentGross",BalanceSheet.class.getMethod("setPropertyPlantEquipment",double.class));
		description.put("AccumulatedDepreciationDepletionAndAmortizationPropertyPlantAndEquipment",BalanceSheet.class.getMethod("setAccumulatedDepreciation",double.class));
		description.put("PropertyPlantAndEquipmentNet",BalanceSheet.class.getMethod("setNetPropertyPlantEquipment",double.class));
		description.put("OtherAssetsNoncurrent",BalanceSheet.class.getMethod("setOtherLongAssets",double.class));
		description.put("Assets",BalanceSheet.class.getMethod("setNetAssets",double.class));

		//putting description for short term liabilities
		description.put("ShortTermBorrowings",BalanceSheet.class.getMethod("setShortTermBorrowings",double.class));
		description.put("AccountsPayableCurrent",BalanceSheet.class.getMethod("setAccountsPayable",double.class));
		description.put("LongTermDebtCurrent",BalanceSheet.class.getMethod("setShortPortionOfLongDebt",double.class));
		description.put("RevenueRemainingPerformanceObligation",BalanceSheet.class.getMethod("setUnearnedRevenue",double.class));
		description.put("EmployeeRelatedLiabilitiesCurrent",BalanceSheet.class.getMethod("setEmployeeRelatedLiabilities",double.class));
		description.put("AccruedLiabilities",BalanceSheet.class.getMethod("setAccruedLiabilities",double.class));
		description.put("LiabilitiesCurrent",BalanceSheet.class.getMethod("setNetCurrentLiabilities",double.class));

		//putting description for long term debt
		description.put("LongTermDebtNoncurrent",BalanceSheet.class.getMethod("setLongDebt",double.class));
		description.put("OtherLiabilitiesNoncurrent",BalanceSheet.class.getMethod("setOtherLongDebt",double.class));
		description.put("Liabilities",BalanceSheet.class.getMethod("setNetLiabilities",double.class));
		
		//putting description for stockholders equity
		description.put("CommonStocksIncludingAdditionalPaidInCapital",BalanceSheet.class.getMethod("setCommonStock",double.class));
		description.put("RetainedEarningsAccumulatedDeficit",BalanceSheet.class.getMethod("setRetainedEarnings",double.class));
		description.put("TreasuryStockCommonValue",BalanceSheet.class.getMethod("setTreasuryStock",double.class));
		description.put("StockholdersEquity",BalanceSheet.class.getMethod("setNetStockHoldersEquity",double.class));
	}

	//logic for adding values to the BalanceSheet
	//sameYear boolean is just to check whether the year matches the year the user is looking for	
	public void addItem(String line,int year) throws IllegalAccessException , InvocationTargetException {
		
		//calling the method to populate the data in the balance sheet and then remove the value that was added from the hashmap
		
		if (this.description.size()==0) {
			return;
		} else {
			String context = XMLParser.getContext(line);
			if (!this.description.containsKey(context)) {
				return;
			} else {
				String date = XMLParser.getDate(line);
				if (XMLParser.sameYear(date,year)) {
					double data = XMLParser.getData(line);
					//then the year matches what the user needs and we can store the data
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


	//getter and setter methods for each private entry:
	
	public void setMarketableSecurities(double marketableSecurities){
		this.marketableSecurities=marketableSecurities;
	}

	public double returnMarketableSecurities(){
		return this.marketableSecurities;
	}
	
	public void setAccountsReceivable(double accountsReceivable) {
		this.accountsReceivable=accountsReceivable;
	}
	
	public double getAccountsReceivable() {
		return this.accountsReceivable;
	}

	public void setShortNotesReceivable(double shortNotesReceivable) {
		this.shortNotesReceivable=shortNotesReceivable;
	}

	public double getShortNotesReceivable() {
		return this.shortNotesReceivable;
	}

	public void setCash(double cash) {
		this.cash=cash;
	}

	public double getCash() {
		return this.cash;
	}

	public void setInventoryNet(double inventoryNet) {
		this.inventoryNet=inventoryNet;
	}

	public double getInventoryNet() {
		return this.inventoryNet;
	}

	public void setInventoryFinished(double inventoryFinished) {
		this.inventoryFinished=inventoryFinished;
	}

	public double getInventoryFinished() {
		return this.inventoryFinished;
	}

	public void setInventoryWorkInProcess(double inventoryWorkInProcess) {
		this.inventoryWorkInProcess=inventoryWorkInProcess;
	}

	public double getInventoryWorkInProcess() {
		return this.inventoryWorkInProcess;
	}

	public void setOtherCurrentAssets(double otherCurrentAssets) {
		this.otherCurrentAssets=otherCurrentAssets;
	}

	public double getOtherCurrentAssets() {
		return this.otherCurrentAssets;
	}

	public void setNetCurrentAssets(double netCurrentAssets) {
		this.netCurrentAssets=netCurrentAssets;
	}

	public double getNetCurrentAssets() {
		return this.netCurrentAssets;
	}
	
	public void setLongNotesReceivable(double longNotesReceivable ) {
		this.longNotesReceivable=longNotesReceivable;
	}

	public double getLongNotesReceivable(){
		return this.longNotesReceivable;
	}

	public void setPropertyPlantEquipment(double propertyPlantEquipment) {
		this.propertyPlantEquipment=propertyPlantEquipment;
	}

	public double getPropertyPlantEquipment() {
		return this.propertyPlantEquipment;
	}


	public void setAccumulatedDepreciation(double accumulatedDepreciation){
		this.accumulatedDepreciation=accumulatedDepreciation;
	}

	public double getAccumulatedDepreciation() {
		return this.accumulatedDepreciation;
	}

	public void setNetPropertyPlantEquipment(double netPropertyPlantEquipment) {
		this.netPropertyPlantEquipment=netPropertyPlantEquipment;
	}

	public double getNetPropertyPlantEquipment() {
		return this.netPropertyPlantEquipment;
	}

	public void setOtherLongAssets(double otherLongAssets) {
		this.otherLongAssets=otherLongAssets;
	}

	public double getOtherLongAssets() {
		return this.otherLongAssets;
	}

	public void setNetAssets(double netAssets) {
		this.netAssets=netAssets;
	}

	public double getNetAssets() {
		return this.netAssets;
	}
	
	public void setAccountsPayable(double accountsPayable) {
		this.accountsPayable=accountsPayable;
	}

	public double getAccountsPayable() {
		return this.accountsPayable;
	}

	public void setShortTermBorrowings(double shortTermBorrowings) {
		this.shortTermBorrowings=shortTermBorrowings;
	}

	public double getShortTermBorrowings() {
		return this.shortTermBorrowings;
	}

	public void setUnearnedRevenue(double unearnedRevenue) {
		this.unearnedRevenue=unearnedRevenue;
	}

	public double getUnearnedRevenue() {
		return this.unearnedRevenue;
	} 

	public void setShortPortionOfLongDebt(double shortPortionOfLongDebt) {
		this.shortPortionOfLongDebt=shortPortionOfLongDebt;
	}

	public double getShortPortionOfLongDebt() {
		return this.shortPortionOfLongDebt;
	}
	
	public void setEmployeeRelatedLiabilities(double employeeRelatedLiabilities) {
		this.employeeRelatedLiabilities=employeeRelatedLiabilities;
	}

	public double getEmployeeRelatedLiabilities() {
		return this.employeeRelatedLiabilities;
	}

	public void setAccruedLiabilities(double accruedLiabilities) {
		this.accruedLiabilities=accruedLiabilities;
	}

	public double getAccruedLiabilities() {
		return this.accruedLiabilities;
	}

	public void setNetCurrentLiabilities(double netCurrentLiabilities) {
		this.netCurrentLiabilities=netCurrentLiabilities;
	}

	public double getNetCurrentLiabilities() {
		return this.netCurrentLiabilities;
	}

	public void setLongDebt(double longDebt) {
		this.longDebt=longDebt;
	}

	public double getLongDebt() {
		return this.longDebt;
	}

	public void setOtherLongDebt(double otherLongDebt) {
		this.otherLongDebt=otherLongDebt;
	}

	public double getOtherLongDebt() {
		return this.otherLongDebt;
	}
	
	public void setNetLiabilities(double netLiabilities) {
		this.netLiabilities=netLiabilities;
	}

	public double getNetLiabilities() {
		return this.netLiabilities;
	}

	public void setCommonStock(double commonStock) {
		this.commonStock=commonStock;
	}

	public double getCommonStock() {
		return this.commonStock;
	}

	public void setRetainedEarnings(double retainedEarnings) {
		this.retainedEarnings=retainedEarnings;
	}

	public double getRetainedEarnings() {
		return this.retainedEarnings;
	}

	public void setTreasuryStock(double treasuryStock) {
		this.treasuryStock=treasuryStock;
	}

	public double getTreasuryStock() {
		return this.treasuryStock;
	}

	public void setNetStockHoldersEquity(double netStockHoldersEquity) {
		this.netStockHoldersEquity=netStockHoldersEquity;
	}

	public double getNetStockHoldersEquity() {
		return this.netStockHoldersEquity;
	}



	





}
