package calculator;

import exceptions.*;
import java.io.IOException;

/**
 *
 * @author YuriyKis
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
        
        try{
            Calculator calculator = new Calculator();
            calculator.start();
        }
        catch(NoSuchDataFileException e){
            System.out.println(e.getMessage()); //exception if the XML file is not in the current directory
        }
        catch(IOException e){
            System.out.println("Title file cannot be found");
        }
       
    }

    public static void clrscr(){ //screen cleaning method
        
        try {

            if (System.getProperty("os.name").contains("Windows")){

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{

                Runtime.getRuntime().exec("clear");
            }

        }catch (IOException | InterruptedException ex) {
            System.out.println("Cannot clean the screen");
        }

    }
}
