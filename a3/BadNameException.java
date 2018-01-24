/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 * file     BadNameException.java
 * @author  Nicholas Skoretz
 * date     2017-11-27
 * 
 * desc     This exception subclass is thrown when the String name attribute
 *          of an Investment class (or subclasses) is invalid.
 */
public class BadNameException extends Exception {
    public BadNameException() {
        super("The name is incorrect.");
    }
    
    public BadNameException(String message) {
        super(message);
    }
}
