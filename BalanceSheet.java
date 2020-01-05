import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;






//balance sheet object that stock data object has

public class BalanceSheet{
	
	//listing out current assets 
	private long accountsReceivable;
	private long shortNotesReceivable;
	private long cash;
	private long marketableSecurities;
	private long inventoryNet;
	private long inventoryFinished;
	private long inventoryWorkInProcess;
	private long otherCurrentAssetsAndPrepaidExpenses;
	private long netCurrentAssets;
	private long deferredCurrentTaxes;

	//listing out long term assets (PPE)
	private long longNotesReceivable;
	private long propertyPlantEquipment;
	private long accumulatedDepreciation;
	private long netPropertyPlantEquipment; 
	private long netAssets;
	private long deferredLongTaxes;

	private long prepaidPension;
	private long deferredTaxes;
	private long goodwill;
	private long intangibleAssets;
	private long longInvestments;

	
	//listing out current liabilities
	private long accountsPayable;
	private long shortTermBorrowings;
	private long shortPortionOfLongDebt;

	private long taxes;
	private long shortTermDebt;
	private long compensationAndBenefits;
	private long currentDeferredIncome;
		//NEED GETTER AND SETTER FOR THESE
	private long netCurrentLiabilities;

	//listing out long Term liabilities
	private long longDebt;
	private long otherLongDebt;
	private long netLiabilities;
	
	private long longDeferredIncome;

	private long retirementObligations;

	//listing out stockholders equity items
	private long commonStock;
	private long retainedEarnings;
	private long treasuryStock;
	private long netStockHoldersEquity;

	private long accumulatedOtherIncome;
	private long noncontrollingInterests;
	private long totalEquity;

	//every BalanceSheet object will have a hashmap with Strings mapped to Functions to insert data to make the process efficient and easy
	
	private HashMap<String,Method> description;

