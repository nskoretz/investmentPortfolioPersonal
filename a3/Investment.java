/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.ArrayList;

/**
 * file     Investment.java
 * @author  Nicholas Skoretz
 * date     2017-11-27
 *
 * desc     The Investment class is a parent abstract class for the Stock and MutualFund
 *          classes.
 */
public abstract class Investment {
    //Attributes
    String      symbol;
    String      name;
    int         quantity;
    double      price;
    double      bookValue;
    ArrayList<String> nameTokens;
    double      gain;

    static ArrayList<ArrayList<String>> investmentNameTokens = new ArrayList<ArrayList<String>>( 20 );

    //Constructors

    /**
     * A four arg constructor for the Investment type. All args are Strings to
     * facilitate the input from GUI fields.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @throws BadSymbolException
     * @throws BadNameException
     * @throws BadQuantityException
     * @throws BadPriceException
     */
    public Investment( String symbol, String name, String quantity,
            String price ) throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        ArrayList<String> defaultNameTokens = new ArrayList<>( 1 );
        defaultNameTokens.add( "-" );

        setSymbol( symbol );
        setName( name );
        setQuantity( quantity );
        setPrice( price );
        setBookValue( 0.0 );
    }

    /**
     * No-Argument constructor for the Investment.
     * @throws a3.BadSymbolException
     * @throws a3.BadPriceException
     * @throws a3.BadNameException
     * @throws a3.BadQuantityException
     */
    public Investment() throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        this ( "-", "-", "0", "0.0" );
    }

    /**
     * Copy constructor for the Investment. Attempts to return a deep copy of
     * the passed Investment. Sadly ArrayList only has clone, which is shallow
     * copy. We haven't learned enough to make a fully robust copy.
     * @param investIn
     */
    public Investment( Investment investIn ) {
        symbol = investIn.symbol;
        name = investIn.name;
        quantity = investIn.quantity;
        price = investIn.price;
        bookValue = investIn.bookValue;
        nameTokens = ( ArrayList<String> )investIn.nameTokens.clone();
        gain = investIn.gain;

    }


    //Accessors
    /**
     * Returns the String value of symbol.
     * @return String symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the String value of name.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the integer value of quantity.
     * @return int quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the double value of price.
     * @return double price.
     */
    public double getPrice() {
        return price;
    }

     /**
     * Returns the double value of bookValue.
     * @return double bookValue
     */
    public double getBookValue() {
        return bookValue;
    }

    /**
     * Returns the ArrayListString value of nameTokens.
     * @return ArrayListString nameTokens.
     */
    public ArrayList<String> getNameTokens() {
        return nameTokens;
    }

    /**
     * Returns the double value of gain.
     * @return double gain.
     */
    public double getGain() {
        return gain;
    }

     /**
     * Allows the calling function access to an ArrayList of all of the
     * tokenized names of all instances of the object and derived objects.
     * @return A list of all of the tokenized names of instances.
     */
    public static ArrayList<ArrayList<String>> getAllInvestmentNameTokens() {
        return investmentNameTokens;
    }


    //Mutators
    /**
     * Allows the safe mutation of the symbol attribute. Rejects an empty
     * string.
     * @param symbol
     * @throws a3.BadSymbolException
     */
    public void setSymbol( String symbol ) throws BadSymbolException {
        if( symbol.isEmpty() ) {
            throw new BadSymbolException();
        } else {
            this.symbol = symbol;
        }
    }

    /**
     * Allows the safe mutation of the name attribute. Rejects and empty
     * string.
     * @param name
     * @throws a3.BadNameException
     */
    public void setName( String name ) throws BadNameException {
        if( name.isEmpty() ) {
            throw new BadNameException();
        } else {
            this.name = name;
        }
    }

    /**
     * Allows for the safe mutation of the quantity attribute. Rejects values
     * lower than 0.
     * @param quantity
     * @throws a3.BadQuantityException
     */
    public void setQuantity( String quantity ) throws BadQuantityException {
        boolean didItWork = false;
        int qty = 0;

        try {
            qty = Integer.parseInt( quantity );
            if( qty > 0 ) {
                this.quantity = qty;
                didItWork = true;
            } else {
                didItWork = false;
            }
        } catch( NumberFormatException e ) {
            didItWork = false;
        }

        if( ! didItWork ) {
            throw new BadQuantityException();
        }
    }

    /**
     * Allows for the safe mutation of the price attribute. Rejects values less
     * than 0.
     * @param price
     * @throws a3.BadPriceException
     */
    public void setPrice( String price ) throws BadPriceException {
        boolean didItWork = false;
        double pr;

        try {
            pr = Double.parseDouble( price );
            if( pr >= 0 ) {
                this.price = pr;
                didItWork = true;
            } else {
                didItWork = false;
            }
        } catch( NumberFormatException e ) {
            didItWork = false;
        }

        if( ! didItWork ) {
            throw new BadPriceException();
        }
    }

    /**
     * Allows for the safe mutation of the bookValue attribute.
     * @param bookValue
     * @return boolean, true on success, false on fail.
     */
    public boolean setBookValue( double bookValue ) {
         boolean didItWork = true;

        this.bookValue = bookValue;

        return didItWork;
    }

    /**
     * Allows for the safe mutation of the nameTokens attribute. Rejects empty
     * ArrayLists.
     * @param nameTokens
     * @return boolean, true on success, false on fail.
     */
    public boolean setNameTokens( ArrayList<String> nameTokens ) {
        boolean didItWork = false;

        if( nameTokens.isEmpty() ) {
            didItWork = false;
        } else {
            this.nameTokens = nameTokens;
            investmentNameTokens.add( this.nameTokens );
            didItWork = true;
        }

        return didItWork;

    }


    //Abstracts

    /**
     *
     * @param fieldHolder
     */
    public abstract void buyExistingInvestment( Investment fieldHolder );

    /**
     *
     * @param invList
     */
    public abstract void buyNewInvestment( ArrayList<Investment> invList );

    /**
     *
     * @param qty
     * @param price
     * @return
     * @throws BadQuantityException
     */
    public abstract String sellInvestment( int qty, double price )
            throws BadQuantityException;

    /**
     *
     */
    public abstract void calculateGain();

    //toString()
    /**
     * Returns all attributes in a readable String format. Overrides parent
     * toString().
     * @return String
     */
    @Override
    public String toString() {
        return "Investment{" + "symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + ", gain=" + gain + '}';
    }


    //equals()
    /**
     * Provides a way to compare Investment objects. Overrides the parent
     * equals().
     * @param obj
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Investment other = ( Investment ) obj;
        if ( this.quantity != other.quantity ) {
            return false;
        }
        if ( this.price != other.price ) {
            return false;
        }
        if ( this.bookValue != other.bookValue ) {
            return false;
        }
        if ( this.gain != other.gain ) {
            return false;
        }
        if ( ! this.symbol.equals( other.symbol ) ) {
            return false;
        }
        if ( ! this.name.equals( other.name ) ) {
            return false;
        }
        if ( ! this.nameTokens.equals( other.nameTokens ) ) {
            return false;
        }
        return true;
    }
}
