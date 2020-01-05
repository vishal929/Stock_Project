//Cash Flow statement object that stock data object has
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;





public class CashFlowStatement{


	private long netIncome;
	private long adjustDepreciation;
	private long adjustInventory;
	private long adjustAccountsReceivable;
	private long netCashFlowFromOps;
	private long cashFromSaleOfPPE;
	private long cashForPurchaseOfEquipment;
	private long netCashFlowFromInvesting;
	private long cashPaidForLoans;
	private long netCashFlowFromFinancing;
	private long netIncreaseInCash;

	private long adjustAmortizationOfIntangibles;
	private long adjustStockCompensation;
	private long adjustDeferredTaxes;
	private long adjustOtherAssets;
	private long adjustAccountsPayable;
	private long cashForSecuritiesInvestment;
	private long cashFromSaleOfSecurities;
	private long cashForBusinessPurchase;
	private long cashFromBusinessDivestiture;
	private long cashFromIssuingLoans;
	private long commonStockRepurchase;
	private long cashFromIssuingCommonStock;
	private long cashForDividends;
	private long adjustExchangeRate;
	private long cashBeginning;
	private long incomeTaxPaid;

	

	

	//hashmap for matching description to method
	private HashMap<String,Method> description;	

	//constructor to initialize hashmap
	public CashFlowStatement() throws NoSuchMethodException{	
		this.description=new HashMap<String,Method>();
		//adding String method pairs to hashmap
		description.put("ProfitLoss",CashFlowStatement.class.getMethod("setNetIncome",long.class));
		description.put("Depreciation",CashFlowStatement.class.getMethod("setAdjustDepreciation",long.class));
		description.put("IncreaseDecreaseInInventories",CashFlowStatement.class.getMethod("setAdjustInventory",long.class));
		description.put("IncreaseDecreaseInReceivables",CashFlowStatement.class.getMethod("setAdjustAccountsReceivable",long.class));
		description.put("NetCashProvidedByUsedInOperatingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromOps",long.class));
		description.put("NetCashProvidedByUsedInOperatingActivitiesContinuingOperations",CashFlowStatement.class.getMethod("setNetCashFlowFromOps",long.class));