	public BalanceSheet() throws NoSuchMethodException{
		//everything starts out null, but on initialization of the object, we initialize the HashMap
		//putting description for current assets
		this.description= new HashMap<String,Method>();	
		description.put("AvailableForSaleSecuritiesDebtSecuritiesCurrent",BalanceSheet.class.getMethod("setMarketableSecurities",long.class));
		description.put("MarketableSecuritiesCurrent",BalanceSheet.class.getMethod("setMarketableSecurities",long.class));

		description.put("AccountsReceivableNetCurrent",BalanceSheet.class.getMethod("setAccountsReceivable",long.class));
		description.put("AccountsNotesAndLoansReceivableNetCurrent",BalanceSheet.class.getMethod("setAccountsReceivable",long.class));

		description.put("NotesAndLoansReceivableNetCurrent",BalanceSheet.class.getMethod("setShortNotesReceivable",long.class));
		description.put("CashAndCashEquivalentsAtCarryingValue",BalanceSheet.class.getMethod("setCash",long.class));
		description.put("InventoryNet",BalanceSheet.class.getMethod("setInventoryNet",long.class));
		description.put("InventoryFinishedGoodsNetOfReserves",BalanceSheet.class.getMethod("setInventoryFinished",long.class));
		description.put("InventoryWorkInProcessAndRawMaterialsNetOfReserves",BalanceSheet.class.getMethod("setInventoryWorkInProcess",long.class));
		description.put("PrepaidExpenseAndOtherAssetsCurrent",BalanceSheet.class.getMethod("setOtherCurrentAssetsAndPrepaidExpenses",long.class));
		description.put("AssetsCurrent",BalanceSheet.class.getMethod("setNetCurrentAssets",long.class));

		//putting description for long term assets
		description.put("NotesAndLoansReceivableNetNoncurrent",BalanceSheet.class.getMethod("setLongNotesReceivable",long.class));
		description.put("PropertyPlantAndEquipmentGross",BalanceSheet.class.getMethod("setPropertyPlantEquipment",long.class));
		description.put("AccumulatedDepreciationDepletionAndAmortizationPropertyPlantAndEquipment",BalanceSheet.class.getMethod("setAccumulatedDepreciation",long.class));
		description.put("PropertyPlantAndEquipmentNet",BalanceSheet.class.getMethod("setNetPropertyPlantEquipment",long.class));
		description.put("Assets",BalanceSheet.class.getMethod("setNetAssets",long.class));

		//putting description for short term liabilities
		description.put("ShortTermBorrowings",BalanceSheet.class.getMethod("setShortTermBorrowings",long.class));
		description.put("AccountsPayableCurrent",BalanceSheet.class.getMethod("setAccountsPayable",long.class));
		description.put("LongTermDebtCurrent",BalanceSheet.class.getMethod("setShortPortionOfLongDebt",long.class));
		description.put("LiabilitiesCurrent",BalanceSheet.class.getMethod("setNetCurrentLiabilities",long.class));

		//putting description for long term debt
		description.put("LongTermDebtNoncurrent",BalanceSheet.class.getMethod("setLongDebt",long.class));
		description.put("LongTermDebtAndCapitalLeaseObligations",BalanceSheet.class.getMethod("setLongDebt",long.class));

		description.put("OtherLiabilitiesNoncurrent",BalanceSheet.class.getMethod("setOtherLongDebt",long.class));
		description.put("Liabilities",BalanceSheet.class.getMethod("setNetLiabilities",long.class));
		
		//putting description for stockholders equity
		description.put("CommonStocksIncludingAdditionalPaidInCapital",BalanceSheet.class.getMethod("setCommonStock",long.class));
		description.put("CommonStockIncludingAdditionalPaidInCapital",BalanceSheet.class.getMethod("setCommonStock",long.class));

		description.put("RetainedEarningsAccumulatedDeficit",BalanceSheet.class.getMethod("setRetainedEarnings",long.class));
		description.put("TreasuryStockCommonValue",BalanceSheet.class.getMethod("setTreasuryStock",long.class));
		description.put("TreasuryStockValue",BalanceSheet.class.getMethod("setTreasuryStock",long.class));

		description.put("StockholdersEquity",BalanceSheet.class.getMethod("setNetStockHoldersEquity",long.class));
		//description i missed (maybe need to reorder)

		description.put("DefinedBenefitPlanAssetsForPlanBenefitsNoncurrent",BalanceSheet.class.getMethod("setPrepaidPension",long.class));
		description.put("PrepaidPensionCosts",BalanceSheet.class.getMethod("setPrepaidPension",long.class));

		description.put("DeferredIncomeTaxAssetsNet",BalanceSheet.class.getMethod("setDeferredTaxes",long.class));
		description.put("DeferredIncomeTaxAssetsNetCurrent",BalanceSheet.class.getMethod("setDeferredCurrentTaxes",long.class));
		description.put("DeferredIncomeTaxAssetsNetNoncurrent",BalanceSheet.class.getMethod("setDeferredLongTaxes",long.class));


		description.put("Goodwill",BalanceSheet.class.getMethod("setGoodwill",long.class));
		description.put("FiniteLivedIntangibleAssetsNet",BalanceSheet.class.getMethod("setIntangibleAssets",long.class));
		description.put("IntangibleAssetsNetExcludingGoodwill",BalanceSheet.class.getMethod("setIntangibleAssets",long.class));

		description.put("InvestmentsAndOtherNoncurrentAssets",BalanceSheet.class.getMethod("setLongInvestments",long.class));
		description.put("OtherAssetsNoncurrent",BalanceSheet.class.getMethod("setLongInvestments",long.class));

		description.put("TaxesPayableCurrent",BalanceSheet.class.getMethod("setTaxes",long.class));
		description.put("DebtCurrent",BalanceSheet.class.getMethod("setShortTermDebt",long.class));
		description.put("EmployeeRelatedLiabilitiesCurrent",BalanceSheet.class.getMethod("setCompensationAndBenefits",long.class));
		description.put("DeferredCompensationLiabilityCurrent",BalanceSheet.class.getMethod("setCompensationAndBenefits",long.class));

		description.put("PensionAndOtherPostretirementDefinedBenefitPlansLiabilitiesNoncurrent",BalanceSheet.class.getMethod("setRetirementObligations",long.class));
		description.put("ContractWithCustomerLiabilityCurrent",BalanceSheet.class.getMethod("setCurrentDeferredIncome",long.class));
		description.put("DeferredRevenueCurrent",BalanceSheet.class.getMethod("setCurrentDeferredIncome",long.class));

		description.put("ContractWithCustomerLiabilityNoncurrent",BalanceSheet.class.getMethod("setLongDeferredIncome",long.class));
		description.put("DeferredRevenueNoncurrent",BalanceSheet.class.getMethod("setLongDeferredIncome",long.class));

		description.put("AccumulatedOtherComprehensiveIncomeLossNetOfTax",BalanceSheet.class.getMethod("setAccumulatedOtherIncome",long.class));
		description.put("MinorityInterest",BalanceSheet.class.getMethod("setNoncontrollingInterests",long.class));
		description.put("StockholdersEquityIncludingPortionAttributableToNoncontrollingInterest",BalanceSheet.class.getMethod("setTotalEquity",long.class));


		

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
					long data = XMLParser.getData(line);
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
	
	public void setMarketableSecurities(long marketableSecurities){
		this.marketableSecurities=marketableSecurities;
	}

	public long getMarketableSecurities(){
		return this.marketableSecurities;
	}
	
	public void setAccountsReceivable(long accountsReceivable) {
		this.accountsReceivable=accountsReceivable;
	}
	
	public long getAccountsReceivable() {
		return this.accountsReceivable;
	}

	public void setShortNotesReceivable(long shortNotesReceivable) {
		this.shortNotesReceivable=shortNotesReceivable;
	}

	public long getShortNotesReceivable() {
		return this.shortNotesReceivable;
	}

	public void setCash(long cash) {
		this.cash=cash;
	}

	public long getCash() {
		return this.cash;
	}

	public void setInventoryNet(long inventoryNet) {
		this.inventoryNet=inventoryNet;
	}

	public long getInventoryNet() {
		return this.inventoryNet;
	}

	public void setInventoryFinished(long inventoryFinished) {
		this.inventoryFinished=inventoryFinished;
	}

	public long getInventoryFinished() {
		return this.inventoryFinished;
	}

	public void setInventoryWorkInProcess(long inventoryWorkInProcess) {
		this.inventoryWorkInProcess=inventoryWorkInProcess;
	}

	public long getInventoryWorkInProcess() {
		return this.inventoryWorkInProcess;
	}

	public void setOtherCurrentAssetsAndPrepaidExpenses(long otherCurrentAssets) {
		this.otherCurrentAssetsAndPrepaidExpenses=otherCurrentAssets;
	}

	public long getOtherCurrentAssetsAndPrepaidExpenses() {
		return this.otherCurrentAssetsAndPrepaidExpenses;
	}

	public void setNetCurrentAssets(long netCurrentAssets) {
		this.netCurrentAssets=netCurrentAssets;
	}

	public long getNetCurrentAssets() {
		return this.netCurrentAssets;
	}
	
	public void setLongNotesReceivable(long longNotesReceivable ) {
		this.longNotesReceivable=longNotesReceivable;
	}

	public long getLongNotesReceivable(){
		return this.longNotesReceivable;
	}

	public void setPropertyPlantEquipment(long propertyPlantEquipment) {
		this.propertyPlantEquipment=propertyPlantEquipment;
	}

	public long getPropertyPlantEquipment() {
		return this.propertyPlantEquipment;
	}


	public void setAccumulatedDepreciation(long accumulatedDepreciation){
		this.accumulatedDepreciation=accumulatedDepreciation;
	}

	public long getAccumulatedDepreciation() {
		return this.accumulatedDepreciation;
	}

	public void setNetPropertyPlantEquipment(long netPropertyPlantEquipment) {
		this.netPropertyPlantEquipment=netPropertyPlantEquipment;
	}

	public long getNetPropertyPlantEquipment() {
		return this.netPropertyPlantEquipment;
	}



	public void setNetAssets(long netAssets) {
		this.netAssets=netAssets;
	}

	public long getNetAssets() {
		return this.netAssets;
	}
	
	public void setAccountsPayable(long accountsPayable) {
		this.accountsPayable=accountsPayable;
	}

	public long getAccountsPayable() {
		return this.accountsPayable;
	}

	public void setShortTermBorrowings(long shortTermBorrowings) {
		this.shortTermBorrowings=shortTermBorrowings;
	}

	public long getShortTermBorrowings() {
		return this.shortTermBorrowings;
	}



	public void setShortPortionOfLongDebt(long shortPortionOfLongDebt) {
		this.shortPortionOfLongDebt=shortPortionOfLongDebt;
	}

	public long getShortPortionOfLongDebt() {
		return this.shortPortionOfLongDebt;
	}
	




	public void setNetCurrentLiabilities(long netCurrentLiabilities) {
		this.netCurrentLiabilities=netCurrentLiabilities;
	}

	public long getNetCurrentLiabilities() {
		return this.netCurrentLiabilities;
	}

	public void setLongDebt(long longDebt) {
		this.longDebt=longDebt;
	}

	public long getLongDebt() {
		return this.longDebt;
	}

	public void setOtherLongDebt(long otherLongDebt) {
		this.otherLongDebt=otherLongDebt;
	}

	public long getOtherLongDebt() {
		return this.otherLongDebt;
	}
	
	public void setNetLiabilities(long netLiabilities) {
		this.netLiabilities=netLiabilities;
	}

	public long getNetLiabilities() {
		return this.netLiabilities;
	}

	public void setCommonStock(long commonStock) {
		this.commonStock=commonStock;
	}

	public long getCommonStock() {
		return this.commonStock;
	}

	public void setRetainedEarnings(long retainedEarnings) {
		this.retainedEarnings=retainedEarnings;
	}

	public long getRetainedEarnings() {
		return this.retainedEarnings;
	}

	public void setTreasuryStock(long treasuryStock) {
		this.treasuryStock=treasuryStock;
	}

	public long getTreasuryStock() {
		return this.treasuryStock;
	}

	public void setNetStockHoldersEquity(long netStockHoldersEquity) {
		this.netStockHoldersEquity=netStockHoldersEquity;
	}

	public long getNetStockHoldersEquity() {
		return this.netStockHoldersEquity;
	}

	public void setPrepaidPension(long prepaidPension){
		this.prepaidPension=prepaidPension;
	}

	public long getPrepaidPension(){
		return this.prepaidPension;
	}

	public void setDeferredTaxes(long DeferredTaxes){
		this.deferredTaxes=DeferredTaxes;
	}

	public long getDeferredTaxes(){
		return this.deferredTaxes;
	}

	public void setGoodwill(long Goodwill){
		this.goodwill=Goodwill;
	}

	public long getGoodwill(){
		return this.goodwill;
	}

	public void setIntangibleAssets(long intangibleAssets){
		this.intangibleAssets=intangibleAssets;
	}

	public long getIntangibleAssets(){
		return this.intangibleAssets;
	}

	public void setLongInvestments(long longInvestments){
		this.longInvestments=longInvestments;
	}

	public long getLongInvestments(){
		return this.longInvestments;
	}



	public void setTaxes(long taxes){
		this.taxes=taxes;
	}

	public long getTaxes(){
		return this.taxes;
	}

	public void setShortTermDebt(long shortTermDebt){
		this.shortTermDebt=shortTermDebt;
	}

	public long getShortTermDebt(){
		return this.shortTermDebt;
	}

	public void setCompensationAndBenefits(long compensationAndBenefits){
		this.compensationAndBenefits=compensationAndBenefits;
	}

	public long getCompensationAndBenefits(){
		return this.compensationAndBenefits;
	}

	
	public void setRetirementObligations(long retirementObligations){
		this.retirementObligations=retirementObligations;
	}

	public long getRetirementObligations(){
		return this.retirementObligations;
	}

	public void setCurrentDeferredIncome(long deferredIncome){
		this.currentDeferredIncome=deferredIncome;
	}

	public long getCurrentDeferredIncome(){
		return this.currentDeferredIncome;
	}

	public void setLongDeferredIncome(long A){
		this.longDeferredIncome=A;
	}

	public long getLongDeferredIncome(){
		return this.longDeferredIncome;
	}

	public void setAccumulatedOtherIncome(long accumulatedOtherIncome){
		this.accumulatedOtherIncome=accumulatedOtherIncome;
	}

	public long getAccumulatedOtherIncome(){
		return this.accumulatedOtherIncome;
	}

	public void setNoncontrollingInterests(long noncontrollingInterests){
		this.noncontrollingInterests=noncontrollingInterests;
	}

	public long getNoncontrollingInterests(){
		return this.noncontrollingInterests;
	}

	public void setTotalEquity(long totalEquity){
		this.totalEquity=totalEquity;
	}

	public long getTotalEquity(){
		return this.totalEquity;
	}

	public void setDeferredCurrentTaxes(long deferredCurrentTaxes){
		this.deferredCurrentTaxes=deferredCurrentTaxes;
	}

	public long getDeferredCurrentTaxes(){
		return this.deferredCurrentTaxes;
	}

	public void setDeferredLongTaxes(long DeferredLongTaxes){
		this.deferredLongTaxes=DeferredLongTaxes;
	}

	public long getDeferredLongTaxes(){
		return this.deferredLongTaxes;
	}







}
