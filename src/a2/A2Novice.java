package a2;

import java.util.Scanner;
import java.util.Arrays;

public class A2Novice extends A2Primary {

public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Ingredient[] ingredients = ingredientInfo(scan);
		
		scan.close();
		
		System.out.println("Number of vegetarian ingredients: " + Arrays.stream(ingredients).filter( x -> x.isVegetarian ).count());
		System.out.println("Highest cals/$: " + find("highest", ingredients).name);
		System.out.println("Lowest cals/$: " + find("lowest", ingredients).name);
		
	}
	
}
