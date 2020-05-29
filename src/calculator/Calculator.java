package calculator;

import exceptions.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author YuriyKis
 */

public class Calculator {
    
    private Parser parser;
    private DecimalFormat dFormat;
    private ArrayList<String> allCurrencies;
    
    public Calculator() throws NoSuchDataFileException{
        parser = new Parser("eurofxref-daily.xml");
        dFormat = new DecimalFormat("##.00");               //the resulting number is rounded to 2 decimal places
        allCurrencies = parser.getAllCurrencies();
    }
    
    public void start() throws IOException{
        
        Scanner in = new Scanner(System.in);
        
        while (true) {
            
            Main.clrscr();
            writeTitle();
   
            try {
                    
                System.out.print("\nCurrency: ");
                String currency = in.nextLine();
                
                if(currency.equalsIgnoreCase("q")){
                    System.exit(0);
                }
            
                System.out.print("\nRate (EUR): ");
                String stringInputRate = in.nextLine();
                
                String textResult = calculations(currency, stringInputRate);    //call a method that performs main calculations
                System.out.print(textResult);                                   //print result string
                in.nextLine();
                    
            }
            catch (NoSuchNodeException ex) {
                System.out.println(ex.getMessage());
                in.nextLine();
            }
            catch(NumberFormatException e){
                System.out.println("The rate value entered is not valid. Please provide the correct value. Press enter to continue...");
                in.nextLine();
            }
               
           
        }
           
    }
    

    public String calculations(String currency, String stringInputRate) throws NoSuchNodeException, NumberFormatException{
        
            if(currency.isEmpty() || stringInputRate.isEmpty()){
                return "The fields cannot be empty. Please provide the correct values. Press enter to continue...";
            }
            
            Double inputRate = Double.parseDouble(stringInputRate);
            
            if(inputRate < 0){    
                return "\nThe rate value cannot be negative. Please provide the correct value. Press enter to continue...";
            }
            
            if(inputRate >= 1000000000000000.0){
                return "\nThe value entered is too large. Please enter a smaller number. Press enter to continue...";
            }
            
            if(parser.checkCurrency(currency)){ 
              
                Double rate = this.parser.getCurrencyRate();
                Double result = inputRate*rate;
                
                return ("\nResult: " + dFormat.format(result) +"\nRate of exchange: " + inputRate + " EUR = " + dFormat.format(result) + " " +
                                this.parser.getNodeName() + "\nPress enter to continue...");
       
            }
            else{
                return "\nThe currency provided is not valid. Please provide the correct value. Press enter to continue...";
            }
    }
    

    private void writeTitle() throws IOException{
        
        //print text from title file
        BufferedReader in = new BufferedReader(new FileReader("title.txt"));
        String line = in.readLine();
        
        while(line != null){
            
          System.out.println(line);
          line = in.readLine();
          
        }
        in.close();
        
        //print all currencies available in the XML file
        int printLineFlag = 0;
        for(int i = 0; i < this.allCurrencies.size(); i++){
            
            System.out.print(this.allCurrencies.get(i) + " ");
           
            printLineFlag++;
            if(printLineFlag == 8){
                System.out.print("\n"); //8 currencies are displayed on one line
                printLineFlag = 0;
            }
        }
    }
    
}
