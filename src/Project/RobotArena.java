package Project;
import java.util.ArrayList;
import java.util.Random;

public class RobotArena {
	
	private int xSize, ySize; //xSize is width, ySize is height
	private ArrayList<Robot> robots; //robots to hold the array list of the Robot
	private int robotCounter; //robotCounter to count the robots
	
	
	RobotArena(int xSize, int ySize) {
		this.xSize = xSize; //Width
		this.ySize = ySize; //Height
		this.robots = new ArrayList<>(); //Array of the robots
		this.robotCounter = 0; //Starts off with 0 for the robotCounter, given no robots initially
	}
	
	
	public int xSize() {
		return xSize; //Returns the xSize for the arena
	}
	
	
	public int ySize() { 
		return ySize; //Returns the ySize for the arena
	}
	
	
	public int robotCounter() { 
		return robotCounter; //Returns the number of robots
	}
	
	
	public ArrayList<Robot> getRobots() {
		return robots; //Returns the array of robots
	}
	
	
	public boolean canMoveHere(int x, int y) {
		if (x <= 0 || y <= 0 || y >= ySize - 1 || x >= xSize - 1) { //Checks if the robot is out of range
			return false; //Returns false if out of range
		}
		
		for (Robot robot : robots) { //Parses through the array of robots
			if (robot.isHere(x, y)) { //Checks if the robot is there
				return false; //Returns false if another robot is there
			}
		}
		
		return true; //Returns true if no robot is there nor the robot is out of range
		
	}
	
	
	public void addRobot() {
		if (robotCounter >= 10) { //Checks if the robot counter is bigger than or equal to 10
			System.out.println("Max amount of 10 robots has been achieved!"); //Outputs a message
		}
		
		else {
			Robot newRobot = createRobot(); //Calls createRobot function to create a new robot
			robots.add(newRobot); //Adds a new robot to the arena
			robotCounter++; //Adds the robotCounter by 1
			System.out.println(toString()); //A statement is output
		}
	}
	
	
	private Robot createRobot() {
		Random rand = new Random(); //We need a random generator
        int xPos = rand.nextInt(xSize() - 2) + 1; //Random x position for robot, ensuring it is within the board for x size
        int yPos = rand.nextInt(ySize() - 2) + 1; //Random y position for robot, ensuring it is within the board for y size
		String ID = "R" + (robotCounter()); //ID is R with a robotCounter
		Direction direction = Direction.randomDirection(); //Random direction for the robot
		return new Robot(xPos, yPos, ID, direction); //Returns the robot
	}
	
	
	public void moveAllRobots() { 
		ConsoleCanvas canvas = new ConsoleCanvas(xSize(), ySize()); //Creates a canvas using the x and y sizes
		for (Robot robot : robots) { //Parses through the robots
			if (robot != null) { //Checks if the robot field is not null
				robot.tryToMove(this); //Moves the robot if not null
			}
		}
		canvas.showIt(); //Displays the canvas with the robots
	}
	
	
	public void showRobots() { 
		ConsoleCanvas canvas = new ConsoleCanvas(xSize(), ySize()); //Creates a canvas using the x and y sizes
		for (Robot robot : robots) { //Parses through the robots
			if (robot != null) { //Checks if the robot field is not null
				robot.displayRobot(canvas);	//Displays the robot on the canvas
			}
		}
		canvas.showIt(); //Displays the canvas with the robots
	}
		
	
	public void resetArray() {
	    robots.clear(); //Clear all robots from the list
	    robotCounter = 0; //Reset the robot counter to 0
	    ConsoleCanvas canvas = new ConsoleCanvas(xSize(), ySize()); //Create a fresh canvas to clear the display
	    canvas.showIt(); //Display an empty canvas
	}

	
	public String toString() { 
		return "The size of the arena is: " +xSize()+ " by " +ySize()+". There are: "+robotCounter()+" of the 10 available robots.";
		//Outputs a statement stating the x and y size of the arena and robotCounter in the arena
	}
	
}
