package calculator;

import exceptions.*;
import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author YuriyKis
 */

public class Parser {

    private File inputFile;
    private Document doc;
    private int actualNode;
    private String actualNodeName;
    
    public Parser(String fileName) throws NoSuchDataFileException{
        this.actualNode = -1;                                           //the value -1 means that there is no node with the given attribute in the XML file
        this.actualNodeName = null;
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            this.inputFile = new File(fileName);
            
            this.doc = builder.parse(this.inputFile);
            this.doc.getDocumentElement().normalize();            
        } 
        catch (ParserConfigurationException | SAXException e) {
            System.out.println("Parser configuration error");
            System.exit(0);
        }
        catch(IOException e){
            throw new NoSuchDataFileException();
        }
    }
    
    
    //the method checkCurrency checks if the XML file contains a node 
    //with a given currency value and writes the node number
    
    public boolean checkCurrency(String currency){
        
        currency = currency.replaceAll(" ", ""); //remove any whitespaces from currency string
        currency = currency.toUpperCase();
        
        NodeList nList = this.doc.getElementsByTagName("Cube");
        
        for(int i = 0; i < nList.getLength(); i++){
            
            Node node = nList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE){
                
                Element element = (Element) node;
                
                if(element.hasAttribute("currency") && element.getAttribute("currency").equals(currency)){
                    this.actualNodeName = currency;
                    this.actualNode = i;
                    return true;
                }
            }
        } 
        this.actualNodeName = null;
        this.actualNode = -1;
        return false;
    } 
    
    
    //the method getCurrencyRate returns the value of the rate attribute using actualNode variable
    //or throws an exception if the XML file does not contain a node or if the method checkCurrency has never been called
    
   public Double getCurrencyRate() throws NoSuchNodeException{
       
       if(this.actualNode == -1){
          throw new NoSuchNodeException();
       }

       NodeList nList = this.doc.getElementsByTagName("Cube");
       Node node = nList.item(this.actualNode);
       Element element = (Element) node;

       String stringRate = element.getAttribute("rate");

       return  Double.parseDouble(stringRate);

   }
   
   //the method returns all currencies from the XML file
   
   public ArrayList<String> getAllCurrencies(){
       
       NodeList nList = this.doc.getElementsByTagName("Cube");
       ArrayList<String> allCurrencies = new ArrayList<>();
       
        for(int i = 0; i < nList.getLength(); i++){
            
            Node node = nList.item(i);
            
            if(node.getNodeType() == Node.ELEMENT_NODE){
                
                Element element = (Element) node;
                
                if(element.hasAttribute("currency")){
                   allCurrencies.add(element.getAttribute("currency"));
                }
            }
        } 
        return allCurrencies;
   }
   
   
   public String getNodeName(){
       return this.actualNodeName;
   }
}
