
public abstract class Animal {

	protected String name;	

	protected int age;
	protected int growthTime;
	protected int lifeTime;

	protected String product;
	protected double purchasePrice;
	protected int productionRate;
	protected int amountProduct;

	protected int baseProdRate;
	protected int baseProdPrice;
	protected int prodRateVariable;

	public Animal()
	{
		age = 0;
		productionRate = 0;
		amountProduct = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void increaseAge() {
		this.age++;
	}

	public double getPurchasePrice()
	{
		return purchasePrice;
	}

	public int getProductionRate() {
		return productionRate;
	}

	public void setProductionRate(int productionRate) {
		this.productionRate = productionRate;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void increaseAmountProduct(int amountProduct) {
		this.amountProduct += amountProduct;
	}

	public double sellAllProduct(double productPrice) //returns total sale amount
	{
		double total = amountProduct*productPrice;
		amountProduct = 0;
		return total;
	}
	
	//the following below defines abstract methods that are to be defined in subclasses, since they involve calculations that are unique to the specific animal
	abstract public void updateProductionRate();
	
	abstract public double getSellPrice(int time);

	abstract public double getProductPrice(int time);

}