		description.put("ProceedsFromSaleOfPropertyPlantAndEquipment",CashFlowStatement.class.getMethod("setCashFromSaleOfPPE",long.class));
		description.put("PaymentsToAcquirePropertyPlantAndEquipment",CashFlowStatement.class.getMethod("setCashForPurchaseOfEquipment",long.class));
		description.put("NetCashProvidedByUsedInInvestingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromInvesting",long.class));
		description.put("NetCashProvidedByUsedInInvestingActivitiesContinuingOperations",CashFlowStatement.class.getMethod("setNetCashFlowFromInvesting",long.class));

		description.put("RepaymentsOfLongTermDebtAndCapitalSecurities",CashFlowStatement.class.getMethod("setCashPaidForLoans",long.class));
		description.put("NetCashProvidedByUsedInFinancingActivities",CashFlowStatement.class.getMethod("setNetCashFlowFromFinancing",long.class));
		description.put("NetCashProvidedByUsedInFinancingActivitiesContinuingOperations",CashFlowStatement.class.getMethod("setNetCashFlowFromFinancing",long.class));

		description.put("CashCashEquivalentsRestrictedCashAndRestrictedCashEquivalentsPeriodIncreaseDecreaseIncludingExchangeRateEffect",CashFlowStatement.class.getMethod("setNetIncreaseInCash",long.class));
		description.put("CashAndCashEquivalentsPeriodIncreaseDecrease",CashFlowStatement.class.getMethod("setNetIncreaseInCash",long.class));

		description.put("AmortizationOfIntangibleAssets",CashFlowStatement.class.getMethod("setAdjustAmortizationOfIntangibles",long.class));
		description.put("ShareBasedCompensation",CashFlowStatement.class.getMethod("setAdjustStockCompensation",long.class));
		description.put("DeferredIncomeTaxExpenseBenefit",CashFlowStatement.class.getMethod("setAdjustDeferredTaxes",long.class));
		description.put("IncreaseDecreaseInOtherOperatingCapitalNet",CashFlowStatement.class.getMethod("setAdjustOtherAssets",long.class));
		description.put("IncreaseDecreaseInAccountsPayable",CashFlowStatement.class.getMethod("setAdjustAccountsPayable",long.class));
		description.put("PaymentsToAcquireInvestments",CashFlowStatement.class.getMethod("setCashForSecuritiesInvestment",long.class));
		description.put("ProceedsFromSaleMaturityAndCollectionsOfInvestments",CashFlowStatement.class.getMethod("setCashFromSaleOfSecurities",long.class));
		description.put("PaymentsToAcquireBusinessesNetOfCashAcquired",CashFlowStatement.class.getMethod("setCashForBusinessPurchase",long.class));
		description.put("ProceedsFromDivestitureOfBusinessesNetOfCashDivested",CashFlowStatement.class.getMethod("setCashFromBusinessDivestiture",long.class));
		description.put("ProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet",CashFlowStatement.class.getMethod("setCashFromIssuingLoans",long.class));
		description.put("PaymentsForRepurchaseOfCommonStock",CashFlowStatement.class.getMethod("setCommonStockRepurchase",long.class));
		description.put("ProceedsFromIssuanceOrSaleOfEquity",CashFlowStatement.class.getMethod("setCashFromIssuingCommonStock",long.class));
		description.put("PaymentsOfDividendsCommonStock",CashFlowStatement.class.getMethod("setCashForDividends",long.class));
		description.put("EffectOfExchangeRateOnCashCashEquivalentsRestrictedCashAndRestrictedCashEquivalents",CashFlowStatement.class.getMethod("setAdjustExchangeRate",long.class));
		description.put("EffectOfExchangeRateOnCashAndCashEquivalents",CashFlowStatement.class.getMethod("setAdjustExchangeRate",long.class));

		description.put("CashAndCashEquivalentsAtCarryingValue",CashFlowStatement.class.getMethod("setCashBeginning",long.class));
		description.put("IncomeTaxesPaidNet",CashFlowStatement.class.getMethod("setIncomeTaxPaid",long.class));













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
					long data=XMLParser.getData(line);
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
	
	public void setNetIncome(long netIncome) {
		this.netIncome=netIncome;
	}

	public long getNetIncome() {
		return this.netIncome;
	}

	public void setAdjustDepreciation(long adjustDepreciation) {
		this.adjustDepreciation=adjustDepreciation;
	}

	public long getAdjustDepreciation() {
		return this.adjustDepreciation;
	}

	public void setAdjustInventory(long adjustInventory) {
		this.adjustInventory=adjustInventory;
	}

	public long getAdjustInventory() {
		return this.adjustInventory;
	}

	public void setAdjustAccountsReceivable(long adjustAccountsReceivable) {
		this.adjustAccountsReceivable=adjustAccountsReceivable;
	}

	public long getAdjustAccountsReceivable() {
		return this.adjustAccountsReceivable;
	}

	public void setNetCashFlowFromOps(long netCashFlowFromOps) {
		this.netCashFlowFromOps=netCashFlowFromOps;
	}

	public long getNetCashFlowFromOps() {
		return this.netCashFlowFromOps;
	}

	public void setCashFromSaleOfPPE(long cashFromSaleOfPPE) {
		this.cashFromSaleOfPPE=cashFromSaleOfPPE;
	}

	public long getCashFromSaleOfPPE() {
		return this.cashFromSaleOfPPE;
	}

	public void setCashForPurchaseOfEquipment(long cashForPurchaseOfEquipment) {
		this.cashForPurchaseOfEquipment=cashForPurchaseOfEquipment;
	}

	public long getCashForPurchaseOfEquipment() {
		return this.cashForPurchaseOfEquipment;
	}

	public void setNetCashFlowFromInvesting(long netCashFlowFromInvesting) {
		this.netCashFlowFromInvesting=netCashFlowFromInvesting;
	}

	public long getNetCashFlowFromInvesting() {
		return this.netCashFlowFromInvesting;
	}

	public void setCashPaidForLoans(long cashPaidForLoans) {
		this.cashPaidForLoans=cashPaidForLoans;
	}

	public long getCashPaidForLoans() {
		return this.cashPaidForLoans;
	}

	public void setNetCashFlowFromFinancing(long netCashFlowFromFinancing) {
		this.netCashFlowFromFinancing=netCashFlowFromFinancing;
	}

	public long getNetCashFlowFromFinancing() {
		return this.netCashFlowFromFinancing;
	}

	public void setNetIncreaseInCash(long netIncreaseInCash) {
		this.netIncreaseInCash=netIncreaseInCash;
	}

	public long getNetIncreaseInCash() {
		return this.netIncreaseInCash;
	}

	public void setAdjustAmortizationOfIntangibles(long adjustAmortizationOfIntangibles){
		this.adjustAmortizationOfIntangibles=adjustAmortizationOfIntangibles;
	}

	public long getAdjustAmortizationOfIntangibles(){
		return this.adjustAmortizationOfIntangibles;
	}

	public void setAdjustStockCompensation(long adjustStockCompensation){
		this.adjustStockCompensation=adjustStockCompensation;
	}

	public long getAdjustStockCompensation(){
		return this.adjustStockCompensation;
	}

	public void setAdjustDeferredTaxes(long adjustDeferredTaxes){
		this.adjustDeferredTaxes=adjustDeferredTaxes;
	}

	public long getAdjustDeferredTaxes(){
		return this.adjustDeferredTaxes;
	}



	public void setAdjustOtherAssets(long adjustOtherAssets){
		this.adjustOtherAssets=adjustOtherAssets;
	}

	public long getAdjustOtherAssets(){
		return this.adjustOtherAssets;
	}

	public void setAdjustAccountsPayable(long A){
		this.adjustAccountsPayable=A;
	}

	public long getAdjustAccountsPayable(){
		return this.adjustAccountsPayable;
	}

	public void setCashForSecuritiesInvestment(long A){
		this.cashForSecuritiesInvestment=A;
	}

	public long getcashForSecuritiesInvestment(){
		return this.cashForSecuritiesInvestment;
	}

	public void setCashFromSaleOfSecurities(long cashFromSaleOfSecurities){
		this.cashFromSaleOfSecurities=cashFromSaleOfSecurities;
	}

	public long getCashFromSaleOfSecurities(){
		return this.cashFromSaleOfSecurities;
	}


	public void setCashForBusinessPurchase(long cashForBusinessPurchase){
		this.cashForBusinessPurchase=cashForBusinessPurchase;
	}

	public long getCashForBusinessPurchase(){
		return this.cashForBusinessPurchase;
	}

	public void setCashFromBusinessDivestiture(long cashFromBusinessDivestiture){
		this.cashFromBusinessDivestiture=cashFromBusinessDivestiture;
	}

	public long getCashFromBusinessDivestiture(){
		return this.cashFromBusinessDivestiture;
	}

	public void setCashFromIssuingLoans(long cashFromLoans){
		this.cashFromIssuingLoans=cashFromLoans;
	}

	public long getCashFromIssuingLoans(){
		return this.cashFromIssuingLoans;
	}


	public void setCommonStockRepurchase(long commonStockRepurchase){
		this.commonStockRepurchase=commonStockRepurchase;
	}

	public long getCommonStockRepurchase(){
		return this.commonStockRepurchase;
	}

	public void setCashFromIssuingCommonStock(long commonStockOtherTransactions){
		this.cashFromIssuingCommonStock=commonStockOtherTransactions;
	}

	public long getCashFromIssuingCommonStock(){
		return this.cashFromIssuingCommonStock;
	}

	public void setCashForDividends(long cashForDividends){
		this.cashForDividends=cashForDividends;
	}

	public long getCashForDividends(){
		return this.cashForDividends;
	}

	public void setAdjustExchangeRate(long A){
		this.adjustExchangeRate=A;
	}

	public long getadjustExchangeRate(){
		return this.adjustExchangeRate;
	}

	public void setCashBeginning(long cashBeginning){
		this.cashBeginning=cashBeginning;
	}

	public long getCashBeginning(){
		return this.cashBeginning;
	}

	
	public void setIncomeTaxPaid(long incomeTaxPaid){
		this.incomeTaxPaid=incomeTaxPaid;
	}

	public long getIncomeTaxPaid(){
		return this.incomeTaxPaid;
	}

}
