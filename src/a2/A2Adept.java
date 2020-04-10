package a2;

import java.util.Arrays;
import java.util.Scanner;

public class A2Adept extends A2Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		MenuItem[] menu = menuInfo(scan, ingredients);
		
		scan.close();
		
		Arrays.asList(menu).forEach((item) -> item.summary());
	}
	
}
