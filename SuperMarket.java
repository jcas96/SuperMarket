import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class SuperMarket {
	
	String superName;
	Random randy = new Random();
	int bigCheckerOccupied =0;
	int fastCheckerOccupied =0;
	ArrayList<Shopper> currentShoppers = new ArrayList<Shopper>();
	ArrayList<BigShopper> bigCheckOut = new ArrayList<BigShopper>();
	ArrayList<FastShopper> fastCheckOut = new ArrayList<FastShopper>();
	ArrayList<Shopper> doneShopping = new ArrayList<Shopper>();

	public void setSuperName(String n) {
		superName = n;
	}
	public String getSuperName() {
		return superName;
	}
	
	SuperMarket(String name, int seed){
		setSuperName(name);
		randy = new Random(seed);
	}
	
	public void openSupermarket() {
		for(int i =0;i<14;i++) {
			if(i%3==0) {
				BigShopper big = new BigShopper(0);
				currentShoppers.add(big);
			}
			else {
				FastShopper fast = new FastShopper(0);
				currentShoppers.add(fast);
			}
		}
	}
	
	public void operateSupermarket(int minutes) {
		int minCounter =1;
		while((currentShoppers.size()!=0)||(bigCheckOut.size()!=0)||(fastCheckOut.size()!=0)) {
			if((minCounter<=minutes)&&(minCounter%5==0)) {
				for(int i =0;i<9;i++) {
					if(i%3==0) {
						BigShopper newBig = new BigShopper(minCounter);
						currentShoppers.add(newBig);
					}
					else {
						FastShopper newFast = new FastShopper(minCounter);
						currentShoppers.add(newFast);
					}
				}
			}
			
			for(int i =0;i<currentShoppers.size();i++) {
				Shopper tempShopper = currentShoppers.get(i);
				tempShopper.setShoppingTimeRemaining(tempShopper.getShoppingTimeRemaining()-1);
				if(tempShopper.getShoppingTimeRemaining()==0) {
					if(tempShopper.getShopperType()=="BigShopper") {
						BigShopper tempBig = (BigShopper)tempShopper;
						tempBig.setTimeIntoCheckoutLine(minCounter);
						bigCheckOut.add(tempBig);
					}
					else {
						FastShopper tempFast = (FastShopper)tempShopper;
						tempFast.setTimeIntoCheckoutLine(minCounter);
						fastCheckOut.add(tempFast);
					}
					currentShoppers.remove(i);
					i--;
				}
			}
			if((bigCheckOut.size()!=0)&&(bigCheckerOccupied==0)) {
				BigShopper tempBig = bigCheckOut.get(0);
				bigCheckOut.remove(0);
				tempBig.setTimeOutOfCheckoutLine(minCounter);//setTimeOutOfCheckoutLine
				doneShopping.add(tempBig);
				bigCheckerOccupied = BigShopper.TIMEWITHCHECKER;
			}else if(bigCheckerOccupied!=0) {
				bigCheckerOccupied--;
			}
			
			if((fastCheckOut.size()!=0)&&(fastCheckerOccupied==0)) {
				FastShopper tempFast = fastCheckOut.get(0);
				fastCheckOut.remove(0);
				tempFast.setTimeOutOfCheckoutLine(minCounter);
				doneShopping.add(tempFast);
				fastCheckerOccupied = FastShopper.TIMEWITHCHECKER;
			}else if(fastCheckerOccupied!=0) {
				fastCheckerOccupied--;
			}
			minCounter++;
		}
	}
	
	public void generateSupermarketResults(String f) throws IOException {
		File outputFile = new File (f);
		PrintWriter outputWriter = new PrintWriter(outputFile);
		outputWriter.printf("Data For Supermarket %S\n", superName);
		outputWriter.printf("   Shopper ID      Start Time   End Time   Time Shopping   Checkout Time   Time In Store\n");
		int fastTime = 0;
		int bigTime=0;
		double fastAvg=0;
		double bigAvg=0;
		int bigCounter=0;
		int fastCounter=0;

		for(int i =0;i<doneShopping.size();i++) {
			String items = doneShopping.get(i).toString();
			outputWriter.printf("%s\n", items);
			if(doneShopping.get(i).getShopperType()=="FastShopper") {
				fastTime+= doneShopping.get(i).getTotalTimeCheckingOut();
				fastCounter++;
			}else if(doneShopping.get(i).getShopperType()=="BigShopper") {
				bigTime+= doneShopping.get(i).getTotalTimeCheckingOut();
				bigCounter++;
			}
			
		}
		fastAvg = fastTime/fastCounter;
		bigAvg = bigTime/bigCounter;
		outputWriter.printf("The average time checking out on the Big Queue for %d is %.2f minutes\n",bigCounter, bigAvg);
		outputWriter.printf("The average time checking out on the Fast Queue for %d is %.2f minutes",fastCounter,fastAvg);
		outputWriter.close();

		System.out.printf("DONE");
	}


}