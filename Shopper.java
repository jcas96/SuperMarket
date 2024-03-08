abstract class Shopper implements ShoppingTimeRemaining{
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
	
	public abstract void setTimeIntoCheckoutLine(int v);
	public abstract int getTotalTimeCheckingOut();
}
