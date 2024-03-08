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
	//Creates ArrayLists that hold Shoppers, BigShoppers, and FastShoppers
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

	//Creates 13 shoppers to open the Super Market
	//Simulates 13 shoppers going into the super market on open
	public void openSupermarket() {
		for(int i =0;i<14;i++) {
			//1 bigshopper create every time i is divisible by 3
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

	//Method runs the process of a supermarket after open
	public void operateSupermarket(int minutes) {
		int minCounter =1;
		//allows shoppers to come in to the store
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

			//Runs through shoppers in the store and processes them in and out
			for(int i =0;i<currentShoppers.size();i++) {
				Shopper tempShopper = currentShoppers.get(i);
				tempShopper.setShoppingTimeRemaining(tempShopper.getShoppingTimeRemaining()-1);
				if(tempShopper.getShoppingTimeRemaining()==0) {
					
					//checks the type of shopper and sorts them out into organized Array Lists for shopper Type
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
					//Since shopper is checking out they are removed from the shoppers in store ArrayList
					currentShoppers.remove(i);
					i--;
				}
			}

			//Then goes through the shoppers in each checkoutLine and processes their time spent
			//in store
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
			//time then moves up one
			minCounter++;
		}
	}
	
	//Uses PrintWriter to print out all the information about a given shopper
	//Like their ShopperID, when they got in the store, when they got out,
	//and how long they spent in the store
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

		//goes through the shoppers that have made it out of the store
		//adds the output to the output file of your choice
		
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

		//adds up the total times from both shopper Array lists
		//and calculates the averages from both ArrayList
		fastAvg = fastTime/fastCounter;
		bigAvg = bigTime/bigCounter;
		outputWriter.printf("The average time checking out on the Big Queue for %d is %.2f minutes\n",bigCounter, bigAvg);
		outputWriter.printf("The average time checking out on the Fast Queue for %d is %.2f minutes",fastCounter,fastAvg);
		outputWriter.close();

		System.out.printf("DONE");
	}


}
