/*Adam McKoy
  APCS1 pd10
  HW45 -- Come Together
  2015-12-10*/

public class SuperArray{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor - initializes 10-item array
    public SuperArray() 
    { 
	_data = new Comparable[10];
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
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	_size += 1;
	_data[_lastPos+1] = newVal;  //slot right after last meaningfull slot is filled
	_lastPos+=1;
    }

    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
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
	Comparable[] new_data = new Comparable[_data.length-1];
	for (int count = 0 ; count < index ; count++){  //copy everything before target
	    new_data[count] = _data[count];
	}
	for (int count = index ; count < new_data.length ; count++){ //copy everything after target, skipping the target, effectively removing it
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
    
    public int linSearch(Comparable target){
	int index = 0;
	while(!(_data[index].compareTo(target) == 0) && index < _size){
	    index += 1;
	}
	if(index < _size){
	    return index;
	} else{
	    return -1;
	}
    }

    public boolean isSorted(){
	for(int i = 0; i < _size - 1; i++){
	    if(_data[i].compareTo(_data[i+1]) > 0){
		return false;
	    }
	}
	return true;
    }

    //main method for testing
    public static void main( String[] args ) 
    {
	   
    }//end main
		
}//end class
