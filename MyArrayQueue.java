
public class MyArrayQueue{

	//creates a queue (FIFO) using MyArrayList: 
	//items are added to the start of array (index 0), and removed from the end	
	MyArrayList queueArray = new MyArrayList();

	//adds a node to the start of the queue
	public void enqueue(MyNode node) {
		queueArray.insertNode(node, 0);
	}

	//removes a node from the end of the queue
	public MyNode dequeue() {
		int index = queueArray.size();
		return queueArray.removeNode(index-1);
	}

	//returns the node at the end of the queue (what the next node to be removed is)
	public MyNode peek() {
		return queueArray.getLastNode();
	}

	//returns all the nodes in the queue
	public MyArrayList peekFull() {
		return queueArray;
	}

	//returns the size of the queue
	public int size() {
		return queueArray.size();
	}

	//returns true is the array is empty, returns false if the array has at least on value
	public boolean isEmpty() {

		if (queueArray.size() == 0)
		{
			return true;
		}
		return false;
	}

	public boolean isFull()
	{
		if (queueArray.size() == 5)
		{
			return true;
		}
		return false;
	}

}