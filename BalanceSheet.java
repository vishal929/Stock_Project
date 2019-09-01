//balance sheet object that stock data object has

public class BalanceSheet{
	
	//listing out current assets 
	private double accountsReceivable;
	private double shortNotesReceivable;
	private  double cash;
	private double inventory;
	private double supplies;
	private double netCurrentAssets

	//listing out long term assets (PPE)
	private double longNotesReceivable;
	private double land;
	private double landImprovements;
	private double buildings;
	private double equipment
	private double accumulatedDepreciation;
	private double netPropertyPlantEquipment; 
	private double netAssets;

	//listing out intangible assets	
	private double goodwill;
	private double netIntangibleAssets;
	
	//listing out current liabilities
	private double accountsPayable;
	private double shortNotesPayable;
	private double wagesPayable;
	private double interestPayable;
	private double taxesPayable;
	private double unearnedRevenue;
	private double netCurrentLiabilities;

	//listing out long Term liabilities
	private double longNotesPayable;
	private double bondsPayable;	
	private double netLongTermLiabilities;
	private double netLiabilities;

	//listing out stockholders equity items
	private double commonStock;
	private double retainedEarnings;
	private double treasuryStock;
	private double netStockHoldersEquity;

	//I will leave constructor blank so that default constructor is used
	
	//getter and setter methods for each private entry:
	
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

	public void setInventory(double inventory) {
		this.inventory=inventory;
	}

	public double getInventory() {
		return this.inventory;
	}

	public void setSupplies(double supplies) {
		this.supplies=supplies;
	}

	public double getSupplies() {
		return this.supplies;
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

	public void setLand(double land) {
		this.land=land;
	}

	public double getLand() {
		return this.land;
	}

	public void setLandImprovements(double landImprovements) {
		this.landImprovements=landImprovements;
	}

	public double getLandImprovements() {
		return this.landImprovements;
	}

	public void setBuildings(double buildings) {
		this.buildings=buildings;
	}

	public double getBuildings() {
		return this.buildings;
	}

	public void setEquipment(double equipment) {
		this.equipment=equipment;
	}

	public double getEquipment() {
		return this.equipment;
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

	public void setNetAssets(double netAssets) {
		this.netAssets=netAssets;
	}

	public double getNetAssets() {
		return this.netAssets;
	}

	public void setGoodwill(double goodwill) {
		this.goodwill=goodwill;
	}

	public double getGoodwill() {
		return this.goodwill;
	}

	public void setNetIntangibleAssets(double netIntangibleAssets) {
		this.netIntangibleAssets=netIntangibleAssets;
	}

	public double getNetIntangibleAssets() {
		return this.netIntangibleAssets;
	}

	public void setAccountsPayable(double accountsPayable) {
		this.accountsPayable=accountsPayable;
	}

	public double getAccountsPayable() {
		return this.accountsPayable;
	}

	public void setShortNotesPayable(double shortNotesPayable) {
		this.shortNotesPayable=shortNotesPayable;
	}

	public double getShortNotesPayable() {
		return this.shortNotesPayable;
	}

	public void setWagesPayable(double wagesPayable) {
		this.wagesPayable=wagesPayable;
	}

	public double getWagesPayable() {
		return this.wagesPayable;
	}

	public void setInterestPayable(double interestPayable) {
		this.interestPayable=interestPayable;
	}

	public double getInterestPayable() {
		return this.interestPayable;
	}

	public void setTaxesPayable(double taxesPayable) {
		this.taxesPayable=taxesPayable;
	}

	public double getTaxesPayable() {
		return this.taxesPayable;
	}

	public void setUnearnedRevenue(double unearnedRevenue) {
		this.unearnedRevenue=unearnedRevenue;
	}

	public double getUnearnedRevenue() {
		return this.unearnedRevenue;
	}

	public void setNetCurrentLiabilities(double netCurrentLiabilities) {
		this.netCurrentLiabilities=netCurrentLiabilities;
	}

	public double getNetCurrentLiabilities() {
		return this.netCurrentLiablities;
	}

	public void setLongNotesPayable(double longNotesPayable) {
		this.longNotesPayable=longNotesPayable;
	}

	public double getLongNotesPayable() {
		return this.longNotesPayable;
	}

	public void setBondsPayable(double bondsPayable) {
		this.bondsPayable=bondsPayable;
	}

	public double getBondsPayable() {
		return this.bondsPayable;
	}

	public void setNetLongTermLiabilities(double netLongTermLiabilities) {
		this.netLongTermLiabilities=netLongTermLiabilities;
	}

	public double getNetLongTermLiabilities() {
		return this.netLongTermLiabilities;
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
