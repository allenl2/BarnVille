
public class Bee extends Animal implements Calculations {

	private double T_a;
	private double T_k;
	private double T_c;
	private double B_a;
	private double B_d;
	private double B_c;
	
	//initializes variables to values specific to the Bee Class
	public Bee()
	{
		name = "Bee";
		growthTime = 1;
		lifeTime = 12;

		product = "Honey"; 
		purchasePrice = 120;
		
		baseProdRate = 25;
		baseProdPrice = 18;
		prodRateVariable = 10;

		T_a = 0.8;
		T_k = 180;
		T_c = 1.8;
		B_a = 0.142857;
		B_d = 1;
		B_c = 7;
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
			baseSellPrice = (int)Math.floor(B_a*Math.pow((age-B_d), 3) + B_c);

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
