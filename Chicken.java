
public class Chicken extends Animal implements Calculations{
	
	private double T_k;
	private double T_d;
	private double T_c;
	private double B_a;
	private double B_d;
	private double B_c;
	
	//initializes variables to values specific to the Chicken Class
	public Chicken()
	{
		name = "Chicken";
		growthTime = 1;
		lifeTime = 5;
		
		product = "Eggs";
		purchasePrice = 10;

		baseProdRate = 12;
		baseProdPrice = 4;
		prodRateVariable = 2;
		
		T_k = 90;
		T_d = 7;
		T_c = 2;
		B_a = 10;
		B_d = -1;
		B_c = 1;
	}

	//updates how many products the animal produces in a month, based on age & random factor
	@Override
	public void updateProductionRate() {		
		if (age >= growthTime && age < lifeTime)
		{
			int numProduced = (int) Math.floor(baseProdRate + Math.random()*prodRateVariable-prodRateVariable/2);
			setProductionRate(numProduced);
		}
		else
		{
			setProductionRate(0);			
		}
	}

	//this method determines the selling price of an animal, based on its age and the time of year
	@Override
	public double getSellPrice(int time) {

		double sellPrice;
		double timeFactor;
		double baseSellPrice;
		
		//determines a multiplier for the sell price based on the time of year
		timeFactor = Math.sin(Math.toRadians(T_k*(time%12)-T_d))+T_c;

		//models the price of a chicken as it ages, using a log scale
		baseSellPrice = (int)Math.floor(B_a*Math.log(age-B_d)+B_c);
		
		sellPrice = baseSellPrice*timeFactor;

		return sellPrice;
	}

	//this method determines the price of the animal's product, given the time of year it is
	@Override
	public double getProductPrice(int time) {
		
		double price;
		double timeFactor;
		
		timeFactor = Math.sin(Math.toRadians(T_k*(time%12)-T_d))+T_c;
		price = timeFactor*baseProdPrice;
		
		return price;
	}

	
}
