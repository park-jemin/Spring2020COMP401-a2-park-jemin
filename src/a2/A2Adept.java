package a2;

import java.util.Scanner;

public class A2Adept extends A2Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		MenuItem[] menu = menuInfo(scan, ingredients);
		
		scan.close();
		
		output(menu);
	}
	
	/* output
	 * Prints summary of each menu item with name, calorie count, price, 
	 * and vegetarian status
	 * (See MenuItem summary method for more info)
	 */
	private static void output ( MenuItem[] menu ) {
		for (int i = 0; i < menu.length; i++) {
			menu[i].summary();
		}
	}
	
}
