
public interface Calculations {
	
	//updates the production rate of the animal (ie. how many products it makes in one month)
	public void updateProductionRate();
	
	//returns the selling price of the animal, based on its age and the time of year
	public double getSellPrice(int time);
	
	//returns the selling price of the animal's product, based on its age and time of year
	public double getProductPrice(int time);
	

}
