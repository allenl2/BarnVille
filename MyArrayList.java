
public class MyArrayList {
	
	private int size = 5; //size of the entire array (including empty indexes), starts with 5
	private int numFill; //number of filled indexes
	
	private MyNode[] array = new MyNode[size];

	//this method adds a node to the end of the array
	public void addNode(MyNode n) {
		//if the array is full, a new array is created with double the size
		  if (numFill >= size-1)
		  {
		   increaseArray();
		  }
		
		array[numFill] = n;
		numFill++;
	}

	//this method inserts a node at the specified index
	public void insertNode(MyNode n, int i) {
		
		if (numFill >= size-1)
		  {
		   increaseArray();
		  }
		
		//shifts all the values after the specified index to clear a space for the new node
		for (int j=numFill; j>i; j--)
		{
			array[j] =  array[j-1];
		}
		
		array[i] = n;
		numFill++;
	}

	//this method removes a given node from the array and shifts all nodes doen to fill the gap
	public void removeNode(MyNode n) {
		for(int i=0; i<numFill; i++)
		{
			if (array[i].equals(n))
			{				
				for(int j=i; j<size-1; j++)
				{
					array[j] = array[j+1];
				}
				numFill--;
			}
		}
	}

	//this method removes a node at a specified index, and shift all following nodes
	public MyNode removeNode(int i) {
		
		MyNode temp = array[i];
				
		for(int j=i; j<size-1; j++)
		{
			array[j] = array[j+1];
		}
		
		numFill--;
		
		return temp;
	}

	//the following methods return the node as specified when calling the method,  
	//or the size of the array
	public MyNode getNode(int i) {
		return array[i];
	}
	
	public MyNode getFirstNode() {
		return array[0];
	}

	public MyNode getLastNode() {
		return array[numFill-1];
	}

	public int size() {
		return numFill;
	}
	
	 //this method creates a new array that is double the original size and copies the values over
	 //it is called when the array is full
	 public void increaseArray()
	 {
	  size = size*2;
	  MyNode newArray[] = new MyNode[size];
	  
	  for (int i=0; i < size/2-1; i++)
	  {
	   newArray[i] = array[i];
	  }
	  
	  array = newArray;
	 }
	
}