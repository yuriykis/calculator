package tests;

import calculator.Calculator;
import exceptions.*;
import org.junit.*;

/**
 *
 * @author YuriyKis
 */

public class CalculatorTest{
    
    Calculator calculatorIntsanse;
    
    @Before
    public void setUp(){
        try {
            calculatorIntsanse = new Calculator();
        } catch (NoSuchDataFileException ex) {
            System.out.println(ex.getMessage()); //exception if the XML file is not in the current directory
        }
    }
    
    @After
    public void tearDown(){
        calculatorIntsanse = null;
    }
    
    
    @Test
    public void correctValuesTest() throws NoSuchNodeException{
        
         String currency = "PLN";
         String rate = "12.0";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("\nResult: 53.25\nRate of exchange: 12.0 EUR = 53.25 PLN\nPress enter to continue...", result);
    }
    
    @Test
    public void lowerCaseCurrencyTest() throws NoSuchNodeException{
        
         String currency = "dkk";
         String rate = "123.0";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("\nResult: 917.37\nRate of exchange: 123.0 EUR = 917.37 DKK\nPress enter to continue...", result);
    }
    
    @Test
    public void badCurrencyValueTest() throws NoSuchNodeException{
        
         String currency = "qwerty";
         String rate = "123.0";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("\nThe currency provided is not valid. Please provide the correct value. Press enter to continue...", result);
    }
    
    @Test
    public void emptyCurrencyValueTest() throws NoSuchNodeException{
        
         String currency = "";
         String rate = "123.0";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("The fields cannot be empty. Please provide the correct values. Press enter to continue...", result);
    }
    
    @Test
    public void emptyValuesTest() throws NoSuchNodeException{
        
         String currency = "";
         String rate = "";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("The fields cannot be empty. Please provide the correct values. Press enter to continue...", result);
    }
    
    @Test
    public void negativeRateValueTest() throws NoSuchNodeException{
        
         String currency = "ISK";
         String rate = "-123";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("\nThe rate value cannot be negative. Please provide the correct value. Press enter to continue...", result);
    }
    
    @Test
    public void tooLargeRateValueTest() throws NoSuchNodeException{
        
         String currency = "HDK";
         String rate = "102947392364918274";
         String result = calculatorIntsanse.calculations(currency, rate);
         
         Assert.assertEquals("\nThe value entered is too large. Please enter a smaller number. Press enter to continue...", result);
    }
    
    @Test(expected = NumberFormatException.class)
    public void badRateFormatTest() throws NoSuchNodeException{
        
         String currency = "NOK";
         String rate = "asdfgh";
         String result = calculatorIntsanse.calculations(currency, rate);
         
    }
    
}

