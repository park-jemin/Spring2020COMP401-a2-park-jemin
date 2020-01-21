package a2;

import java.util.Scanner;

// Note: for this assignment, can assume ALL VALID INPUTS

/* A2Primary
 * Parent class for A2 programs containing methods to build ingredient and menu data, as well as helper methods
 * for finding specific data
 */
public class A2Primary {
	
	/* ingredientInfo
	 * Builds ingredient data given unique ingredients and their respective names, prices per ounce, vegetarian status,
	 * and calories per ounce
	 *
	 * Input: scanner object scan for console inputs
	 * 
	 * Output: an array of Ingredients, with each holding the ingredient's respective data
	 * 
	 * Preconditions:
	 * Ingredient data must be entered with the following form:
	 * 	1) integer input of number of ingredients used by a restaurant
	 * 	2) for each ingredient, the input must be entered as follows:
	 * 		a) the name of the ingredient as an unbroken String
	 * 		b) the price per ounce as a double
	 * 		c) whether the ingredient is vegetarian as a boolean
	 * 		d) calories per ounce as an integer
	 */
	public static Ingredient[] ingredientInfo (Scanner scan) {
		Ingredient[] ingredients = new Ingredient[scan.nextInt()];
		for (int i = 0; i < ingredients.length; i++) {
			ingredients[i] = new Ingredient(scan.next(), scan.nextDouble(), scan.nextBoolean(), scan.nextInt(), 0);
		}
		return ingredients;
	}
	
	/* find
	 * Finds and returns a specific customer in a set of customers matching the conditions set by a key
	 * Used to find the biggest and smallest spenders (A2Novice), and the matching ingredient found 
	 * in a menu item's recipe for menuPrep (A2Jedi)
	 *
	 * Input: a keyword as a string, ingredient set data as an Ingredient array
	 * 
	 * Output: the Ingredient matching the condition
	 * Returns the first ingredient in the ingredients array if none match the stated conditions
	 */
	public static Ingredient find (String key, Ingredient[] ingredients) {
		
		Ingredient memo = ingredients[0];
		
		if (key.matches("lowest")) { // finds lowest cals/$
			for (int i = 1; i < ingredients.length; i++) {
				memo = (ingredients[i].caloriesPerDollar < memo.caloriesPerDollar) ? ingredients[i] : memo;
			}
			
			
		} else if (key.matches("highest")) { // finds highest cals/$
			for (int i = 1; i < ingredients.length; i++) {
				memo = (ingredients[i].caloriesPerDollar > memo.caloriesPerDollar) ? ingredients[i] : memo;
			}
			
		} else for (int i = 0; i < ingredients.length; i++) {
			if (key.matches(ingredients[i].name)) { // finds ingredient matching key name
				memo = ingredients[i];
			}
		}
		
		return memo;
		
	}
	
	/* menuInfo
	 * Builds menu data given unique menu items and ingredients set data
	 *
	 * Input: scanner object scan for console inputs, ingredients set data as an Ingredient array
	 * 
	 * Output: an array of MenuItems, with each holding the menu item's respective data
	 * (See MenuItem for more detailed info)
	 * 
	 * Preconditions:
	 * Menu data must be entered with the following form:
	 * 	1) integer input of number of menu items provided by restaurant
	 * 	2) for each menu item, the input must be entered as follows:
	 * 		a) the name of the menu item as an unbroken String
	 * 		b) the number of unique ingredients used as an integer
	 * 		c) for each ingredient used, the input must be entered as follows:
	 * 			i) the name of the ingredient as an unbroken String
	 * 			ii) the number of ounces required for the menu item as a double
	 */
	public static MenuItem[] menuInfo (Scanner scan, Ingredient[] ingredients) {
		MenuItem[] menu = new MenuItem[scan.nextInt()];
		for (int i = 0; i < menu.length; i++) {
			menu[i] = new MenuItem(scan, ingredients);
		}
		return menu;
	}

	/* order
	 * Scans for menu item matching order, then builds ingredient requirement in ingredients list
	 *
	 * Input: scanner object scan for console inputs, menu item data as a MenuItem array, ingredients set 
	 * data as an Ingredient array
	 * 
	 * Output: None (builds ounces required in ingredient list -- see menuPrep for more info)
	 * Ordering ends when input is "EndOrder"
	 * 
	 * Preconditions:
	 * All inputs must be entered as unbroken Strings and match menu items found in menu list
	 */
	public static void order (Scanner scan, MenuItem[] menu, Ingredient[] ingredients) {
		String key = scan.next();
		if (key.matches("EndOrder")) {
			return;
		}
		menuPrep(key, menu, ingredients);
		order(scan, menu, ingredients);		
	}

