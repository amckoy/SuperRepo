/*
Team feckless-octo-turtle - Ruochong Wu, Adam McKoy
APCS1 pd10
HW42 -- Array of Titanium
2015-12-07
*/

public class SuperArray implements ListInt{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor - initializes 10-item array
    public SuperArray() 
    { 
	_data = new int[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	int[] temp = new int[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public int get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) 
    { 
 	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) {
	_size += 1;
	_data[_lastPos+1] = newVal;  //slot right after last meaningfull slot is filled
	_lastPos+=1;
    }

    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, int newVal ) {
	_size += 1;
	_lastPos += 1;
	for ( int x = _lastPos ; x > index ; x--){ //shifts everything between index and _lastpos
	    _data[x] = _data[x-1];
	}
	_data[index] = newVal;
    }    

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
	int[] new_data = new int[_data.length-1];
	for (int count = 0 ; count < index ; count++){  //copy everything before target
	    new_data[count] = _data[count];
	}
	for (int count = index ; count<new_data.length ; count++){ //copy everything after target, skipping the target, effectively removing it
	    new_data[count] = _data[count + 1];
	}
	_lastPos -=1;
	_size -=1;
	_data = new_data;
    }
   
    //return number of meaningful items in _data
    public int size() {
	return _size;
    }
   

    //main method for testing
    public static void main( String[] args ) 
    {
	SuperArray curtis = new SuperArray();
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);

	for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set(i,i*2);
	    curtis._size++; //necessary bc no add() method yet
	}

	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);

	System.out.println("testing get()...");
	for( int i = 0; i < curtis._size; i++ ) {
	    System.out.print( "item at index" + i + ":\t" );
	    System.out.println( curtis.get(i) );
	}

	System.out.println("Expanded SuperArray curtis:");
	curtis.expand();
	System.out.println(curtis);


	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.add(5);
	mayfield.add(4);
	mayfield.add(3);
	mayfield.add(2);
	mayfield.add(1);

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	System.out.println(mayfield._lastPos);

	mayfield.add(3,99);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(2,88);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(1,77);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);

	System.out.println(mayfield.size());

	ListInt test = new SuperArray();
	test.add(5);
	test.add(4);
	test.add(3);
	test.add(2);
	test.add(1);
	System.out.println(test);

	System.out.println("testing get()...");
	for( int i = 0; i < test.size(); i++ ) {
	    System.out.print( "item at index" + i + ":\t" );
	    System.out.println( test.get(i) );
	}

	test.remove(3);
	System.out.println("Printing test post-remove...");
	System.out.println(test);
	
	test.add(3,21);
	System.out.println("Printing test post-insert...");
	System.out.println(test);
	test.add(2,42);
	System.out.println("Printing test post-insert...");
	System.out.println(test);
	test.add(1,51);
	System.out.println("Printing test post-insert...");
	System.out.println(test);

	System.out.println(test.size());
 
	    
    }//end main
		
}//end class
