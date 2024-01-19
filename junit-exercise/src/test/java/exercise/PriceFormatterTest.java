package exercise;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;

import price.formatter.PriceFormatter;
import org.junit.jupiter.api.Test;

/**
 * Write your tests for the PriceFormatter here. See the specification of the
 * PriceFormatter and formatPrice method in the PriceFormatter class.
 */
public class PriceFormatterTest {
	
	private PriceFormatter priceFormatter = new PriceFormatter();
	
	@BeforeEach
	void setUp() {
		priceFormatter = new PriceFormatter();
	}

    @Test
    
    
    //muista testata negatiiviset!
    
    void testThousandSeparatorIsSpace() {
    	assertEquals("5 000 €", priceFormatter.formatPrice(5000));
    	assertEquals("123 000 €", priceFormatter.formatPrice(123000));
    	assertEquals("1 000 €", priceFormatter.formatPrice(1000));
    	assertEquals("1 000 €", priceFormatter.formatPrice(1000.00));
    	assertEquals("1 000 €", priceFormatter.formatPrice(999.999));
    	assertEquals("999,99 €", priceFormatter.formatPrice(999.991));
    	assertEquals("10 102,85 €", priceFormatter.formatPrice(10102.85));
    	
    }
    @Test
    void testDecimalSeparatorIsComma() {
    	assertEquals("5 000,02 €", priceFormatter.formatPrice(5000.023));
    	assertEquals("1,23 €", priceFormatter.formatPrice(1.23));
    	assertEquals("5 000 €", priceFormatter.formatPrice(5000.0));
    	assertEquals("0,12 €", priceFormatter.formatPrice(0.1234));
    	assertEquals("1 000 €", priceFormatter.formatPrice(1000.00));
    	assertEquals("1 000 €", priceFormatter.formatPrice(999.996));
    	
    }
    
    @Test
    //toimii
    void testDecimalLengthIsTwo() {
    	assertEquals("1,12 €", priceFormatter.formatPrice(1.1200350));
    	assertEquals("99,99 €", priceFormatter.formatPrice(99.994));
    	assertEquals("100 €", priceFormatter.formatPrice(99.995));
    	assertEquals("0 €", priceFormatter.formatPrice(0.0035));
    	assertEquals("1 000 €", priceFormatter.formatPrice(1000.00));
    	assertEquals("1 000,10 €", priceFormatter.formatPrice(1000.1));
    }

    void testDecimalRoundsToCents() {
    	assertEquals("0,01 €", priceFormatter.formatPrice(0.0051));
    	
    }
    @Test
    void testShowDecimalIfNotZero() {
    	assertEquals("0,01 €", priceFormatter.formatPrice(0.01));
    	assertEquals("0 €", priceFormatter.formatPrice(0.00000));
    	assertEquals("0 €", priceFormatter.formatPrice(0.001));
    	assertEquals("0,99 €", priceFormatter.formatPrice(0.987));
    	assertEquals("1 €", priceFormatter.formatPrice(0.996));
    }
    
    @Test
    //toimii
    void testPriceEndingInEuro() {
    	assertEquals("10 000 112,43 €", priceFormatter.formatPrice(10000112.432));
    	assertEquals("0,99 €", priceFormatter.formatPrice(0.987));
    	assertEquals("0 €", priceFormatter.formatPrice(0.001));
    	assertEquals("-0 €", priceFormatter.formatPrice(-0.001));
    }
    
}
