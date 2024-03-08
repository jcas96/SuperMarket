import java.util.Random;

public class FastShopper extends Shopper{
	public static int fastShopperCounter =0;
	public static int TIMEWITHCHECKER=1;
	private String fastShopperID;
	
	
	public String getFastShopperID() {
		return fastShopperID;
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
	
	FastShopper(int input){
		super("FastShopper");
		randy = new Random(fastShopperCounter);
		setFastShopperID();
		setStartTime(input);
		setTimeShopping();
		setShoppingTimeRemaining(timeShopping);
	}
	
	public void setFastShopperID() {
		fastShopperCounter++;
		fastShopperID =(getShopperType()+fastShopperCounter);
	}
	
	public void setTimeShopping() {
		timeShopping = randy.nextInt(1,7);
	}
	
	public void setTimeOutOfCheckoutLine(int out) {
		timeOutOfCheckoutLine = out;
		calculateFinalDurations();
	}
	
	public void calculateFinalDurations() {
		endTime = timeOutOfCheckoutLine + TIMEWITHCHECKER;
		totalTimeCheckingOut = Math.abs(endTime-timeIntoCheckoutLine);
		totalTimeInStore = Math.abs(endTime-startTime);
	}

	
	@Override
	public String toString(){
		return String.format("%-22S%-11d%-16d%-16d%-16d%-16d", fastShopperID,startTime,endTime,timeShopping,totalTimeCheckingOut,totalTimeInStore);
	}
	
	

}

