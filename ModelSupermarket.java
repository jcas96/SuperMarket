import java.io.IOException;
import java.util.Scanner;

public class ModelSupermarket {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		System.out.printf("Please enter the name of the Supermarket:\n");
		String name = input.nextLine();
		System.out.printf("Please enter an int for the seed: ");
		int seed= input.nextInt();
		SuperMarket SM1 = new SuperMarket(name,seed);
		System.out.printf("Please enter the name of the output file for SuperMarket Results: ");
		String outputFileName=input.next();
		System.out.printf("Please enter the number of minutes to operate the Supermarket: ");
		int mins = input.nextInt();
		SM1.openSupermarket();
		SM1.operateSupermarket(mins);
		SM1.generateSupermarketResults(outputFileName);
		
		input.close();

	}

}

