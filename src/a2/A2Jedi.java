package a2;

import java.util.Arrays;
import java.util.Scanner;

public class A2Jedi extends A2Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		MenuItem[] menu = menuInfo(scan, ingredients);
		
		order(scan, menu, ingredients);
		
		scan.close();
		
		System.out.println("The order will require:");
		Arrays.asList(ingredients).forEach((item) -> System.out.println(item.orderRequirement()));
	}
	
}