	/* menuPrep
	 * Takes an order and builds ounces of each ingredient required in order using menu and ingredient list
	 *
	 * Input: String order item name, menu data as a MenuItem array, ingredient set data as an Ingredient array
	 * 
	 * Output: None (builds ounces required in ingredient list)
	 */
	public static void menuPrep (String order, MenuItem[] menu, Ingredient[] ingredients) {
		for (int i = 0; i < menu.length; i++) {
			if (order.matches(menu[i].name)) {
				for (int j = 0; j < menu[i].recipe.length; j++) {
					find(menu[i].recipe[j].name, ingredients).ouncesRequired += menu[i].recipe[j].ouncesRequired;	
				}
			}
		}
	}
	
}

/* Ingredient
 * class to hold ingredient data
 * 
 * constructed with ingredient name, price per ounce, vegetarian status, calories per ounce, and ounces used/required
 */
class Ingredient {
	
	String name;
	double pricePerOunce;
	boolean isVegetarian;
	int caloriesPerOunce;
	double caloriesPerDollar, ouncesRequired = 0;
	
	public Ingredient ( String name, double price, boolean isVeg, int cals, double ounces) {
		this.name = name;
		this.pricePerOunce = price;
		this.isVegetarian = isVeg;
		this.caloriesPerOunce = cals;
		this.caloriesPerDollar = cals / price;
		this.ouncesRequired = ounces;
	}
	
	/* clone
	 * clones this ingredient to menu recipe data with ounces required of this ingredient
	 */
	public Ingredient clone (double ounces) {
		return new Ingredient (this.name, this.pricePerOunce, this.isVegetarian, this.caloriesPerOunce, ounces);
	}
	
	/* orderRequirement
	 * Returns a string in the format:
	 * <IngredientTotalAmount> ounces of <IngredientName>
	 */
	public String orderRequirement() { 
		return String.format("%.2f", this.ouncesRequired) + " ounces of " + this.name;
	}
	
}

/* MenuItem
 * class to hold menu item data
 * 
 * constructed with Scanner object scan and ingredient set data
 * Input MUST be in the following format:
 * 1) Menu item name as an unbroken String
 * 2) Number of ingredients needed as an integer
 * 3) The ingredients needed in this recipe
 */
class MenuItem {
	
	String name; 
	Ingredient[] recipe;
	boolean isVegetarian = true;
	double calories, cost = 0;
	
	public MenuItem ( Scanner scan, Ingredient[] ingredients ) {
		this.name = scan.next();
		this.recipe = new Ingredient[scan.nextInt()];
		for (int i = 0; i < this.recipe.length; i++) {
			this.recipe[i] = ingredientSearch(scan.next(), scan.nextDouble(), ingredients);
		} 
	}
	
	/* ingredientSearch
	 * Method to build ingredient information in menu recipe, and returns a clone of the ingredient 
	 * with ounces required
	 * Also adjusts vegetarian status, cost, and calorie count of the menu item based on ingredients
	 */
	public Ingredient ingredientSearch (String item, double ounces, Ingredient[] ingredients) {	
		for (int i = 0; i < ingredients.length; i++) {
			if (item.matches(ingredients[i].name)) {
				this.isVegetarian = (!ingredients[i].isVegetarian) ? false : this.isVegetarian;
				this.cost += ingredients[i].pricePerOunce * ounces;
				this.calories += ingredients[i].caloriesPerOunce * ounces;
				return ingredients[i].clone(ounces);
			}
		}
		return new Ingredient ("", 0, false, 0, 0);
		
	}
	
	/* summary
	 * Prints a summary of the menu item information with the following format:
	 * <Menu_Item_Name>:
  		 <CalorieCount> calories
  		 $<Cost>
  		 <"Vegetarian" or "Non-Vegetarian">
	 */
	public void summary () {
		System.out.println(this.name + ":");
		System.out.println("  " + ( (int)(this.calories + 0.5) ) + " calories");
		System.out.println("  $" + String.format("%.2f", this.cost));
		if (this.isVegetarian) {
			System.out.println("  Vegetarian");
		} else {
			System.out.println("  Non-Vegetarian");
		}
	}
	
}