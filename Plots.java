
public class Plots {
	
	private int plotNum;
	private int animalType;
	private double[] xCoords; //specify the x and y coordinate range of the plot
	private double[] yCoords;
	private boolean isEmpty;
	private Animal animal;
	
	public Plots(int num, double[] x, double[] y)
	{
		plotNum = num;
		animalType = 5;
		xCoords = x;
		yCoords = y;
		isEmpty = true;
	}
	
	public int getPlotNum() {
		return plotNum;
	}

	public void setPlotNum(int plotNum) {
		this.plotNum = plotNum;
	}

	public int getAnimalType() {
		return animalType;
	}

	public void setAnimalType(int animalType) {
		this.animalType = animalType;
	}

	public double[] getxCoords() {
		return xCoords;
	}

	public void setxCoords(double[] xCoords) {
		this.xCoords = xCoords;
	}

	public double[] getyCoords() {
		return yCoords;
	}

	public void setyCoords(double[] yCoords) {
		this.yCoords = yCoords;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
		isEmpty = false;
	}
	
	public void clearAnimal()
	{
		animal = null;
		isEmpty = true;
		animalType = 5;
	}
	

}
