abstract class Shopper implements ShoppingTimeRemaining{
	//Abstract Shopper class created while implementing the previous interface
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

	//creates 2 more abstract methods to be used in next classes
	public abstract void setTimeIntoCheckoutLine(int v);
	public abstract int getTotalTimeCheckingOut();
}
