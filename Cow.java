
public class Cow extends Animal implements Calculations{

	private double T_a;
	private double T_k;
	private double T_d;
	private double T_c;
	private double B_b;
	private double B_k;
	private double B_c;

	//initializes variables to values specific to the Cow Class
	public Cow()
	{
		name = "Cow";
		growthTime = 3;
		lifeTime = 10;

		product = "Milk"; 
		purchasePrice = 75;

		baseProdRate = 60;
		baseProdPrice = 4;
		prodRateVariable = 10;

		T_a = 1.5;
		T_k = 30;
		T_d = -2;
		T_c = 3;
		B_b = 2;
		B_k = 0.5;
		B_c = 10;
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
		timeFactor = T_a*Math.sin(Math.toRadians(T_k*(time%12)-T_d))+T_c;

		//models the price of the animal as it ages
		baseSellPrice = (int)Math.floor(Math.pow(B_b, age*B_k)+B_c);

		sellPrice = baseSellPrice*timeFactor;

		return sellPrice;
	}

	//this method determines the price of the animal's product, given the time of year it is
	@Override
	public double getProductPrice(int time) {
		double price;
		double timeFactor;

		timeFactor = T_a*Math.sin(Math.toRadians(T_k*(time%12)-T_d))+T_c;
		price = timeFactor*baseProdPrice;

		return price;
	}

}
