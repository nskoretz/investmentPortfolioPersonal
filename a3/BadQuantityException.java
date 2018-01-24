/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 * file     BadQuantityException.java
 * @author  Nicholas Skoretz
 * date     2017-11-27
 * 
 * desc     This exception subclass is thrown when the String quantity
 *          attribute of an Investment class (or subclasses) is invalid.
 */
public class BadQuantityException extends Exception {
    public BadQuantityException() {
        super("The quantity is incorrect.");
    }
    
    public BadQuantityException(String message) {
        super(message);
    }
}
