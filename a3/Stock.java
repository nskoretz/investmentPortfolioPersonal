
package a3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * file    Stock.java
 * @author  Nicholas Skoretz
 * date    2017-11-10
 *
 * desc    Implements the required attributes and methods to emulate a stock.
 */
public class Stock extends Investment {
    //Attributes
    public static final double COM_FEE = 9.99;


    //Constructors
    /**
     * No-Argument constructor for the Stock. Calls the four
     * argument constructor with default values.
     * @throws a3.BadSymbolException
     * @throws a3.BadPriceException
     * @throws a3.BadQuantityException
     * @throws a3.BadNameException
     */
    public Stock() throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        this ( "-", "-", "0", "0.0" );
        /*
        this.symbol = "-";
        this.name = "-";
        this.quantity = 0;
        this.price = 0.0;
        calculateGain();
        */
    }

    /**
     * Four argument constructor for the Stock. Will fully create a Stock,
     * calculating the values of the other fields. Will call the constructor of
     * the parent class.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @throws a3.BadSymbolException
     * @throws a3.BadNameException
     * @throws a3.BadPriceException
     * @throws a3.BadQuantityException
     */
    public Stock( String symbol, String name, String quantity, String price )
            throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        super ( symbol, name, quantity, price );
        calculateGain();
    }

    /**
     * Five argument constructor for Stock. This is implemented for the
     * creation of funds from the entry file. It takes into account bookVal
     * from the file instead of calculating the value itself.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @param bookVal
     * @throws a3.BadSymbolException
     * @throws a3.BadNameException
     * @throws a3.BadQuantityException
     * @throws a3.BadPriceException
     */
    public Stock ( String symbol, String name, String quantity, String price,
            double bookVal ) throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        super (symbol, name, quantity, price );
        this.setBookValue( bookVal );
    }

    /**
     * Copy constructor for the Stock. Attempts to return a deep copy of
     * the passed Stock. Sadly ArrayList only has clone, which is shallow
     * copy. We haven't learned enough to make a fully robust copy.
     * @param sIn
     * @throws a3.BadSymbolException
     * @throws a3.BadNameException
     * @throws a3.BadQuantityException
     * @throws a3.BadPriceException
     */

    public Stock ( Stock sIn ) throws BadSymbolException, BadNameException,
            BadQuantityException, BadPriceException {
        symbol = sIn.getSymbol();
        name = sIn.getName();
        quantity = sIn.getQuantity();
        price = sIn.getPrice();
        bookValue = sIn.getBookValue();
        nameTokens = ( ArrayList<String> ) sIn.getNameTokens().clone();
        gain = sIn.getGain();
    }


    @Override
    public void buyNewInvestment( ArrayList<Investment> invList ) {
        String[] splitName = this.getName().split( "\\s" );
        ArrayList<String> splitArray =
                new ArrayList<>( Arrays.asList( splitName ) );
        for ( int i = 0; i < splitArray.size(); i++ ) {
            String temp = splitArray.remove( i );
            String lower = temp.toLowerCase();
            splitArray.add( i, lower );

        }
        this.setNameTokens( splitArray );
        this.setBookValue( ( this.getQuantity() * this.getPrice() ) + COM_FEE);
        investmentNameTokens.add( splitArray );
        invList.add( this );

    }

    @Override
    public void buyExistingInvestment ( Investment fieldsHolder ) {
        boolean runLoop = true;
        double price = fieldsHolder.getPrice();
        double bookValue = this.getBookValue();
        int quantity = fieldsHolder.getQuantity();
        int totalQty = quantity + this.getQuantity();




        bookValue += ( ( quantity * this.getPrice() ) + COM_FEE );
        this.setBookValue( bookValue );
        this.calculateGain();
    }

    /**
     * Implements creating a Stock from parameters in an input file.
     * @param components, five fields parsed from the input file.
     * @return the Stock created or null if failure.
     * @throws a3.BadSymbolException
     * @throws a3.BadPriceException
     * @throws a3.BadQuantityException
     * @throws a3.BadNameException
     */
    public static Stock addStock ( String[] components ) throws
            BadSymbolException, BadNameException, BadQuantityException,
            BadPriceException{
        int failed = 0;
        int quantity = 0;
        double price = 0;
        double bookVal = 0;
        String splitName[];
        ArrayList<String> splitNameArray = new ArrayList<>( 10 );

        try {
            bookVal = Double.parseDouble( components[5] );
        } catch ( NumberFormatException e ) {
            failed++;
        }

        if ( failed == 0 ) {
            Stock toAdd = new Stock ( components[1], components[2],
                    components[3], components[4], bookVal );
            toAdd.calculateGain();
            splitName = toAdd.getName().split( "\\s" );

            splitNameArray = new ArrayList<>( Arrays.asList( splitName ) );
            for ( int i = 0; i < splitNameArray.size(); i++ ) {
                String temp = splitNameArray.remove( i );
                String lower = temp.toLowerCase();
                splitNameArray.add( i, lower );

            }
            toAdd.setNameTokens( splitNameArray );
            investmentNameTokens.add( splitNameArray );
            return toAdd;
        } else {
            System.out.print ( "\nCannot parse the investment.\n" );
            return null;
        }
    }


    //toString()
    /**
     * Returns all attributes in a readable String format.
     * @return String
     */
    @Override
    public String toString() {
        return "Stock { " + "symbol = " + symbol + ", name = " + name + ", quantity = " + quantity + ", price = " + price + ", bookValue = " + bookValue + ", gain = " + gain + " }";
    }


    //equals()
    /**
     * Provides a way to compare Stock objects. Overrides the parent
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
        final Investment other = ( Stock ) obj;
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


    //Public Functions
    @Override
    public String sellInvestment( int qtyToSell, double priceIn )
            throws BadQuantityException {
        double bookVal;
        double cashOut;
        String toReturn = "";

        if( qtyToSell > this.getQuantity() ) {
            throw new BadQuantityException( "You cannot sell more than you have!" );
        } else {
            cashOut = ( qtyToSell * priceIn ) - COM_FEE;

            bookVal = this.getBookValue() *
                    ( ( double ) ( this.getQuantity() - qtyToSell )
                    / ( double ) this.getQuantity() );
            this.setBookValue( bookVal );
            this.setQuantity( ( ( Integer ) ( this.getQuantity() - qtyToSell ) ).toString() );
            try {
                this.setPrice( ( ( Double ) priceIn ).toString() );
            } catch( BadPriceException e ) {}
            toReturn = "You have made " + cashOut + " from this sale and "
                    + this.getQuantity()
                    + " units remain in the investment!\n";
        }

        return toReturn;
    }



    /**
     * Calculates the gain of the instance object. This function is the only
     * way to 'set' the gain attribute, as it doesn't have a public mutator.
     */
    @Override
    public void calculateGain() {
        //Declarations
        int qty;
        double sharePrice;
        double bookVal;
        double locGain;

        //Initializations
        qty = getQuantity();
        sharePrice = getPrice();
        bookVal = getBookValue();
        locGain = 0;

        //Body
        locGain = ( ( qty * sharePrice ) - 9.99 ) - bookVal;
        this.gain = locGain;

    }


}
