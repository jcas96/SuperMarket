abstract class Shopper implements ShoppingTimeRemaining{
	//Abstract Shopper class created
	String shopperType;
	Shopper(){
		setShopperType("");
	}
	
	Shopper(String s){
		setShopperType(s);
	}
	
	
	public void setShopperType(String sh) {
		shopperType=sh;
	}
	
	public String getShopperType() {
		return shopperType;
	}

	//previous abstract methods are made sure to be implemented
	public abstract void setTimeIntoCheckoutLine(int v);
	public abstract int getTotalTimeCheckingOut();
}
