package a2;

import java.util.Scanner;
import java.util.Arrays;

public class A2Novice extends A2Primary {

public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		scan.close();
		
		output(ingredients);
		
	}
	
	/* output
	* Prints report of number of vegetarian ingredients and reports which ingredients provide the highest
	* and lowest calories per dollar, formatted as follows:
	* 
	* Number of vegetarian ingredients: <NumberOfVegetarianIngredients>
	  Highest cals/$: <IngredientWithHighestRatio>
	  Lowest cals/$: <IngredientWithLowestRatio>
	*/
	private static void output ( Ingredient[] ingredients ) {
		System.out.println("Number of vegetarian ingredients: " 
				+ Arrays.stream(ingredients).filter( x -> x.isVegetarian ).toArray(Ingredient[]::new).length);
		System.out.println("Highest cals/$: " + find("highest", ingredients).name);
		System.out.println("Lowest cals/$: " + find("lowest", ingredients).name);
	}
	
}
