package a3;

import static a3.MutualFund.addFund;
import static a3.Stock.addStock;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * file     Portfolio.java
 * @author  Nicholas Skoretz
 * date     2017-11-29
 *
 * Implements the GUI and runs the program.
 */
public class Portfolio extends JFrame {
    //Attributes
    public static final int HEIGHT = 900;
    public static final int WIDTH = 1600;
    public static final Dimension paneSize = new Dimension ( HEIGHT, WIDTH );
    public static final String[] cBoxStrings = { "stock", "mutualfund" };

    public static ArrayList<Investment> investmentList;
    public static HashMap<String, ArrayList<Integer>> index;
    public static int currentPane = 0;
    /*0 is pLanding
      1 is pBuy
      2 is pSell
      3 is pUpdate
      4 is pGain
      5 is pSearch
    */
    public static int indexOfInvestList = 0;
    public static Set<String> keys;
    public String selectedInvestmentType = "";

    private JPanel pLanding;
    private JPanel pBuy;
    private JPanel pSell;
    private JPanel pUpdate;
    private JPanel pGain;
    private JPanel pSearch;



    //Window Listener
    /**
     * This inner class is the window listener for the GUI. Makes sure to save
     * investments upon exit using the 'X' button.
     */
    public class CheckForExit implements WindowListener {
        @Override
        public void windowOpened( WindowEvent e ) {
        }

        @Override
        public void windowClosing( WindowEvent e ) {
            saveInvestments();
        }
        @Override
        public void windowClosed( WindowEvent e ) {}
        @Override
        public void windowIconified( WindowEvent e ) {}
        @Override
        public void windowDeiconified( WindowEvent e ) {}
        @Override
        public void windowActivated( WindowEvent e ) {

        }
        @Override
        public void windowDeactivated( WindowEvent e ) {}
    }


