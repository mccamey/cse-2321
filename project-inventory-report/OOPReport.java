import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OOPReport {

	// Class variables
	public static ArrayList<Product> productList = new ArrayList<Product>();

	// Appends products from the input text file to the public ArrayList
	public static void addProducts(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		for (int i = 0; input.hasNextLine(); i++) {
			Product product = new Product();
			productList.add(product);
			product.setName(input.nextLine());
			product.setInventoryCode(input.nextLine());
			product.setQuantity(Integer.parseInt(input.nextLine()));
			product.setPrice(Integer.parseInt(input.nextLine()));
			product.setType(input.nextLine());

			while (input.hasNextInt()) {
				int rating = input.nextInt();
				if (rating != -1)
					product.addUserRating(rating);
				else if (input.hasNextLine())
					input.nextLine();
				else
					break;
			}
		}
		input.close();
	}
	
	// Prints the formatted list of products
	public static void printProducts() {

		System.out.println("Product Inventory Summary Report");
		System.out.println("---------------------------------" + "------------------------------------------");
		System.out.println();

		String print = "Product Name";
		System.out.printf("%-34s", print);
		print = "I Code";
		System.out.printf("%-10s", print);
		print = "Type";
		System.out.printf("%-6s", print);
		print = "Rating";
		System.out.printf("%-7s", print);
		print = "# Rat.";
		System.out.printf("%-7s", print);
		print = "Quant.";
		System.out.printf("%-7s", print);
		print = "Price";
		System.out.printf("%-8s \n", print);

		print = "--------------------------------";
		System.out.printf("%-34s", print);
		print = "---------";
		System.out.printf("%-10s", print);
		print = "----";
		System.out.printf("%-6s", print);
		print = "------";
		System.out.printf("%-7s", print);
		print = "------";
		System.out.printf("%-7s", print);
		print = "------";
		System.out.printf("%-7s", print);
		print = "------";
		System.out.printf("%-8s \n", print);

		for (int i = 0; i < productList.size(); i++) {
			System.out.printf("%-34s", productList.get(i).getName());
			System.out.printf("%-10s", productList.get(i).getInventoryCode());
			System.out.printf("%-6s", productList.get(i).getType());
			
			int rating = productList.get(i).getAvgUserRating();
			String stars = "";
			switch (rating) {
			case 1:
				stars = "(*)";
				System.out.printf("%-6s", stars);
				break;
			case 2:
				stars = "(**)";
				System.out.printf("%-6s", stars);
				break;
			case 3:
				stars = "(***)";
				System.out.printf("%-6s", stars);
				break;
			case 4:
				stars = "(****)";
				System.out.printf("%-6s", stars);
				break;
			case 5:
				stars = "(*****)";
				System.out.printf("%-6s", stars);
				break;
			default:
				stars = "()";
				System.out.printf("%-6s", stars);
				break;
			}
			
			System.out.printf("%7s", productList.get(i).getUserRatingCount());
			System.out.printf("%7s", productList.get(i).getQuantity());
			
			int totalPennies = productList.get(i).getPrice() * productList.get(i).getQuantity();
			double totalDollars = (totalPennies / 100) + ((totalPennies % 100) * .01);
			System.out.printf("%8.2f \n", totalDollars);
		}
	}

	// Prints the analysis of the inventory, calls computational sub-methods
	public static void printAnalysis() {
		System.out.println("---------------------------------" + "------------------------------------------");
		System.out.println("Total products in the database: " + productList.size());
		System.out.println("Highest Average Ranked item: " + highestRanked());
		System.out.println("Lowest Average Ranked item: " + lowestRanked());
		System.out.println("Highest Total Dollar item: " + highestDollar());
		System.out.println("Lowest Total Dollar item: " + lowestDollar());
	}

	// Sub-method to compute highest ranked item
	public static String highestRanked() {
		String product = "";
		String stars = "()";
		int rating = -1;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getAvgUserRating() > rating) {
				product = productList.get(i).getName();
				rating = productList.get(i).getAvgUserRating();
			}
		}
		switch (rating) {
		case 1:
			stars = "(*)";
		case 2:
			stars = "(**)";
		case 3:
			stars = "(***)";
		case 4:
			stars = "(****)";
		case 5:
			stars = "(*****)";
		default:
			break;
		}
		return product + " " + stars;
	}

	// Sub-method to compute lowest ranked item
	public static String lowestRanked() {
		String product = "";
		String stars = "()";
		int rating = 6;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getAvgUserRating() < rating) {
				product = productList.get(i).getName();
				rating = productList.get(i).getAvgUserRating();
			}
		}
		switch (rating) {
		case 1:
			stars = "(*)";
		case 2:
			stars = "(**)";
		case 3:
			stars = "(***)";
		case 4:
			stars = "(****)";
		case 5:
			stars = "(*****)";
		default:
			break;
		}
		return product + " " + stars;
	}

	// Sub-method to compute highest total dollar amount (price * quantity)
	public static String highestDollar() {
		String product = "";
		double highestDollar = 0;
		for (int i = 0; i < productList.size(); i++) {
			int totalPennies = productList.get(i).getPrice() * productList.get(i).getQuantity();
			double totalDollars = (totalPennies / 100) + ((totalPennies % 100) * .01);
			if (totalDollars > highestDollar) {
				product = productList.get(i).getName();
				highestDollar = totalDollars;
			}
		}
		String productDetails = String.format("%s ($%.2f)", product, highestDollar);
		return productDetails;
	}

	// Sub-method to compute highest total dollar amount (price * quantity)
	public static String lowestDollar() {
		String product = "";
		double lowestDollar = 2147483647;
		for (int i = 0; i < productList.size(); i++) {
			int totalPennies = productList.get(i).getPrice() * productList.get(i).getQuantity();
			double totalDollars = (totalPennies / 100) + ((totalPennies % 100) * .01);
			if (totalDollars < lowestDollar) {
				product = productList.get(i).getName();
				lowestDollar = totalDollars;
			}
		}
		String productDetails = String.format("%s ($%.2f)", product, lowestDollar);
		return productDetails;
	}

	// Main method which prompts user, takes input, creates a new File object, and calls methods
	public static void main(String[] args) {
		System.out.print("Enter an inventory filename: ");

		Scanner keyboard = new Scanner(System.in);
		File file = new File(keyboard.nextLine());

		try {
			addProducts(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		printProducts();
		printAnalysis();

		keyboard.close();
	}
}
