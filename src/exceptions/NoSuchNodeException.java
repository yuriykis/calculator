/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author YuriyKis
 */
public class NoSuchNodeException extends Exception{
    
    private String message;
    
    public NoSuchNodeException(){
        this.message = "There is no such node in the input file";
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
}
