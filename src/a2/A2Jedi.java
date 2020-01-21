package a2;

import java.util.Scanner;

public class A2Jedi extends A2Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		MenuItem[] menu = menuInfo(scan, ingredients);
		
		order(scan, menu, ingredients);
		
		scan.close();
		
		output(ingredients);
	}
	
	/* output
	 * Prints ingredient requirement for the order with the following format:
	 * The order will require:
	   <Ingredient1TotalAmount> ounces of <Ingredient1>
	   <Ingredient2TotalAmount> ounces of <Ingredient2>
	   ...
	   <IngredientNTotalAmount> ounces of <IngredientN>
	 */
	private static void output ( Ingredient[] ingredients ) {
		System.out.println("The order will require:");
		for (int i = 0; i < ingredients.length; i++) {
			System.out.println(ingredients[i].orderRequirement());
		}
	}
	
}
