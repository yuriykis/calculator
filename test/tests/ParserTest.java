package tests;

import calculator.Parser;
import exceptions.*;
import org.junit.*;

/**
 *
 * @author YuriyKis
 */

public class ParserTest {
    
    Parser parserIntsanse;
    
    @Before
    public void setUp(){
        try {
            parserIntsanse = new Parser("eurofxref-daily.xml");
        } catch (NoSuchDataFileException ex) {
            System.out.println(ex.getMessage()); //exception if the XML file is not in the current directory
        }
    }
    
    @After
    public void tearDown(){
        parserIntsanse = null;
    }
    
    
    @Test
    public void availableCurrencyTest(){
        
        String currency = "PLN";
        boolean ifCurrencyExists = parserIntsanse.checkCurrency(currency);
        Assert.assertEquals(true, ifCurrencyExists);
        
    }
    
    @Test
    public void availableCurrencyLowerCaseTest(){
        
        String currency = "isk";
        boolean ifCurrencyExists = parserIntsanse.checkCurrency(currency);
        Assert.assertEquals(true, ifCurrencyExists);
        
    }
    
    @Test
    public void availableCurrencyWithWhiteSpacesTest(){
        
        String currency = "  AUD ";
        boolean ifCurrencyExists = parserIntsanse.checkCurrency(currency);
        Assert.assertEquals(true, ifCurrencyExists);
        
    }
    
    @Test
    public void badCurrencyTest(){
        
        String currency = "badCurrency";
        boolean ifCurrencyExists = parserIntsanse.checkCurrency(currency);
        Assert.assertEquals(false, ifCurrencyExists);
        
    }
    
    @Test(expected = NoSuchDataFileException.class)
    public void noNodeinXMLFileTest() throws NoSuchDataFileException{
        
       Parser parserIntsanse2 = new Parser("BadFileName");
        
    }
    
    
    
}
