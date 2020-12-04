import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.DecimalFormat;

import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Main extends Application{

	//declares variables for the size of the window
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;

	//declares variables for instances of different classes
	private Timer timer;
	private Money balance;
	private Storage storage;
	private MyArrayQueue queue;

	//declares variables for the file paths of graphics
	private String menuBackgroundFile;
	private String gameBackgroundFile;
	private String endBackgroundFile;
	private String animalImage;
	private String plotImage;
	private String imageEnding;

	//declares arraylists for the storage of actual graphics (as imageview)
	private ArrayList<ImageView> background;
	private ArrayList<ImageView> animalImages;
	private ArrayList<Image> plotImages;

	//declares variables with details for the animals
	private int numAnimals;
	private Animal[] allAnimals;

	//declares variables used for the JavaFx GUI
	private Scene menuScene;
	private Scene gameScene;
	private Scene endScene;
	private Group menuBackground = new Group();
	private Group gameBackground = new Group();
	private Group endBackground = new Group();
	private VBox sidebar = new VBox();
	private HBox bottombar = new HBox();

	//declares variables for fonts, uses the Font and Font Weight class, sets the font style and size
	private Font buyLabelTitleFont;
	private Font buyLabelFont;
	private Font topLabelFont;
	private Font smallLabelFont;

	//declares variables for formatting of prices, uses the Decimal Format Class, with allows numbers to be formatted in the specified manner
	private DecimalFormat wholeFormat;
	private DecimalFormat decimalFormat;

	//declares variables that store the x and y coordinates of the animal plots. With each pair, the first and last values represent the start and ending coordinate of a plot
	private double[][] rowCoords;
	private double[][] colCoords;

	//declares variables to keep track of the plots
	private Plots[] plots;
	private int numPlots;
	private int currentPlot;

	//declares variables that keep track of the current animal price and product price being looked at
	private double currentAnimalSell;
	private double currentProdSell;
	
	//declares variables used to keep track of the multiplier for upgraded products
	private int upgradeMultiplier;

	//declares variables that keep track of the current product being looked at and amount of that product
	private int currentProduct;
	private int[] currentProductAmounts;

	//declares variables for instances of the subclasses (animal types) used for price calculations
	private Chicken sampleChicken;
	private Cow sampleCow;
	private Sheep sampleSheep;
	private Goat sampleGoat;
	private Bee sampleBee;

	//declares various labels and buttons used for display in the JavaFX GUI
	private Button b_BuyChick;
	private Button b_BuyCow;
	private Button b_BuySheep;
	private Button b_BuyGoat;
	private Button b_BuyBee;

	private Label l_BuyTitle;
	private Label l_BuyChick;
	private Label l_BuyCow;
	private Label l_BuySheep;	
	private Label l_BuyGoat;
	private Label l_BuyBee;

	private Label l_InfoTitle;
	private Label l_InfoFixed;
	private Label l_InfoAge;
	private Label l_InfoProdPrice;
	private Label l_InfoAnimalPrice;
	private Label l_InfoProdRate;

	private Rectangle separator;

	private Button b_sellAnimal;

	private Button b_seeStorage;
	private Button b_seeUpgrades;

	private Button b_prodChicken;
	private Button b_prodCow;
	private Button b_prodSheep;
	private Button b_prodGoat;
	private Button b_prodBee;

	private Label l_StorageTitle;
	private Label l_prodFirst;
	private Label l_prodLast;

	private Button b_prodSellFirst;
	private Button b_prodSellLast;
	private Button b_prodUpgrade;

	private Label l_QueueTitle;
	private Label l_Queue1;
	private Label l_Queue2;
	private Label l_Queue3;
	private Label l_Queue4;

	private Label l_time;
	private Label l_money;
	
	private int menuX1;
	private int menuX2;
	private int menuY1;
	private int menuY2;
	private int instructionsX1;
	private int instructionsX2;
	private int instructionsY1;
	private int instructionsY2;
	
	private int nextMoX1;
	private int nextMoX2;
	private int nextMoY1;
	private int nextMoY2;
	
	private int endingX;
	private int endingY;

	//constructor class that initializes many variables
	public Main()
	{
		WINDOW_WIDTH = 1000;
		WINDOW_HEIGHT = 700;

		timer = new Timer();
		balance = new Money();
		storage = new Storage();
		queue = new MyArrayQueue();

		menuBackgroundFile = "./src/graphics/background_menu1.png";
		gameBackgroundFile = "./src/graphics/background_menu7.png";
		endBackgroundFile = "./src/graphics/background_menu8.png";
		animalImage = "./src/graphics/";
		plotImage = "./src/graphics/plot";
		imageEnding = ".png";

		numAnimals = 5;
		Animal[] allAnimals = {sampleChicken, sampleCow, sampleSheep, sampleGoat, sampleBee};
		this.allAnimals = allAnimals;

		menuScene = new Scene(menuBackground, WINDOW_WIDTH, WINDOW_HEIGHT);
		gameScene = new Scene(gameBackground, WINDOW_WIDTH, WINDOW_HEIGHT);
		endScene = new Scene(endBackground, WINDOW_WIDTH, WINDOW_HEIGHT);
		menuBackground = new Group();
		gameBackground = new Group();
		endBackground = new Group();
		sidebar = new VBox();
		bottombar = new HBox();
		
		buyLabelTitleFont = Font.font("Verdana", FontWeight.EXTRA_BOLD, 14);
		buyLabelFont = Font.font("Verdana", FontWeight.BOLD, 12);
		topLabelFont = Font.font("Verdana", FontWeight.EXTRA_BOLD, 22);
		smallLabelFont = Font.font("Verdana", 12);

		wholeFormat = new DecimalFormat( "#,###");
		decimalFormat = new DecimalFormat( "$#,###.00");

		//initializes the coordinates of the animal pens, adds them to a 2D array
		double[] row1 = {115,240};
		double[] row2 = {315,440};
		double[] row3 = {515,640};
		double[] col1 = {60, 260};
		double[] col2 = {300, 500};
		double[] col3 = {550, 760};

		double[][] rows = {row1, row2, row3};
		double[][] cols = {col1, col2, col3};
		this.rowCoords = rows;
		this.colCoords = cols;

		numPlots = 9;
		currentPlot = -1;
		
		//initializes the plots
		plots = new Plots[numPlots];
		for (int i=0; i<numPlots; i++)
			plots[i] = new Plots(i, colCoords[i%3], rowCoords[(int) Math.floor(i/3)]);

		sampleChicken = new Chicken();
		sampleCow = new Cow();
		sampleSheep= new Sheep();
		sampleGoat = new Goat();
		sampleBee= new Bee();
		
		currentProduct = 0;
		currentProductAmounts = null;
		upgradeMultiplier = 2;
		
		menuX1 = 650;
		menuX2 = 950;
		menuY1 = 110;
		menuY2 = 210;
		instructionsX1 = 650;
		instructionsX2 = 950;
		instructionsY1 = 310;
		instructionsY2 = 410;
		
		nextMoX1 = 500;
		nextMoX2 = 600;
		nextMoY1 = 10;
		nextMoY2 = 60;

		endingX = 450;
		endingY = 250;
		
		//calls various methods that are used to setup the program
		loadFiles();
		drawMenu();
		setUpButtonsLabels();
	}

	//main method that launches the javafx screen
	public static void main(String[] args) {
		launch(args);
	}

	//within the start class, it checks for user input (via mouse) and performs various actions
	@Override
	public void start(Stage primaryStage) throws Exception 
	{  	
		//if the user clicks on the menu screen
		menuScene.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) {
				double mouseX = event.getX();
				double mouseY = event.getY();
				
				//MOVE Theses---------------------------------------
				
				//if user clicks 'play' button, it moves to the game screen
				if (mouseX >= menuX1 && mouseX <= menuX2 && mouseY >= menuY1 && mouseY <= menuY2)
				{
					drawGame();
					primaryStage.setScene(gameScene);
				}

				//if user clicks 'instructions' button, it moves to the instructions
				if (mouseX >= instructionsX1 && mouseX <= instructionsX2 && mouseY >= instructionsY1 && mouseY <= instructionsY2)
				{
					System.out.println("do instructions!!");
					//TODO; =-----------------------------------------------------------------------------------------------
				}
			}
		});

		//if the user clicks of the game screen (not a button)
		gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) {
				double mouseX = event.getSceneX();
				double mouseY = event.getSceneY();

				//checks to see if any of the plots are clicked on
				outerloop:
					for (int i=0; i<numPlots; i++)
					{
						if (mouseX >= plots[i].getxCoords()[0] && mouseX <= plots[i].getxCoords()[1])
						{						
							if(mouseY >= plots[i].getyCoords()[0] && mouseY <= plots[i].getyCoords()[1])
							{
								//if the mouse clicked within the coords of a plot, it sets the currentPlot to that plot's number
								currentPlot = i;
								break outerloop;
							}
						}
						else
						{
							currentPlot = -1;
						}
					}				

				//checks to see if the next month button was clicked
				if (mouseX >= nextMoX1 && mouseX <= nextMoX2)
				{	
					if (mouseY >= nextMoY1 && mouseY <= nextMoY2)
					{
						//only runs if the time limit has not been reached
						if (!timer.isOver())
						{
							//increases month and products (based on what is produced), then it updates the labels
							timer.increaseCurrentTime();
							l_time.setText(timer.getCurrentTimeString());	
							updateStorage();

							//if the upgrades queue has items in it, it will 'process'/dequeue the first one and update the storage acoordingly
							if (!queue.isEmpty())
							{
								MyNode temp = queue.dequeue();
								storage.addItems(temp.getProductType(), 0, temp.getAmountProduct());
								changeStorage();
							}						
						}
						//if the game has reached the of the allowed time, this loop runs
						else
						{
							drawEnd(); //sets up the ending screen, before displaying it

							//sells all the remaining animals at purchase price
							for(int i=0; i<numPlots; i++)
							{
								if(!plots[i].isEmpty())
								{
									balance.sellItem(plots[i].getAnimal().getPurchasePrice());
									plots[i].clearAnimal();
								}
							}

							double amountAdded = 0; //variable used to keep track of money being added
							
							//sells all remaining products and adds it to the balance.
							for(int i=0; i<numAnimals; i++)
							{
								amountAdded += storage.getNumItems(i)[0]*allAnimals[i].getProductPrice(timer.getCurrentTime());
								amountAdded += storage.getNumItems(i)[1]*allAnimals[i].getProductPrice(timer.getCurrentTime()*upgradeMultiplier);								

								//removes all items from the storage
								storage.removeItems(i, storage.getNumItems(i)[0], storage.getNumItems(i)[1]);
								currentProductAmounts = storage.getNumItems(i);
								changeStorage();
							}

							balance.sellItem(amountAdded);

							//creates a label to display the final total
							Label endingAmount = new Label(decimalFormat.format(balance.getBalance()));
							endingAmount.setFont(topLabelFont);
							endingAmount.setTextFill(Color.BLACK);
							endingAmount.setLayoutX(endingX);
							endingAmount.setLayoutY(endingY);
							
							endBackground.getChildren().addAll(endingAmount);
							primaryStage.setScene(endScene);
						}
					}
				}

				//when the screen in clicked, the side panel is always cleared, then redrawn if the user clicked on a plot
				sidebar.getChildren().clear();
				drawBuyButtons();
				drawInfo();
			}
		});

		endScene.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) {
				double mouseX = event.getX();
				double mouseY = event.getY();

				//if user clicks 'Return to Menu' button, it moves to the game screen
				if (mouseX >= 425 && mouseX <= 610 && mouseY >= 340 && mouseY <= 400)
				{
					gameBackground.getChildren().clear();
					endBackground.getChildren().clear();
					primaryStage.setScene(menuScene);
				}
			}
		});

		//buttons for buying animals
		b_BuyChick.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Chicken c = new Chicken();

				//if the user has enough money, the animal is purchased
				if(balance.canBuy(c.getPurchasePrice()))
				{
					Plots current = plots[currentPlot];
					current.setAnimal(c);
					current.setAnimalType(0);

					//updates the visuals on screen
					balance.buyItem(c.getPurchasePrice());
					l_money.setText(decimalFormat.format(balance.getBalance()));
					currentPlot=-1;
					sidebar.getChildren().clear();
					drawPlots();
				}

			}
		});

		b_BuyCow.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				Cow c = new Cow();

				//if the user has enough money, the animal is purchased
				if(balance.canBuy(c.getPurchasePrice()))
				{
					Plots current = plots[currentPlot];
					current.setAnimal(c);
					current.setAnimalType(1);

					//updates the visuals on screen
					balance.buyItem(c.getPurchasePrice());
					l_money.setText(decimalFormat.format(balance.getBalance()));
					currentPlot=-1;
					sidebar.getChildren().clear();
					drawPlots();
				}
			}
		});

		b_BuySheep.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				Sheep s = new Sheep();

				//if the user has enough money, the animal is purchased
				if(balance.canBuy(s.getPurchasePrice()))
				{
					Plots current = plots[currentPlot];
					current.setAnimal(s);
					current.setAnimalType(2);

					//updates the visuals on screen
					balance.buyItem(s.getPurchasePrice());
					l_money.setText(decimalFormat.format(balance.getBalance()));
					currentPlot=-1;
					sidebar.getChildren().clear();
					drawPlots();
				}
			}
		});

		b_BuyGoat.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Goat g = new Goat();

				//if the user has enough money, the animal is purchased
				if(balance.canBuy(g.getPurchasePrice()))
				{
					Plots current = plots[currentPlot];
					current.setAnimal(g);
					current.setAnimalType(3);

					//updates the visuals on screen
					balance.buyItem(g.getPurchasePrice());
					l_money.setText(decimalFormat.format(balance.getBalance()));
					currentPlot=-1;
					sidebar.getChildren().clear();
					drawPlots();
				}
			}
		});

		b_BuyBee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Bee b = new Bee();

				//if the user has enough money, the animal is purchased
				if(balance.canBuy(b.getPurchasePrice()))
				{
					Plots current = plots[currentPlot];
					current.setAnimal(b);
					current.setAnimalType(4);

					//updates the visuals on screen
					balance.buyItem(b.getPurchasePrice());
					l_money.setText(decimalFormat.format(balance.getBalance()));
					currentPlot=-1;
					sidebar.getChildren().clear();
					drawPlots();
				}
			}
		});

		//button for when the user sells an animal
		b_sellAnimal.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Plots current = plots[currentPlot];

				current.clearAnimal();

				//updates the visuals on screen
				balance.sellItem(currentAnimalSell);
				l_money.setText(decimalFormat.format(balance.getBalance()));
				currentPlot=-1;
				sidebar.getChildren().clear();
				drawPlots();
			}
		});

		b_seeStorage.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				drawStorage();
			}
		});

		b_prodChicken.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				currentProduct = 0;
				currentProductAmounts = storage.getNumItems(currentProduct);
				changeStorage();
			}
		});

		b_prodCow.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				currentProduct = 1;
				currentProductAmounts = storage.getNumItems(currentProduct);
				changeStorage();
			}
		});

		b_prodSheep.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				currentProduct = 2;
				currentProductAmounts = storage.getNumItems(currentProduct);
				changeStorage();
			}
		});

		b_prodGoat.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				currentProduct = 3;
				currentProductAmounts = storage.getNumItems(currentProduct);
				changeStorage();
			}
		});

		b_prodBee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				currentProduct = 4;
				currentProductAmounts = storage.getNumItems(currentProduct);
				changeStorage();
			}
		});

		//this button is clicked to selling the basic product of the current animal's product
		b_prodSellFirst.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				double amount = currentProductAmounts[0]*allAnimals[currentProduct].getProductPrice(timer.getCurrentTime());
				storage.removeItems(currentProduct, currentProductAmounts[0], 0);
				currentProductAmounts = storage.getNumItems(currentProduct);

				balance.sellItem(amount);
				l_money.setText(decimalFormat.format(balance.getBalance()));
				changeStorage();

			}
		});

		//this button is clicked to selling the special product of a current animal's product
		b_prodSellLast.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				double amount = currentProductAmounts[1]*allAnimals[currentProduct].getProductPrice(timer.getCurrentTime()*upgradeMultiplier);
				storage.removeItems(currentProduct, 0, currentProductAmounts[1]);
				currentProductAmounts = storage.getNumItems(currentProduct);

				balance.sellItem(amount);
				l_money.setText(decimalFormat.format(balance.getBalance()));
				changeStorage();
			}
		});

		b_prodUpgrade.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentProductAmounts[0]> 0)
				{
					MyNode temp = new MyNode(currentProduct, currentProductAmounts[0]);
					queue.enqueue(temp);
					storage.removeItems(currentProduct, currentProductAmounts[0], 0);

					currentProductAmounts = storage.getNumItems(currentProduct);
					changeStorage();
				}
			}
		});

		b_seeUpgrades.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				drawUpgrades();
			}
		});

		primaryStage.setTitle("AllenLiu_Assignment1_ICS4U");
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}


	//draws the menu screen
	public void drawMenu()
	{ 
		ImageView currentView = background.get(0);
		menuBackground.getChildren().add(currentView); 
	} 

	public void drawGame()
	{
		sidebar.setLayoutX(830);
		sidebar.setLayoutY(90);
		sidebar.setSpacing(10);

		bottombar.setLayoutX(830);
		bottombar.setLayoutY(600);
		bottombar.setSpacing(10);

		ImageView currentView = background.get(1);
		gameBackground.getChildren().addAll(currentView, sidebar, bottombar);
		drawTimerMoney();
		drawPlots();
	}

	public void drawEnd()
	{
		ImageView currentView = background.get(2);
		endBackground.getChildren().addAll(currentView);
	}

	public void drawTimerMoney()
	{
		l_time = new Label(timer.getCurrentTimeString());
		l_time.setFont(topLabelFont);
		l_time.setLayoutX(320);
		l_time.setLayoutY(25);
		l_time.setTextFill(Color.BLACK);

		l_money = new Label(decimalFormat.format(balance.getBalance()));
		l_money.setFont(topLabelFont);
		l_money.setLayoutX(60);
		l_money.setLayoutY(40);
		l_money.setTextFill(Color.BLACK);			

		gameBackground.getChildren().addAll(l_money, l_time);

	}

	public void setUpButtonsLabels()
	{
		b_BuyChick = new Button("Buy Chicken");
		b_BuyCow = new Button("Buy Cow");
		b_BuySheep = new Button("Buy Sheep");
		b_BuyGoat = new Button("Buy Goat");
		b_BuyBee = new Button("Buy Bee");

		//all this can later be moved to some initializer class, so its not done each time a click happens
		b_BuyChick.setPrefWidth(125);
		b_BuyCow.setPrefWidth(125);
		b_BuySheep.setPrefWidth(125);
		b_BuyGoat.setPrefWidth(125);
		b_BuyBee.setPrefWidth(125);

		ImageView smallChicken = animalImages.get(0);
		smallChicken.setScaleX(0.5);
		smallChicken.setScaleY(0.5);
		ImageView smallCow = animalImages.get(1);
		smallCow.setScaleX(0.5);
		smallCow.setScaleY(0.5);
		ImageView smallSheep = animalImages.get(2);
		smallSheep.setScaleX(0.5);
		smallSheep.setScaleY(0.5);
		ImageView smallGoat = animalImages.get(3);
		smallGoat.setScaleX(0.5);
		smallGoat.setScaleY(0.5);
		ImageView smallBee = animalImages.get(4);
		smallBee.setScaleX(0.5);
		smallBee.setScaleY(0.5);

		b_BuyChick.setGraphic(smallChicken); 
		b_BuyCow.setGraphic(smallCow); 
		b_BuySheep.setGraphic(smallSheep); 
		b_BuyGoat.setGraphic(smallGoat); 
		b_BuyBee.setGraphic(smallBee); 

		b_BuyChick.setWrapText(true);
		b_BuyCow.setWrapText(true);
		b_BuySheep.setWrapText(true);
		b_BuyGoat.setWrapText(true);
		b_BuyBee.setWrapText(true);

		b_BuyChick.setPrefHeight(60);
		b_BuyCow.setPrefHeight(60);
		b_BuySheep.setPrefHeight(60);
		b_BuyGoat.setPrefHeight(60);
		b_BuyBee.setPrefHeight(60);

		l_BuyTitle = new Label("BUY AN ANIMAL");
		l_BuyChick = new Label("Chickens: $ " + wholeFormat.format(new Chicken().purchasePrice));
		l_BuyCow = new Label("Cows: $ " + wholeFormat.format(new Cow().purchasePrice));
		l_BuySheep = new Label("Sheep: $ " + wholeFormat.format(new Sheep().purchasePrice));
		l_BuyGoat = new Label("Goats: $ " + wholeFormat.format(new Goat().purchasePrice));
		l_BuyBee = new Label("Bees: $ " + wholeFormat.format(new Bee().purchasePrice));

		l_BuyTitle.setFont(buyLabelTitleFont);
		l_BuyChick.setFont(buyLabelFont);
		l_BuyCow.setFont(buyLabelFont);
		l_BuySheep.setFont(buyLabelFont);
		l_BuyGoat.setFont(buyLabelFont);
		l_BuyBee.setFont(buyLabelFont);

		l_BuyTitle.setTextFill(Color.NAVY);
		l_BuyChick.setTextFill(Color.MAROON);
		l_BuyCow.setTextFill(Color.MAROON);
		l_BuySheep.setTextFill(Color.MAROON);
		l_BuyGoat.setTextFill(Color.MAROON);
		l_BuyBee.setTextFill(Color.MAROON);

		l_InfoTitle = new Label();
		l_InfoFixed = new Label();
		l_InfoAge = new Label();
		l_InfoProdRate = new Label();
		l_InfoAnimalPrice = new Label();
		l_InfoProdPrice = new Label();

		l_InfoTitle.setFont(buyLabelTitleFont);
		l_InfoFixed.setFont(buyLabelFont);
		l_InfoAge.setFont(smallLabelFont);
		l_InfoProdRate.setFont(smallLabelFont);
		l_InfoAnimalPrice.setFont(smallLabelFont);
		l_InfoProdPrice.setFont(smallLabelFont);

		l_InfoTitle.setTextFill(Color.NAVY);
		l_InfoFixed.setTextFill(Color.MAROON);
		l_InfoAge.setTextFill(Color.BLACK);
		l_InfoProdRate.setTextFill(Color.BLACK);
		l_InfoAnimalPrice.setTextFill(Color.BLACK);
		l_InfoProdPrice.setTextFill(Color.BLACK);

		separator = new Rectangle(0, 0, 160, 4);
		separator.setFill(Color.BLACK);

		b_sellAnimal = new Button("Sell Animal");
		b_sellAnimal.setPrefWidth(160);

		b_seeStorage = new Button("See Storage");
		b_seeUpgrades = new Button("See Upgrades Queue");

		b_seeStorage.setPrefWidth(70);
		b_seeUpgrades.setPrefWidth(70);
		b_seeStorage.setPrefHeight(70);
		b_seeUpgrades.setPrefHeight(70);

		b_seeStorage.setWrapText(true);
		b_seeUpgrades.setWrapText(true);

		b_prodChicken = new Button("See Chicken Products");
		b_prodCow = new Button("See Cow Products");
		b_prodSheep = new Button("See Sheep Products");
		b_prodGoat = new Button("See Goat Products");
		b_prodBee = new Button("See Bee Products");

		b_prodChicken.setPrefWidth(160);
		b_prodCow.setPrefWidth(160);
		b_prodSheep.setPrefWidth(160);
		b_prodGoat.setPrefWidth(160);
		b_prodBee.setPrefWidth(160);

		l_StorageTitle = new Label("STORAGE");
		l_prodFirst = new Label();
		l_prodLast = new Label();

		l_StorageTitle.setFont(buyLabelTitleFont);
		l_StorageTitle.setTextFill(Color.NAVY);
		l_prodFirst.setFont(smallLabelFont);		
		l_prodLast.setFont(smallLabelFont);

		l_prodFirst.setTextFill(Color.BLACK);
		l_prodLast.setTextFill(Color.BLACK);

		b_prodSellFirst = new Button("Sell Basic Products");
		b_prodSellLast = new Button("Sell Special Products");
		b_prodUpgrade = new Button("Upgrade Basic Products");

		b_prodSellFirst.setPrefWidth(160);
		b_prodSellLast.setPrefWidth(160);
		b_prodUpgrade.setPrefWidth(160);

		l_QueueTitle = new Label("UPGRADES QUEUE");
		l_Queue1 = new Label("Next Item in Queue ");
		l_Queue2 = new Label("");
		l_Queue3 = new Label("Number of Items \nin Queue");
		l_Queue4 = new Label("");

		l_QueueTitle.setFont(buyLabelTitleFont);
		l_Queue1.setFont(buyLabelFont);
		l_Queue2.setFont(smallLabelFont);
		l_Queue3.setFont(buyLabelFont);
		l_Queue4.setFont(smallLabelFont);

		l_QueueTitle.setTextFill(Color.NAVY);
		l_Queue1.setTextFill(Color.MAROON);
		l_Queue2.setTextFill(Color.BLACK);
		l_Queue3.setTextFill(Color.MAROON);
		l_Queue4.setTextFill(Color.BLACK);

		bottombar.getChildren().addAll(b_seeStorage, b_seeUpgrades);
	}

	//this method runs at the end of every month to update the ages of animals and storage (products)
	public void updateStorage()
	{
		//checks each plot
		for(int i=0; i<numPlots; i++)
		{
			Plots current = plots[i];

			//if the plot is not empty
			if(!current.isEmpty())
			{
				Animal a = current.getAnimal();

				//increases the age, updates its new production rate, and adds all new items to storage
				a.increaseAge();
				a.updateProductionRate();
				storage.addItems(current.getAnimalType(), a.getProductionRate(), 0);
			}
		}
	}

	public void drawStorage()
	{
		sidebar.getChildren().clear();

		l_prodFirst.setText("");
		l_prodLast.setText("");

		sidebar.getChildren().addAll(l_StorageTitle, b_prodChicken, b_prodCow, b_prodSheep, 
				b_prodGoat, b_prodBee, separator, l_prodFirst, l_prodLast, b_prodSellFirst, b_prodSellLast, b_prodUpgrade);
	}

	public void changeStorage()
	{
		if(currentProduct == 0)
		{
			l_prodFirst.setText(("# Eggs: " + storage.getNumItems(currentProduct)[0]));
			l_prodLast.setText("# Omelettes: " + storage.getNumItems(currentProduct)[1]);
		}
		else if (currentProduct == 1)
		{
			l_prodFirst.setText(("# Milk: " + storage.getNumItems(currentProduct)[0]));
			l_prodLast.setText("# Butter: " + storage.getNumItems(currentProduct)[1]);
		}
		else if (currentProduct == 2)
		{
			l_prodFirst.setText(("# Wool: " + storage.getNumItems(currentProduct)[0]));
			l_prodLast.setText("# Sweaters: " + storage.getNumItems(currentProduct)[1]);
		}
		else if (currentProduct == 3)
		{
			l_prodFirst.setText(("# Cheese: " + storage.getNumItems(currentProduct)[0]));
			l_prodLast.setText("# Spreads: " + storage.getNumItems(currentProduct)[1]);
		}
		else if (currentProduct == 4)
		{
			l_prodFirst.setText(("# Honey: " + storage.getNumItems(currentProduct)[0]));
			l_prodLast.setText("# Tea: " + storage.getNumItems(currentProduct)[1]);
		}
	}

	public void drawUpgrades()
	{
		sidebar.getChildren().clear();

		if (!queue.isEmpty())
		{
			l_Queue2.setText(queue.peek().toString());
			l_Queue4.setText(Integer.toString(queue.size()));	
		}else
		{
			l_Queue2.setText("None");
			l_Queue4.setText("0");	
		}

		sidebar.getChildren().addAll(l_QueueTitle, l_Queue1, l_Queue2, l_Queue3, l_Queue4);
	}

	public void drawBuyButtons()
	{
		if(currentPlot != -1)
		{
			if (plots[currentPlot].isEmpty())
			{
				sidebar.getChildren().addAll(l_BuyTitle, l_BuyChick, b_BuyChick, l_BuyCow, b_BuyCow, l_BuySheep, b_BuySheep, l_BuyGoat, b_BuyGoat, l_BuyBee, b_BuyBee);
			}
		}
	}

	public void drawPlots()
	{
		for (int i=0; i<numPlots; i++)
		{
			Group g = new Group();
			g.setLayoutX(plots[i].getxCoords()[0]);
			g.setLayoutY(plots[i].getyCoords()[0]);


			g.getChildren().addAll(new ImageView(plotImages.get(plots[i].getAnimalType())));
			gameBackground.getChildren().addAll(g);
		}
	}

	//instanceOf conversions code taken from https://stackoverflow.com/questions/9121889/storing-subclass-object-as-superclass-and-retrieving-subclass-later
	public void drawInfo()
	{
		if(currentPlot != -1)
		{
			if (!plots[currentPlot].isEmpty())
			{
				Animal a = plots[currentPlot].getAnimal();

				//lol this is cancer 	-----------------------------------------------------------------
				if(a instanceof Chicken)
				{
					Chicken a_sub = (Chicken)a;
					a_sub.updateProductionRate();
					currentAnimalSell = a_sub.getSellPrice(timer.getCurrentTime());
					currentProdSell = a_sub.getProductPrice(timer.getCurrentTime());
					l_InfoAnimalPrice.setText("Animal Sell Price: " + 
							(decimalFormat.format(currentAnimalSell))); //this cast is very IFFY .................
					l_InfoProdPrice.setText("Product Sell Price: " + 
							(decimalFormat.format(currentProdSell)));

				}
				else if(a instanceof Cow)
				{
					Cow a_sub = (Cow)a;
					a_sub.updateProductionRate();
					currentAnimalSell = a_sub.getSellPrice(timer.getCurrentTime());
					currentProdSell = a_sub.getProductPrice(timer.getCurrentTime());
					l_InfoAnimalPrice.setText("Animal Sell Price: " + 
							(decimalFormat.format(currentAnimalSell))); //this cast is very IFFY .................
					l_InfoProdPrice.setText("Product Sell Price: " + 
							(decimalFormat.format(currentProdSell)));
				}
				else if(a instanceof Sheep)
				{
					Sheep a_sub = (Sheep)a;
					a_sub.updateProductionRate();
					currentAnimalSell = a_sub.getSellPrice(timer.getCurrentTime());
					currentProdSell = a_sub.getProductPrice(timer.getCurrentTime());
					l_InfoAnimalPrice.setText("Animal Sell Price: " + 
							(decimalFormat.format(currentAnimalSell))); //this cast is very IFFY .................
					l_InfoProdPrice.setText("Product Sell Price: " + 
							(decimalFormat.format(currentProdSell)));
				}
				else if(a instanceof Goat)
				{
					Goat a_sub = (Goat)a;
					a_sub.updateProductionRate();
					currentAnimalSell = a_sub.getSellPrice(timer.getCurrentTime());
					currentProdSell = a_sub.getProductPrice(timer.getCurrentTime());
					l_InfoAnimalPrice.setText("Animal Sell Price: " + 
							(decimalFormat.format(currentAnimalSell))); //this cast is very IFFY .................
					l_InfoProdPrice.setText("Product Sell Price: " + 
							(decimalFormat.format(currentProdSell)));
				}
				else
				{
					Bee a_sub = (Bee)a;
					a_sub.updateProductionRate();
					currentAnimalSell = a_sub.getSellPrice(timer.getCurrentTime());
					currentProdSell = a_sub.getProductPrice(timer.getCurrentTime());
					l_InfoAnimalPrice.setText("Animal Sell Price: " + 
							(decimalFormat.format(currentAnimalSell))); //this cast is very IFFY .................
					l_InfoProdPrice.setText("Product Sell Price: " + 
							(decimalFormat.format(currentProdSell)));
				}

				l_InfoTitle.setText("PLOT " + (currentPlot+1) + ": " + a.name);
				l_InfoFixed.setText("Product: \t\t " + a.product + "\nGrowth Time: \t " + 
						a.growthTime + "\nLife Time: \t " + a.lifeTime);

				l_InfoAge.setText("Age: " + a.age);
				l_InfoProdRate.setText("Production Rate: " + a.getProductionRate());

				sidebar.getChildren().addAll(l_InfoTitle, l_InfoFixed, l_InfoAge, l_InfoProdRate, 
						l_InfoAnimalPrice, l_InfoProdPrice, separator, b_sellAnimal);
			}
		}
	}

	public void loadFiles()
	{
		background = new ArrayList<ImageView>();
		animalImages = new ArrayList<ImageView>();
		plotImages = new ArrayList<Image>();

		try {

			ImageView menuView = new ImageView(new Image(new FileInputStream(menuBackgroundFile)));
			ImageView gameView = new ImageView(new Image(new FileInputStream(gameBackgroundFile)));
			ImageView endView = new ImageView(new Image(new FileInputStream(endBackgroundFile)));

			background.add(menuView);
			background.add(gameView);
			background.add(endView);

			for(int i=0; i<numAnimals; i++)
			{
				animalImages.add(new ImageView(new Image(
						new FileInputStream(animalImage + allAnimals[i].getName() + imageEnding))));
				plotImages.add(new Image (
						new FileInputStream(plotImage + "_" + allAnimals[i].getName() + imageEnding)));


			}			
			plotImages.add(new Image (new FileInputStream(plotImage + imageEnding)));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

