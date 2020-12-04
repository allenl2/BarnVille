
public class Money {
	
	//represents the money the user has
	private double money;
	
	public Money()
	{
		money = 1000.00; //default starting balance is 100 dollars
	}
	
	//returns the amount of money the user has
	public double getBalance()
	{
		return money;
	}
	
	//returns true if the user can buy an item at the given price
	public boolean canBuy(double price)
	{
		if(money > price)
		{
			return true;
		}
		return false;
	}
	
	//updates the balance after the user buys an item
	public double buyItem(double price)
	{
		money -= price;
		return money;
	}
	
	//updates the balance after the user sells an item
	public double sellItem(double price)
	{
		money+=price;
		return money;
	}

}
