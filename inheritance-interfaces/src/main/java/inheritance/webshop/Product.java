package inheritance.webshop;

import interfaces.markdown.MarkdownExport;

/**
 * This class represents products in a webshop application. The class will be
 * used as a base class for various product types in the next parts of the
 * exercise.
 *
 * You will need to add the following private instance variables to the class:
 * title (string), description (string) and price (double). The class already
 * has a constructor that takes the values as arguments, but you need to
 * complete its implementation.
 *
 * Also, create getter methods getTitle(), getDescription() and getPrice() to
 * return the values of the instance variables.
 *
 * Finally, create a toString() method that returns a string representation of
 * the product. You can decide yourself how the string should look like, but it
 * must contain the title, description and price of the product.
 *
 * Note that this class itself does not utilize inheritance, but it will
 * extended by other classes in the exercise.
 */
public class Product implements MarkdownExport {

	String title;
	String description;
	double price;
	
    public Product(String title, String description, double price) {
    	this.title = title;
    	this.description = description;
    	this.price = price;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", price=" + price + "]";
	}
	
	@Override
	public String exportMarkdown() {
	String productDetails = "";
			
		productDetails += "Product name: " + this.title;
		productDetails += "\nProduct description: " + this.description;
		productDetails += "\nProduct price: " + this.price;
	
		return productDetails;
	}
    
    
}
