
public class Storage {

	private int numEgg;
	private int numMilk;
	private int numWool;
	private int numCheese;
	private int numHoney;

	private int numOmelette;
	private int numButter;
	private int numSweater;
	private int numSpread;
	private int numTea;

	public Storage()
	{
		numEgg = 0;
		numMilk = 0;
		numWool = 0;
		numCheese = 0;
		numHoney = 0;

		numOmelette = 0;
		numButter = 0;
		numSweater = 0;
		numSpread = 0;
		numTea = 0;
	}

	//methods to get, add, or subtract the specified product type
	public int[] getNumItems(int type) {
		
		if(type == 0)
		{
			int[] list = {numEgg, numOmelette};
			return list;
		}
		else if (type == 1)
		{
			int[] list = {numMilk, numButter};
			return list;
		}
		else if (type == 2)
		{
			int[] list = {numWool, numSweater};
			return list;
		}
		else if (type == 3)
		{
			int[] list = {numCheese, numSpread};
			return list;
		}
		else
		{
			int[] list = {numHoney, numTea};
			return list;
		}
	}

	//method to add items to storage, of a specified type
	public void addItems(int type, int numFirst, int numLast) {
		if(type == 0)
		{
			numEgg += numFirst;
			numOmelette += numLast;
		}
		else if (type == 1)
		{
			numMilk += numFirst;
			numButter+= numLast;
		}
		else if (type == 2)
		{
			numWool += numFirst;
			numSweater+= numLast;
		}
		else if (type == 3)
		{
			numCheese += numFirst;
			numSpread += numLast;
		}
		else
		{
			numHoney += numFirst;
			numTea+= numLast;
		}
	}

	public void removeItems(int type, int numFirst, int numLast) {
		if(type == 0)
		{
			numEgg -= numFirst;
			numOmelette -= numLast;
		}
		else if (type == 1)
		{
			numMilk -= numFirst;
			numButter-= numLast;
		}
		else if (type == 2)
		{
			numWool -= numFirst;
			numSweater-= numLast;
		}
		else if (type == 3)
		{
			numCheese -= numFirst;
			numSpread -= numLast;
		}
		else
		{
			numHoney -= numFirst;
			numTea-= numLast;
		}		
	}
}
