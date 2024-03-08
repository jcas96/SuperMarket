import java.util.Random;

public class BigShopper extends Shopper{
	/creates a subclass of shopper so all the abstract methods
	//from Shopper.java and ShoppingTimeRemaining.java will be implemented
	public static int fastShopperCounter =0;
	public static int bigShopperCounter =0;
	public static int TIMEWITHCHECKER=2;
	private String bigShopperID;
	
	
	public String getBigShopperID() {
		return bigShopperID;
	}
	
	private int startTime;
	private int timeShopping;
	private int shoppingTimeRemaining;
	private int timeIntoCheckoutLine;
	private int timeOutOfCheckoutLine;
	private int endTime;
	private int totalTimeCheckingOut;
	private int totalTimeInStore;
	
	
	public void setStartTime(int start) {
		startTime = start;
	}
	public int getStartTime() {
		return startTime;
	}
	
	public int getTimeShopping() {
		return timeShopping;
	}
	
	public void setShoppingTimeRemaining(int remaining) {
		shoppingTimeRemaining= remaining;
	}
	
	public int getShoppingTimeRemaining() {
		return shoppingTimeRemaining;
	}
	
	public void setTimeIntoCheckoutLine(int into) {
		timeIntoCheckoutLine=into;
	}
	
	public int getTimeIntoCheckoutLine() {
		return timeIntoCheckoutLine;
	}
	
	public int getTimeOutOfCheckoutLine() {
		return timeOutOfCheckoutLine;
	}
	
	public int getEndTime() {
		return endTime;
	}
	public int getTotalTimeCheckingOut() {
		return totalTimeCheckingOut;
	}
	
	public int getTotalTimeInStore() {
		return totalTimeInStore;
	}
	
	Random randy;
	//BigShopper Constructor that sets the type of shopper through the Shopper super class
	//and using the already instantiated bigShopperCounter you create a random obect
	//then ID, time, timeshopping, and time remaining are set 
	BigShopper(int input){
		super("BigShopper");
		randy = new Random(bigShopperCounter);
		setBigShopperID();
		setStartTime(input);
		setTimeShopping();
		setShoppingTimeRemaining(timeShopping);
	}
	
	public void setBigShopperID() {
		bigShopperCounter++;
		bigShopperID =(getShopperType()+bigShopperCounter);
	}

	//uses the randy object created in the constructor to set the time shopping from 1-6
	public void setTimeShopping() {
		timeShopping = randy.nextInt(5,16);
	}
	
	public void setTimeOutOfCheckoutLine(int out) {
		timeOutOfCheckoutLine = out;
		calculateFinalDurations();
	}

	//calculates how long a shopper spent inside the store
	public void calculateFinalDurations() {
		endTime = timeOutOfCheckoutLine + TIMEWITHCHECKER;
		totalTimeCheckingOut = Math.abs(endTime-timeIntoCheckoutLine);
		totalTimeInStore = Math.abs(endTime-startTime);
	}

	//uses String.format format to print out the values from BigShopper
	@Override
	public String toString(){
		return String.format("%-22S%-11d%-16d%-16d%-16d%-16d", bigShopperID,startTime,endTime,timeShopping,totalTimeCheckingOut,totalTimeInStore);
	}
	
	

}
