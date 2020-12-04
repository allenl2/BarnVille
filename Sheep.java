
public class Sheep extends Animal implements Calculations {

	private double T_a;
	private double T_k;
	private double T_c;
	private double B_m;
	private double B_b;

	//initializes variables to values specific to the Sheep Class
	public Sheep()
	{
		name = "Sheep";
		growthTime = 2;
		lifeTime = 6;

		product = "Wool";
		purchasePrice = 58; 

		baseProdRate = 30;
		baseProdPrice = 6;
		prodRateVariable = 6;

		T_a = 0.5;
		T_k = 60;
		T_c = 2;
		B_m = 3;
		B_b = 20;

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
		timeFactor = T_a*Math.sin(Math.toRadians(T_k*(time%12)))+T_c;

		//models the price of the animal as it ages
		baseSellPrice = (int)Math.floor(age*B_m + B_b);

		sellPrice = baseSellPrice*timeFactor;

		return sellPrice;
	}

	//this method determines the price of the animal's product, given the time of year it is
	@Override
	public double getProductPrice(int time) {

		double price;
		double timeFactor;

		timeFactor = T_a*Math.sin(Math.toRadians(T_k*(time%12)))+T_c;
		price = timeFactor*baseProdPrice;

		return price;
	}
}
