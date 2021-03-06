/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 * file     BadPriceException.java
 * @author  Nicholas Skoretz
 * date     2017-11-27
 * 
 * desc     This exception subclass is thrown when the String price
 *          attribute of an Investment class (or subclasses) is invalid.
 */
public class BadPriceException extends Exception {
    public BadPriceException() {
        super("The price is incorrect.");
    }
    
    public BadPriceException(String message) {
        super(message);
    }
}