    //Constructors
    /**
     * No-Arg constructor for my GUI instance. This constructor handles all of
     * the component building.
     */
    public Portfolio() {
        //L1 Declare properties of the JFrame
        super( "Investment Portfolio" );
        this.setSize( WIDTH, HEIGHT );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLayout( new FlowLayout() );

        //Declarations
        GridBagConstraints cons = new GridBagConstraints();
        //GridBagLayout gridBag = new GridBagLayout();


        JMenuBar topBar;
            JMenu mCommands;
                JMenuItem miBuy;
                JMenuItem miSell;
                JMenuItem miUpdate;
                JMenuItem miGain;
                JMenuItem miSearch;
                JMenuItem miQuit;
        //JPanel pLanding;
            JTextArea tcWelcome;
        //JPanel pBuy;
            JPanel pBuyFields;
                JLabel lBuyTitle;
                JPanel pBuyFieldsAndLabels;
                    JPanel pType;
                        JLabel lType;
                        JComboBox investmentCBox;
                    JPanel pSymbol;
                        JLabel lSymbol;
                        JTextField fSymbol;
                    JPanel pName;
                        JLabel lName;
                        JTextField fName;
                    JPanel pQty;
                        JLabel lQty;
                        JTextField fQty;
                    JPanel pPrice;
                        JLabel lPrice;
                        JTextField fPrice;
            JPanel pBuyButtons;
                JButton buyReset;
                JButton buyBuy;
            JPanel pBuyWarnings;
                JLabel bMessages;
                JTextArea jtaBuyWarning;
                JScrollPane visibleWarn;
        //JPanel pSell;
            JLabel lSellTitle;
            JLabel lsSymbol;
            JTextField fsSymbol;
            JLabel lsQty;
            JTextField fsQty;
            JLabel lsPrice;
            JTextField fsPrice;
            JButton sellReset;
            JButton sellSell;
            JLabel lsMessage;
            JTextArea jtaSellWarn;
            JScrollPane sellWarn;
        //JPanel pUpdate
            JLabel ulTitle;
            JLabel ulSymbol;
            JLabel ulName;
            JLabel ulPrice;
            JTextField ufSymbol;
            JTextField ufName;
            JTextField ufPrice;
            JButton ubPrev;
            JButton ubNext;
            JButton ubSave;
            JLabel ulMessage;
            JTextArea utWarn;
            JScrollPane uWarn;
        //JPanel pGain
            JLabel glTitle;
            JLabel glGain;
            JTextField gfGain;
            JLabel glMessage;
            JTextArea gtWarn;
            JScrollPane gWarn;
        //JPanel pSearch;
            JLabel selTitle;
            JLabel selSymbol;
            JLabel selKeys;
            JLabel sellPrice;
            JLabel selhPrice;
            JTextField sefSymbol;
            JTextField sefKeys;
            JTextField seflPrice;
            JTextField sefhPrice;
            JButton sebReset;
            JButton sebSearch;
            JLabel selMessage;
            JTextArea setWarn;
            JScrollPane seWarn;




        //JPanel 'pLanding' and children components
        // <editor-fold>
        pLanding = new JPanel( new GridLayout( 1, 1 ) );

        tcWelcome = new JTextArea( 100, 145 );
        tcWelcome.setText( "Welcome to the Investment Portfolio by " );
        tcWelcome.append( "Nicholas Skoretz.\n\n\n" );
        tcWelcome.append( "Choose an action that you would like to perform " );
        tcWelcome.append( "from the 'Commands' menu. Upon quitting, the ");
        tcWelcome.append( "program automatically saves your investments so " );
        tcWelcome.append( "that you can access them when you next open " );
        tcWelcome.append( "the program!" );

        //TODO
        if ( ! investmentList.isEmpty() ) {
            for ( Investment ele : investmentList ) {
                tcWelcome.append( "TODO" + ele + "\n" );
            }
        } else {
            tcWelcome.append( "\n\nTHERE ARE NO INVESTMENTS.\n");
        }
        tcWelcome.setEditable( false );
        tcWelcome.setLineWrap( true );

        pLanding.add( tcWelcome );
        pLanding.setVisible( true );
        this.add( pLanding );
        // </editor-fold>

        //JPanel 'pBuy' and all its coponents
        // <editor-fold>
        //L1
        pBuy = new JPanel( new GridBagLayout() );
        //L2
        pBuyFields = new JPanel( new GridLayout( 2, 1) );
        pBuyButtons = new JPanel( new GridLayout( 2, 1 ) );
        pBuyWarnings = new JPanel( new FlowLayout() );
        //L3
        lBuyTitle = new JLabel( "Buying an Investment" );
        pBuyFieldsAndLabels = new JPanel ( new GridLayout( 5, 2, 0, 20 ) );
        bMessages = new JLabel( "Messages" );
        jtaBuyWarning = new JTextArea( 29, 136 );
        jtaBuyWarning.setEditable( false );
        visibleWarn = new JScrollPane( jtaBuyWarning );
        visibleWarn.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        visibleWarn.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        //L4
        pType = new JPanel( new GridLayout( 1, 2 ) );
        pSymbol = new JPanel( new GridLayout( 1, 2 ) );
        pName = new JPanel( new GridLayout( 1, 2 ) );
        pQty = new JPanel( new GridLayout( 1, 2 ) );
        pPrice = new JPanel( new GridLayout( 1, 2 ) );
        //L5
        lType = new JLabel( "Type" );
        investmentCBox = new JComboBox( cBoxStrings );
        investmentCBox.setSelectedIndex( 0 );
        investmentCBox.setEditable( false );

        lSymbol = new JLabel( "Symbol" );
        fSymbol = new JTextField();

        lName = new JLabel( "Name" );
        fName = new JTextField();

        lQty = new JLabel( "Quantity" );
        fQty = new JTextField();

        lPrice = new JLabel( "Price" );
        fPrice = new JTextField();

        buyReset = new JButton( "Reset" );
        buyReset.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                //TODO here we must reset all text fields
                fSymbol.setText( null );
                fName.setText( null );
                fQty.setText( null );
                fPrice.setText( null );
            }
        });
        buyBuy = new JButton( "Buy" );
        buyBuy.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                //Get all the fields
                boolean isExisting = false;
                boolean isInputValid = false;
                Investment tempInvest = null;
                Investment testInvest = null;
                String symbol = fSymbol.getText();
                String name = fName.getText();
                String qty = fQty.getText();
                String price = fPrice.getText();
                String type = (String) investmentCBox.getSelectedItem();

                //To check if inputs are valid, make a test investment.
                if ( type.equals( "stock" ) ) {
                    try {
                        Stock testStock = new Stock( symbol, name, qty,
                                price );
                        isInputValid = true;
                        testInvest = ( Investment ) testStock;
                    } catch( BadSymbolException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadNameException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadQuantityException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadPriceException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    }
                } else {
                    try {
                        MutualFund testFund = new MutualFund( symbol, name,
                                qty, price );
                        isInputValid = true;
                        testInvest = ( Investment ) testFund;
                    } catch( BadSymbolException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadNameException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadQuantityException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    } catch( BadPriceException f ) {
                        jtaBuyWarning.append( f.getMessage() + "\n" );
                    }
                }

                if( isInputValid ) {
                    fSymbol.setText( null );
                    fName.setText( null );
                    fQty.setText( null );
                    fPrice.setText( null );
                    //If the 'symbol' is non-empty, search the list for matching symbol
                    for ( int i = 0; i < investmentList.size(); i++ ) {
                        if ( investmentList.get( i ).getSymbol().equals( symbol ) ) {
                            //If there is a symbol match, end loop, buyExistingInvestment
                            tempInvest = investmentList.get( i );
                            i = investmentList.size();
                            tempInvest.buyExistingInvestment( testInvest );
                            jtaBuyWarning.append( "Investment added too!" );
                            jtaBuyWarning.append( "\t" + testInvest + "\n" );
                            isExisting = true;
                        } //Else, its a new Investment
                    }

                    if (  !isExisting) {
                        //If the investment doesnt exist, buy it new!
                        testInvest.buyNewInvestment( investmentList );
                        for ( String ele : testInvest.getNameTokens() ) {
                            if ( index.containsKey( ele ) ) {
                                ArrayList<Integer> temp = index.get( ele );
                                temp.add( investmentList.indexOf ( testInvest ) );
                            } else {
                                ArrayList<Integer> temp = new ArrayList();
                                temp.add( investmentList.indexOf ( testInvest ) );
                                index.put( ele, temp );

                            }
                        }
                        jtaBuyWarning.append( "Investment added!" );
                        jtaBuyWarning.append( "\t" + testInvest + "\n" );

                    }
                }
            }
        });



        //L5
        pType.add( lType );
        pType.add( investmentCBox );

        pSymbol.add( lSymbol );
        pSymbol.add (fSymbol );

        pName.add( lName );
        pName.add( fName );

        pQty.add( lQty );
        pQty.add( fQty );

        pPrice.add( lPrice );
        pPrice.add( fPrice );
        //L4
        pBuyFieldsAndLabels.add( pType );
        pBuyFieldsAndLabels.add( pSymbol );
        pBuyFieldsAndLabels.add( pName );
        pBuyFieldsAndLabels.add( pQty );
        pBuyFieldsAndLabels.add( pPrice );
        //L3
        pBuyFields.add(lBuyTitle );
        pBuyFields.add( pBuyFieldsAndLabels );
        pBuyButtons.add( buyReset );
        pBuyButtons.add( buyBuy );
        pBuyWarnings.add( bMessages );
        pBuyWarnings.add( visibleWarn );
        //L2
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 2.0;
        cons.weighty = 2.0;
        pBuy.add( pBuyFields, cons );
        cons.weightx = 1.0;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        pBuy.add( pBuyButtons, cons);
        cons.weightx = 0.0;
        cons.weighty = 1.0;
        cons.gridheight = GridBagConstraints.REMAINDER;
        pBuy.add( pBuyWarnings, cons);

        //pBuy.setLayout( gridBag );
        pBuy.setMinimumSize( paneSize );
        pBuy.validate();
        pBuy.setVisible( false );
        //L1
        this.add( pBuy );
        // </editor-fold>

        //JPanel 'pSell' and all its components
        // <editor-fold>
        pSell = new JPanel( new GridBagLayout() );

        lSellTitle = new JLabel( "Sell an Investment" );
        lsSymbol = new JLabel( "Symbol" );
        fsSymbol = new JTextField( 20 );
        lsQty = new JLabel( "Quantity" );
        fsQty = new JTextField( 20 );
        lsPrice = new JLabel( "Price" );
        fsPrice = new JTextField( 20 );
        lsMessage = new JLabel( "Messages" );
        jtaSellWarn = new JTextArea( 29, 144 );
        jtaSellWarn.setEditable( false );
        sellWarn = new JScrollPane( jtaSellWarn );
        sellWarn.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        sellWarn.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
                sellReset = new JButton( "Reset" );
        sellReset.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                fsSymbol.setText( null );
                fsQty.setText( null );
                fsPrice.setText( null );
            }
        });
        sellSell = new JButton( "Sell" );
        sellSell.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                //Get all the fields
                boolean isExisting = false;
                boolean isInputValid = false;
                boolean soldOut = false;
                Investment tempInvest = null;
                String sCatch = "";
                String symbol = fsSymbol.getText();
                String qty = fsQty.getText();
                String price = fsPrice.getText();

                try {
                    Stock fieldTesting = new Stock( symbol, "_", qty, price );

                    //If the input is conforming, search for the symbol
                    for( int i = 0; i < investmentList.size(); i++ ) {
                        tempInvest = investmentList.get( i );
                        if( tempInvest.getSymbol().equals( symbol ) ) {
                            //If the symbols match, call sell
                            try {
                                sCatch = tempInvest.sellInvestment(
                                        fieldTesting.getQuantity(),
                                        fieldTesting.getPrice() );
                                fsSymbol.setText( null );
                                fsQty.setText( null );
                                fsPrice.setText( null );
                            } catch( BadQuantityException f ) {
                                jtaSellWarn.append( f.getMessage() + "\n" );
                                if ( f.getMessage().length() > 40 ) {
                                    soldOut = true;
                                }
                            }
                            i = investmentList.size();
                            jtaSellWarn.append( sCatch );
                        }
                    }
                    if ( tempInvest != null ) {
                        if( soldOut ) {
                            int removedIndex = investmentList.indexOf( tempInvest );
                            investmentList.remove( tempInvest );
                            for ( String element : index.keySet() ) {
                                ArrayList<Integer> ints = index.get( element );
                                for ( int i = 0; i < ints.size(); i++) {
                                    if (ints.get( i ) > removedIndex ) {
                                        Integer toReduce = ints.remove ( i );
                                        toReduce--;
                                        ints.add ( i, toReduce );
                                    }
                                }
                                if ( ints.contains ( removedIndex ) ) {
                                    ints.remove ( ( Integer ) removedIndex );
                                }
                            }
                        }
                    }
                } catch( BadSymbolException f ) {
                    jtaSellWarn.append( f.getMessage() + "\n" );
                } catch( BadQuantityException f ) {
                    jtaSellWarn.append( f.getMessage() + "\n" );
                } catch( BadPriceException f ) {
                    jtaSellWarn.append( f.getMessage() + "\n" );
                } catch( BadNameException f ) {}


            }
        });


        GridBagConstraints cSellTitle = new GridBagConstraints();
        cSellTitle.fill = GridBagConstraints.BOTH;
        cSellTitle.anchor = GridBagConstraints.WEST;
        cSellTitle.gridx = 0;
        cSellTitle.gridy = 0;
        cSellTitle.weighty = 2.0;
        cSellTitle.ipady = 50;
        pSell.add( lSellTitle, cSellTitle);

        GridBagConstraints clsSymbol = new GridBagConstraints();
        clsSymbol.fill = GridBagConstraints.BOTH;
        clsSymbol.anchor = GridBagConstraints.LINE_END;
        clsSymbol.gridx = 0;
        clsSymbol.gridy = 1;
        clsSymbol.weighty = 1.0;
        clsSymbol.ipady = 50;
        pSell.add( lsSymbol, clsSymbol );

        GridBagConstraints cfsSymbol = new GridBagConstraints();
        cfsSymbol.fill = GridBagConstraints.BOTH;
        cfsSymbol.anchor = GridBagConstraints.CENTER;
        cfsSymbol.gridx = 1;
        cfsSymbol.gridy = 1;
        pSell.add( fsSymbol, cfsSymbol );

        GridBagConstraints cSellReset = new GridBagConstraints();
        cSellReset.fill = GridBagConstraints.BOTH;
        cSellReset.anchor = GridBagConstraints.CENTER;
        cSellReset.gridx = 2;
        cSellReset.gridy = 0;
        cSellReset.gridheight = 2;
        cSellReset.insets = new Insets( 20, 150, 20, 150 );
        pSell.add( sellReset, cSellReset );

        GridBagConstraints clsQty = new GridBagConstraints();
        clsQty.fill = GridBagConstraints.BOTH;
        clsQty.anchor = GridBagConstraints.CENTER;
        clsQty.gridx = 0;
        clsQty.gridy = 2;
        clsQty.weightx = 1.0;
        clsQty.weighty = 1.0;
        clsQty.ipady = 50;
        pSell.add( lsQty, clsQty );

        GridBagConstraints cfsQty = new GridBagConstraints();
        cfsQty.fill = GridBagConstraints.BOTH;
        cfsQty.anchor = GridBagConstraints.WEST;
        cfsQty.gridx = 1;
        cfsQty.gridy = 2;
        cfsQty.weightx = 1.0;
        pSell.add( fsQty, cfsQty );

        GridBagConstraints cSellSell = new GridBagConstraints();
        cSellSell.anchor = GridBagConstraints.CENTER;
        cSellSell.gridx = 2;
        cSellSell.gridy = 2;
        cSellSell.gridheight = 2;
        cSellSell.weightx = 1.0;
        cSellSell.fill = GridBagConstraints.BOTH;
        cSellSell.insets = new Insets( 20, 150, 20, 150 );
        pSell.add( sellSell, cSellSell );

        GridBagConstraints clsPrice = new GridBagConstraints();
        clsPrice.fill = GridBagConstraints.BOTH;
        clsPrice.anchor = GridBagConstraints.CENTER;
        clsPrice.gridx = 0;
        clsPrice.gridy = 3;
        clsPrice.weighty = 1.0;
        clsPrice.ipady = 50;
        pSell.add( lsPrice, clsPrice );

        GridBagConstraints cfsPrice = new GridBagConstraints();
        cfsPrice.fill = GridBagConstraints.BOTH;
        cfsPrice.anchor = GridBagConstraints.WEST;
        cfsPrice.gridx = 1;
        cfsPrice.gridy = 3;
        pSell.add( fsPrice, cfsPrice );

        GridBagConstraints clsMessage = new GridBagConstraints();
        clsMessage.fill = GridBagConstraints.BOTH;
        clsMessage.anchor = GridBagConstraints.WEST;
        clsMessage.gridx = 0;
        clsMessage.gridy = 4;
        clsMessage.weighty = 1.0;
        clsMessage.gridheight = GridBagConstraints.RELATIVE;
        clsMessage.ipady = 125;
        pSell.add( lsMessage, clsMessage );

        GridBagConstraints cSellWarn = new GridBagConstraints();
        cSellWarn.fill = GridBagConstraints.BOTH;
        cSellWarn.anchor = GridBagConstraints.WEST;
        cSellWarn.gridx = 0;
        cSellWarn.gridy = 5;
        cSellWarn.gridwidth = 3;
        cSellWarn.gridheight = GridBagConstraints.REMAINDER;
        cSellWarn.weighty = 5.0;
        pSell.add( sellWarn, cSellWarn );


        pSell.setMinimumSize( paneSize );
        pSell.validate();
        pSell.setVisible( false );
        this.add( pSell );
        // </editor-fold>

        //JPanel 'pUpdate' and all its components
        // <editor-fold>
        pUpdate = new JPanel( new GridBagLayout() );

        ulTitle = new JLabel( "Updating Investments" );
        ulSymbol = new JLabel( "Symbol" );
        ulName = new JLabel( "Name" );
        ulPrice = new JLabel( "Price" );
        ufSymbol = new JTextField( 5 );
        ufSymbol.setEditable( false );
        ufName = new JTextField( 30 );
        ufName.setEditable( false );
        ufPrice = new JTextField( 10 );
        ulMessage = new JLabel( "Messages" );
        utWarn = new JTextArea( 29, 144 );
        utWarn.setEditable( false );
        //utWarn.append( "Press 'Next' to show the first investment!\n" );
        uWarn = new JScrollPane( utWarn );
        uWarn.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        uWarn.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        ubPrev = new JButton( "Prev" );
        ubPrev.setEnabled( false );
        ubNext = new JButton( "Next" );
        ubPrev.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                int caught;
                indexOfInvestList--;

                caught = changeUpdate( ufSymbol, ufName, ufPrice );


                if( caught < 0 ) {
                    ubPrev.setEnabled( false );
                    ubNext.setEnabled( true );
                    indexOfInvestList++;
                } else if ( caught == 0 ) {
                    ubNext.setEnabled( false );
                    indexOfInvestList++;
                    utWarn.append( "There are no investments saved.\n" );
                }


                if ( indexOfInvestList == 0 ) {
                    ubPrev.setEnabled( false );
                    if ( ( investmentList.size() - 1 ) != 0 ) {
                        ubNext.setEnabled( true );
                    } else {
                        ubNext.setEnabled( false );
                    }
                } else if( indexOfInvestList == ( investmentList.size() - 1 ) ) {
                    ubNext.setEnabled( false );
                    if ( ( investmentList.size() - 1 ) != 0 ) {
                        ubPrev.setEnabled( true );
                    }
                }
            }
        });
        ubNext.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                int caught;
                indexOfInvestList++;

                caught = changeUpdate( ufSymbol, ufName, ufPrice );


                if( caught < 0 ) {
                    ubNext.setEnabled( false );
                    ubPrev.setEnabled( true );
                    indexOfInvestList--;
                } else if ( caught == 0 ) {
                    ubNext.setEnabled( false );
                    indexOfInvestList--;
                    utWarn.append( "There are no investments saved.\n" );
                }


                if ( indexOfInvestList == 0 ) {
                    ubPrev.setEnabled( false );
                    if ( ( investmentList.size() - 1 ) != 0 ) {
                        ubNext.setEnabled( true );
                    } else {
                        ubNext.setEnabled( false );
                    }
                } else if( indexOfInvestList == ( investmentList.size() - 1 ) ) {
                    ubNext.setEnabled( false );
                    if ( ( investmentList.size() - 1 ) != 0 ) {
                        ubPrev.setEnabled( true );
                    }
                } else {
                    ubPrev.setEnabled( true );
                    ubNext.setEnabled( true );
                }
            }
        });
        ubSave = new JButton( "Save" );
        ubSave.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                String newPrice = ufPrice.getText();
                Investment toSave = investmentList.get( indexOfInvestList );
                try {
                    toSave.setPrice( newPrice );
                    toSave.calculateGain();
                    utWarn.append( toSave + "\n" );
                } catch( BadPriceException f ) {
                    utWarn.append( f.getMessage() );
                }
            }
        });

        GridBagConstraints uCons = new GridBagConstraints();
        uCons.gridx = 0;
        uCons.gridy = 0;
        uCons.anchor = GridBagConstraints.WEST;
        pUpdate.add( ulTitle, uCons );

        uCons.gridx = 0;
        uCons.gridy = 1;
        uCons.weightx = 1.0;
        uCons.anchor = GridBagConstraints.EAST;
        uCons.ipady = 50;
        pUpdate.add( ulSymbol, uCons );

        uCons.gridx = 0;
        uCons.gridy = 2;
        uCons.weightx = 0.0;
        pUpdate.add( ulName, uCons );

        uCons.gridx = 0;
        uCons.gridy = 3;
        pUpdate.add( ulPrice, uCons );

        uCons.gridx = 0;
        uCons.gridy = 4;
        uCons.anchor = GridBagConstraints.WEST;
        uCons.ipady = 175;
        pUpdate.add( ulMessage, uCons );

        uCons.gridx = 1;
        uCons.gridy = 1;
        uCons.weightx = 1.0;
        uCons.ipady = 0;
        pUpdate.add( ufSymbol, uCons );

        uCons.gridx = 1;
        uCons.gridy = 2;
        uCons.weightx = 0.0;
        pUpdate.add( ufName, uCons );

        uCons.gridx = 1;
        uCons.gridy = 3;
        pUpdate.add( ufPrice, uCons );

        uCons.gridx = 2;
        uCons.gridy = 1;
        uCons.weightx = 1.0;
        uCons.anchor = GridBagConstraints.CENTER;
        pUpdate.add( ubPrev, uCons );

        uCons.gridx = 2;
        uCons.gridy = 2;
        uCons.weightx = 0.0;
        pUpdate.add( ubNext, uCons );

        uCons.gridx = 2;
        uCons.gridy = 3;
        pUpdate.add( ubSave, uCons );

        uCons.gridx = 0;
        uCons.gridy = 5;
        uCons.gridwidth = 3;
        uCons.fill = GridBagConstraints.BOTH;
        pUpdate.add( uWarn, uCons );


        pUpdate.setMinimumSize( paneSize );
        pUpdate.validate();
        pUpdate.setVisible( false );
        this.add( pUpdate );
        // </editor-fold>

        //JPanel 'pGain' and all its components
        // <editor-fold>
        pGain = new JPanel( new GridBagLayout() );

            glTitle = new JLabel( "Getting Total Gain" );
            glGain = new JLabel( "Total Gain" );
            gfGain = new JTextField( 10 );
            gfGain.setEditable( false );
            glMessage = new JLabel( "Individual Gains" );
            gtWarn = new JTextArea( 39, 144 );
            gtWarn.setEditable( false );
            gWarn = new JScrollPane( gtWarn );
            gWarn.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
            gWarn.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
            GridBagConstraints gCons = new GridBagConstraints();

            gCons.gridx = 0;
            gCons.gridy = 0;
            pGain.add( glTitle, gCons );

            gCons.gridx = 0;
            gCons.gridy = 1;
            gCons.ipady = 200;
            pGain.add( glGain, gCons );

            gCons.gridx = 1;
            gCons.gridy = 1;
            gCons.ipady = 0;
            pGain.add( gfGain, gCons );

            gCons.gridx = 0;
            gCons.gridy = 2;
            pGain.add( glMessage, gCons );

            gCons.gridx = 0;
            gCons.gridy = 3;
            gCons.gridwidth = 5;
            gCons.fill = GridBagConstraints.HORIZONTAL;
            pGain.add( gWarn, gCons );


            pGain.setMinimumSize( paneSize );
            pGain.validate();
            pGain.setVisible( false );
            this.add( pGain );
        //</editor-fold>

        //JPanel 'pSearch' and all its compoments
        // <editor-fold>
        pSearch = new JPanel( new GridBagLayout() );

        selTitle = new JLabel( "Searching Investments" );
        selSymbol = new JLabel( "Symbol" );
        selKeys = new JLabel( "Name Keywords" );
        sellPrice = new JLabel( "Low Price" );
        selhPrice = new JLabel( "High Price" );
        sefSymbol = new JTextField( 5 );
        sefKeys = new JTextField( 30 );
        seflPrice = new JTextField( 10 );
        sefhPrice = new JTextField( 10 );
        setWarn = new JTextArea( 36, 144 );
        selMessage = new JLabel( "Search Results" );
        setWarn.setEditable( false );
        seWarn = new JScrollPane( setWarn );
        seWarn.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        seWarn.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        sebReset = new JButton( "Reset" );
        sebReset.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                sefSymbol.setText( null );
                sefKeys.setText( null );
                seflPrice.setText( null );
                sefhPrice.setText( null );

            }
        });
        sebSearch = new JButton( "Search" );
        sebSearch.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                String searchSymbol = sefSymbol.getText();
                String searchKeys = sefKeys.getText();
                String searchlPrice = seflPrice.getText();
                String searchhPrice = sefhPrice.getText();

                toSearch( searchSymbol, searchKeys, searchlPrice,
                        searchhPrice, setWarn );
            }
        });
        GridBagConstraints seCons = new GridBagConstraints();

        seCons.gridx = 0;
        seCons.gridy = 0;
        seCons.anchor = GridBagConstraints.WEST;
        pSearch.add( selTitle, seCons );

        seCons.gridx = 0;
        seCons.gridy = 1;
        //seCons.weightx = 1.0;
        seCons.anchor = GridBagConstraints.EAST;
        seCons.ipady = 50;
        pSearch.add( selSymbol, seCons );

        seCons.gridx = 0;
        seCons.gridy = 2;
        //seCons.weightx = 0.0;
        pSearch.add( selKeys, seCons );

        seCons.gridx = 0;
        seCons.gridy = 3;
        pSearch.add( sellPrice, seCons );

        seCons.gridx = 0;
        seCons.gridy = 4;
        pSearch.add( selhPrice, seCons );

        seCons.gridx = 1;
        seCons.gridy = 1;
        //seCons.weightx = 1.0;
        seCons.anchor = GridBagConstraints.WEST;
        seCons.ipady = 0;
        pSearch.add( sefSymbol, seCons );

        seCons.gridx = 1;
        seCons.gridy = 2;
        //seCons.weightx = 0.0;
        pSearch.add( sefKeys, seCons );

        seCons.gridx = 1;
        seCons.gridy = 3;
        pSearch.add( seflPrice, seCons );

        seCons.gridx = 1;
        seCons.gridy = 4;
        pSearch.add( sefhPrice, seCons );

        seCons.gridx = 2;
        seCons.gridy = 2;
        //seCons.weightx = 1.0;
        seCons.anchor = GridBagConstraints.CENTER;
        pSearch.add( sebReset, seCons );

        seCons.gridx = 2;
        seCons.gridy = 3;
        //seCons.weightx = 0.0;
        pSearch.add( sebSearch, seCons );

        seCons.gridx = 0;
        seCons.gridy = 5;
        seCons.anchor = GridBagConstraints.WEST;
        pSearch.add( selMessage, seCons );

        seCons.gridx = 0;
        seCons.gridy = 6;
        seCons.gridwidth = 3;
        seCons.fill = GridBagConstraints.HORIZONTAL;
        seCons.anchor = GridBagConstraints.CENTER;
        pSearch.add( seWarn, seCons );

        pSearch.setMinimumSize( paneSize );
        pSearch.validate();
        pSearch.setVisible( false );
        this.add( pSearch );
        // </editor-fold>

        //JMenuBar 'topBar' and children components
        // <editor-fold>
        topBar = new JMenuBar();
        mCommands = new JMenu( "Commands" );

        miBuy = new JMenuItem( "buy" );
        miBuy.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( currentPane == 0 ) {
                    pLanding.setVisible( false );
                } else if ( currentPane == 2 ) {
                    pSell.setVisible( false );
                } else if ( currentPane == 3 ) {
                    pUpdate.setVisible( false );
                } else if ( currentPane == 4 ) {
                    pGain.setVisible( false );
                } else if ( currentPane == 5 ) {
                    pSearch.setVisible( false );
                }

                pBuy.setVisible( true );
                currentPane = 1;
            }
        });
        mCommands.add( miBuy );

        miSell = new JMenuItem( "sell" );
        miSell.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( currentPane == 0 ) {
                    pLanding.setVisible( false );
                } else if ( currentPane == 1 ) {
                    pBuy.setVisible( false );
                } else if ( currentPane == 3 ) {
                    pUpdate.setVisible( false );
                } else if ( currentPane == 4 ) {
                    pGain.setVisible( false );
                } else if ( currentPane == 5 ) {
                    pSearch.setVisible( false );
                }

                pSell.setVisible( true );
                currentPane = 2;
            }
        });
        mCommands.add( miSell );

        miUpdate = new JMenuItem( "update" );
        miUpdate.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( currentPane == 0 ) {
                    pLanding.setVisible( false );
                } else if ( currentPane == 1 ) {
                    pBuy.setVisible( false );
                } else if ( currentPane == 2 ) {
                    pSell.setVisible( false );
                } else if ( currentPane == 4 ) {
                    pGain.setVisible( false );
                } else if ( currentPane == 5 ) {
                    pSearch.setVisible( false );
                }

                pUpdate.setVisible( true );
                changeUpdate( ufSymbol, ufName, ufPrice );
                pUpdate.validate();
                ubNext.setEnabled( true );
                currentPane = 3;
            }
        });
        mCommands.add( miUpdate );

        miGain = new JMenuItem( "getGain" );
        miGain.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( currentPane == 0 ) {
                    pLanding.setVisible( false );
                } else if ( currentPane == 1 ) {
                    pBuy.setVisible( false );
                } else if ( currentPane == 2 ) {
                    pSell.setVisible( false );
                } else if ( currentPane == 3 ) {
                    pUpdate.setVisible( false );
                } else if ( currentPane == 5 ) {
                    pSearch.setVisible( false );
                }

                pGain.setVisible( true );
                pGain.validate();
                getGain( gfGain, gtWarn );
                currentPane = 4;
            }
        });
        mCommands.add( miGain );

        miSearch = new JMenuItem( "search" );
        miSearch.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( currentPane == 0 ) {
                    pLanding.setVisible( false );
                } else if ( currentPane == 1 ) {
                    pBuy.setVisible( false );
                } else if ( currentPane == 2 ) {
                    pSell.setVisible( false );
                } else if ( currentPane == 3 ) {
                    pUpdate.setVisible( false );
                } else if ( currentPane == 4 ) {
                    pGain.setVisible( false );
                }

                pSearch.setVisible( true );
                currentPane = 5;
            }
        });
        mCommands.add( miSearch );

        miQuit = new JMenuItem( "quit" );
        miQuit.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                saveInvestments(); //This call is not implemented yet!
                System.exit(0);
            }
        });
        mCommands.add( miQuit );

        topBar.add( mCommands );
        this.setJMenuBar( topBar );
        // </editor-fold>
    }

    /**
     * Creates an instance of the GUI and runs it, as well as initializing the
     * some static vars.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        investmentList = new ArrayList<>( 20 );
        index = new HashMap<String, ArrayList<Integer>>();
        parseFile( "SavedInvestments.txt" );
        Portfolio gui = new Portfolio();
        gui.setVisible(true);
    }

    private static void saveInvestments() {
        //Dec & Init
        BufferedWriter wOut;
        String lineOut = "";
        //Stock tempStock = new Stock();
        //MutualFund tempFund = new MutualFund();



        //Body
        try {
            wOut = new BufferedWriter ( new FileWriter ( "SavedInvestments.txt" ));

            for ( Investment ele : investmentList ) {

                lineOut = "type = \"" + ele.getClass() + "\"\n";
                wOut.write( lineOut );

                lineOut = "symbol = \"" + ele.getSymbol() + "\"\n";
                wOut.write( lineOut );

                lineOut = "name = \"" + ele.getName() + "\"\n";
                wOut.write( lineOut );

                lineOut = "quantity = \"" + ele.getQuantity() + "\"\n";
                wOut.write( lineOut );

                lineOut = "price = \"" + ele.getPrice() + "\"\n";
                wOut.write( lineOut );

                lineOut = "bookValue = \"" + ele.getBookValue() + "\"\n";
                wOut.write( lineOut );

                lineOut = "\n";
                wOut.write( lineOut );
            }
            wOut.close();
        } catch ( IOException e ) {
            System.out.print( "\nCould not save your investments. Sorry.\n");
        }
    }

    private static void parseFile ( String filename ) {
        //Dec & Init
        ArrayList<String> lines = new ArrayList<> ( 50 );
        int count = 0;
        MutualFund testFund = null;
        Scanner inStream = null;
        Stock testStock = null;
        String tempLine;
        String splits[];
        String[] components = { "","","","","","" };

        //Body
        try {
            inStream = new Scanner ( new FileInputStream ( filename ) );
            while ( inStream.hasNextLine() ) {
                lines.add( inStream.nextLine() );
            }
            inStream.close();

            for ( String element : lines ) {
                splits = element.split( "\"" );
                if ( splits.length > 1 ) {
                    components[count++] = splits[1];
                }

                if ( count > 5 ) {
                    if ( components[0].equals( "class a3.Stock" ) ) {
                        try {
                            testStock = addStock( components );
                        } catch ( BadSymbolException | BadNameException |
                                BadQuantityException | BadPriceException e ) {
                            System.out.print( "Entries in the save file are"
                                    + "corrupted, cannot parse.\n");
                        }
                        if ( testStock != null ) {
                            investmentList.add( testStock );
                            for ( String ele : testStock.getNameTokens()) {
                                if ( index.containsKey( ele ) ) {
                                    ArrayList<Integer> temp = index.get( ele );
                                    temp.add( investmentList.indexOf( testStock ) );
                                } else {
                                    ArrayList<Integer> temp = new ArrayList();
                                    temp.add( investmentList.indexOf( testStock ) );
                                    index.put( ele, temp );
                                }
                            }
                        }
                    } else if ( components[0].equals ( "class a3.MutualFund" ) ) {
                        try {
                            testFund = addFund( components );
                        } catch ( BadSymbolException | BadNameException |
                                BadQuantityException | BadPriceException e ) {
                            System.out.print( "Entries in the save file are"
                                    + "corrupted, cannot parse.\n");
                        }
                        if ( testFund != null ) {
                            investmentList.add( testFund );
                            for ( String ele : testFund.getNameTokens()) {
                                if ( index.containsKey( ele ) ) {
                                    ArrayList<Integer> temp = index.get( ele );
                                    temp.add( investmentList.indexOf( testFund ) );
                                } else {
                                    ArrayList<Integer> temp = new ArrayList();
                                    temp.add( investmentList.indexOf( testFund ) );
                                    index.put( ele, temp );
                                }
                            }
                        }
                    } else {
                        System.out.print ( "\nCannot parse the investment.\n" );
                    }
                    count = 0;
                }

            }
        } catch ( FileNotFoundException e ) {
            System.out.print ( "\nFile could not be opened, program will continue as if there were no file\n" );
        }
    }

    private static int changeUpdate( JTextField sym, JTextField name,
            JTextField price ) {
        int toReturn = 0;

        if ( ! investmentList.isEmpty() ) {
           //If the list is not empty
           if ( ( indexOfInvestList < 0 ) || indexOfInvestList >= investmentList.size() ) {
               //If the list is out of bounds
               toReturn = -1;
           } else {
               //else in bounds, toReturn=1 and update the fields
               Investment inv = investmentList.get( indexOfInvestList );
               toReturn = 1;
               sym.setText( inv.getSymbol() );
               name.setText( inv.getName() );
               price.setText(  (( Double ) inv.getPrice() ).toString() );
            }
        }
        return toReturn;
    }

    private static void getGain( JTextField gainSpot, JTextArea indiGains ) {
        //Dec and Init
        double totGain = 0;
        double gain = 0;

        indiGains.setText( "Symbol\t\tIndividual Gain\n\n" );
        for( Investment ele : investmentList ) {
            ele.calculateGain();
            gain = ele.getGain();
            indiGains.append( ele.getSymbol() + "\t\t" + gain + "\n" );
            totGain += gain;
        }
        gainSpot.setText( ( (Double) totGain ).toString() );
    }

    private static void toSearch( String symbol, String keys, String lPrice,
            String hPrice, JTextArea output ) {
        //Dec and Init
        ArrayList<Integer> temp = new ArrayList<>( 20 );
        ArrayList<Integer> intersects = new ArrayList<>( 20 );

        ArrayList<Investment> post1= new ArrayList<>( 20 );
        ArrayList<Investment> post2= new ArrayList<>( 20 );
        ArrayList<Investment> matches = new ArrayList<>( 20 );
        boolean first = true;
        boolean runLoop = true;
        double price1 = 0;
        double price2 = 0;
        int keysTotal = 0;
        int matchKeys = 0;
        int priceFormat = 0;
        int indexOfDash = 0;
        String symbolIn = "";
        String keywords = "";
        String prices = "";
        String[] tokens;

        //Body
        //Symbol Search
        symbolIn = symbol;
        if( symbolIn.isEmpty() ) {
            for( Investment element : investmentList) {
                post1.add( element );
            }
        } else {
            for( Investment element : investmentList ) {
                if( element.getSymbol().equals( symbolIn ) ) {
                    post1.add( element );
                }
            }
        }

        //Keyword Search
        keywords = keys;
        tokens = keywords.split( "\\s" );
        keysTotal = tokens.length;

        //If no keywords
        if ( keywords.isEmpty() ) {
            //add all to the next stage
            for ( Investment element : post1 ) {
                post2.add( element );
            }
        //Otherwise
        } else {
            //For each keyword
            for ( String ele: tokens ){
                //Get its corresponding AL<Int>
                temp = index.get( ele );
                //If total is empty for the first time
                if ( intersects.isEmpty() && first) {
                    if( ( temp == null ) || ( ! temp.isEmpty() ) ) {
                        //Add the integers to the AL<Int>
                        for ( Integer i : temp ) {
                            intersects.add( i );
                        }
                        first = false;
                    }
                } else {
                    //Otherwise, subtract to find intersection.
                    intersects.retainAll( temp );
                }
            }

            for ( Integer i : intersects ) {
                if ( post1.contains( investmentList.get( i ) ) ) {
                    post2.add( investmentList.get( i ) );
                }
            }
        }

        //Price Range Search
        double lprice = 0;
        double hprice = 0;
        int priceSyntax = 0;
        if ( lPrice.isEmpty() && hPrice.isEmpty() ) {
            //If both empty, move all Investments from last step onward
            for( Investment element : post2 ) {
                    matches.add( element );
                }
        } else if ( lPrice.equals( hPrice ) ) {
            //If they are both equal FULL
            try {
                lprice = Double.parseDouble( lPrice );
                hprice = Double.parseDouble( hPrice );
                priceSyntax = 1;
            } catch( NumberFormatException e ) {
                output.append( "The prices are incorrect.\n" );
            }
        } else if( ( ! lPrice.isEmpty() ) && ( ! hPrice.isEmpty() ) ) {
            //If they are both FULL but different
            try {
                lprice = Double.parseDouble( lPrice );
                hprice = Double.parseDouble( hPrice );
                if ( lprice >= hprice ) {
                    output.append( "The low price cannot be higher than the high price.\n");
                } else {
                    priceSyntax = 2;
                }
            } catch( NumberFormatException e ) {
                output.append( "The prices are incorrect.\n" );
            }
        } else if ( lPrice.isEmpty() && ( ! hPrice.isEmpty() ) ) {
            //If only lPrice is empty and hPrice is FULL
            try {
                hprice = Double.parseDouble( hPrice );
                priceSyntax = 3;
            } catch( NumberFormatException e ) {
                output.append( "The high price is incorrect.\n" );
            }
        } else if ( hPrice.isEmpty() && ( ! lPrice.isEmpty() ) ) {
            //If only hPrice is empty and hPrice is FULL
            try {
                lprice = Double.parseDouble( lPrice );
                priceSyntax = 4;
            } catch( NumberFormatException e ) {
                output.append( "The low price is incorrect.\n" );
            }
        }

        /*
            1 means both EQUAL 30
            2 means 10-30
            3 means -30
            4 means 30-
        */
        if( priceSyntax == 1 ) {
            for( Investment element : post2 ) {
                if( element.getPrice() == lprice ) {
                    matches.add( element );
                }
            }
        } else if( priceSyntax == 2 ) {
            for( Investment element : post2 ) {
                if( ( element.getPrice() >= lprice ) && ( element.getPrice() <= hprice ) ) {
                    matches.add( element );
                }
            }
        } else if( priceSyntax == 3 ) {
            for( Investment element : post2 ) {
                if( element.getPrice() <= hprice ) {
                    matches.add( element );
                }
            }
        } else if( priceSyntax == 4 ) {
            for( Investment element : post2 ) {
                if( element.getPrice() >= lprice ) {
                    matches.add( element );
                }
            }
        }



        //Print out
        if( matches.isEmpty()) {
            output.append( "No investment matched the search parameters.\n\n" );
        } else {
            output.setText( "vv RESULTS vv\n\n");
            for( Investment element : matches ) {
                output.append( element.toString() + "\n" );
            }
        }
    }
}


