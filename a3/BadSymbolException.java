/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 * file     BadSymbolException.java
 * @author  Nicholas Skoretz
 * date     2017-11-27
 * 
 * desc     This exception subclass is thrown when the String symbol attribute
 *          of an Investment class (or subclasses) is invalid.
 */
public class BadSymbolException extends Exception {
    public BadSymbolException() {
        super("The symbol is incorrect.");
    }
    
    public BadSymbolException(String message) {
        super(message);
    }
}
