/* Adam McKoy
   APCS1 pd10
   HW45 -- Come Together
   2015-12-10 */

public class Hexadecimal implements Comparable{

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_hexNum = s;
	_decNum = hexToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;
    }


    public static String decToHex( int n ) {
	if(n == 0){
	    return "0";
	} else{
	    int quotient = n;
	    String retStr = "";
	    while(quotient > 0){
		retStr = HEXDIGITS.substring(quotient % 16, (quotient % 16) + 1) + retStr;
		quotient /= 16;
	    }
	    return retStr;
	}
    }


    public static String decToHexR( int n ) { 
	if(n < 16){
	    return HEXDIGITS.substring(n, n+1);
	} else{
	    return decToHexR(n / 16) + (n % 16);
	}
    }


   public static int hexToDec( String s ) {
	int decimal = 0;
	for(int i = 0; i < s.length(); i++){
	    decimal += HEXDIGITS.indexOf(s.substring(i, i+1)) * Math.pow(16, (-1 * i) + s.length() - 1);
	}
	return decimal;
    }


    public static int hexToDecR( String s ) { 
	if(s.length() == 1){
	    return HEXDIGITS.indexOf(s);
	} else{
	    return HEXDIGITS.indexOf(s.substring(0,1)) * (int)Math.pow(16, s.length() - 1) + hexToDecR(s.substring(1));
	} 
    }

    public int getValue(){
	return _decNum;
    }

    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
	if(other instanceof Hexadecimal){
	    return this == other || compareTo(other) == 0;
	} else{
	    throw new ClassCastException("\nequals() input not Hexadecimal");
	}
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if(other instanceof Hexadecimal){
	    if(this._decNum < ((Hexadecimal)other).getValue()){
		return -1;
	    } else if(this._decNum > ((Hexadecimal)other).getValue()){
		return 1;
	    } else{
		return 0;
	    }
	}
	if(other instanceof Binary){
	    if(this._decNum < ((Binary)other).getValue()){
		return -1;
	    } else if(this._decNum > ((Binary)other).getValue()){
		return 1;
	    } else{
		return 0;
	    }
	}
	if(other instanceof Rational){
	    ((Rational)other).compareTo(this);
	}
	else{
	    throw new ClassCastException("\ncompareTo() input not Comparable");
	}	
	return -2;
    }


    //main method for testing
    public static void main( String[] args ) {
	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal h1 = new Hexadecimal(21);
	Hexadecimal h2 = new Hexadecimal(21);
	Hexadecimal h3 = h1;
	Hexadecimal h4 = new Hexadecimal(42);

	System.out.println( h1 );
	System.out.println( h2 );
	System.out.println( h3 );       
	System.out.println( h4 );       

	System.out.println( "\n==..." );
	System.out.println( h1 == h2 ); //should be false
	System.out.println( h1 == h3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( h1.equals(h2) ); //should be true
	System.out.println( h1.equals(h3) ); //should be true
	System.out.println( h3.equals(h1) ); //should be true
	System.out.println( h4.equals(h2) ); //should be false
	System.out.println( h1.equals(h4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( h1.compareTo(h2) ); //should be 0
	System.out.println( h1.compareTo(h3) ); //should be 0
	System.out.println( h1.compareTo(h4) ); //should be neg
	System.out.println( h4.compareTo(h1) ); //should be pos
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
