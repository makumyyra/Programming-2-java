package interfaces.markdown;

import java.util.List;

/**
 * This class represents pizzas. A pizza has a name and a price as well as a
 * list of toppings. Each topping is represented by a string, which you can
 * assume is always in lower case.
 *
 * Your task is to implement the MarkdownExport interface in this class. The
 * exportMarkdown() method should return a markdown representation of the pizza
 * object. The markdown representation should look something like this:
 *
 *
 * # Hawaiian
 *
 * Toppings:
 * - ham
 * - pineapple
 * - mozarella
 *
 * Price: 10.90
 */

 
//example: private final Pizza margherita = new Pizza("Margherita", 8.99, List.of("tomato sauce", "mozzarella", "basil"));


public class Pizza implements MarkdownExport {

    private final String name;
    private final double price;
    private final List<String> toppings;

    public Pizza(String name, double price, List<String> toppings) {
        this.name = name;
        this.price = price;
        this.toppings = toppings;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getToppings() {
        return toppings;
    }

	@Override
	public String exportMarkdown() {
		String menuItem = "";
		
		menuItem += "# " + this.name + "\n\nToppings:";
		for (String topping : toppings) {
			menuItem += "- " + topping;
		}
		menuItem += "\nPrice: " + this.price;
	
		return menuItem;
	}

}
