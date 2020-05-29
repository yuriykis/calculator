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
public class NoSuchDataFileException extends Exception{
    private String message;
    
    public NoSuchDataFileException(){
        this.message = "No file with required data. The program could not be started";
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
}
