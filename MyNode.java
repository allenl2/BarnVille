

public class MyNode{
	
	private int productType;
	private int amountProduct; 
	
	//constructor class that creates an instance of MyNode with specified variables
	public MyNode(int productType, int amountProduct)
	{
		this.productType = productType;
		this.amountProduct = amountProduct;
	}
	
	//the following methods are getter and setter methods for the variables in the MyNode class
	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	//toString method, that returns a string with the product and amount
	public String toString() {
		String result;
		if(productType == 0)
			result = "Omelettes";
		else if (productType == 1)
			result = "Butter";
		else if (productType == 2)
			result = "Sweater";
		else if (productType == 3)
			result = "Spread";
		else
			result = "Tea";
		return result + " - " + amountProduct;
	}	
}