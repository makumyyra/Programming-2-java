package inheritance.webshop;

import java.util.Date;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * The Ticket class represents a concert or sports event ticket in a webshop.
 * Like the Vehicle class, the Ticket class should inherit the Product class.
 *
 * In real life a ticket would likely have additional properties, such as the
 * event date and time (LocalDateTime). In this exercise, you can decide
 * yourself which properties you want to add. You can also decide yourself how
 * the toString() method should look like.
 *
 * This class will not be autograded, so it is up to you to test it. You can
 * also skip this class and continue with the next part of the exercise if you
 * feel that you have understood the concept of inheritance.
 */
public class Ticket extends Product {

	Date input = new Date(); //tämä olisi nyt oikeasti vaikka localDate
	int seatNumber;
	BigInteger barcodeNumber;
	
	public Ticket(String title, String description, double price, Date input, int seatNumber,
			BigInteger barcodeNumber) {
		super(title, description, price);
		this.input = input;
		this.seatNumber = seatNumber;
		this.barcodeNumber = barcodeNumber;
	}

	public Date getInput() {
		return input;
	}

	public void setInput(Date input) {
		this.input = input;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public BigInteger getBarcodeNumber() {
		return barcodeNumber;
	}

	public void setBarcodeNumber(BigInteger barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	@Override
	public String toString() {
		return "Ticket [input=" + input + ", seatNumber=" + seatNumber + ", barcodeNumber=" + barcodeNumber + ", title="
				+ title + ", description=" + description + ", price=" + price + "]";
	}
	
	
	
	
}
