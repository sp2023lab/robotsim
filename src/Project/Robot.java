package Project;

public class Robot {
	
	private int xPos, yPos; //xPos is the x coordinate for the robot, yPos is the y coordinate for the robot
	private String ID; //ID to track the robot
	private Direction direction; //direction to show which way the robot is facing
	
	
	Robot(int xPos, int yPos, String ID, Direction direction) {
		this.xPos = xPos; //x Position
		this.yPos = yPos; //y Position
		this.ID = ID; //ID
		this.direction = direction; //direction
	}	

	public int xPos() {
		return xPos; //Returns the x position of the robot
	}
	public int yPos() {
		return yPos; //Returns the y position of the robot
	}	
	public String ID() {
		return ID; //Returns the ID of the robot
	}
	public Direction direction() { 
		return direction; //Returns the direction
	}

	public boolean isHere(int x, int y) {
		return x == xPos() && y == yPos(); 
		/**
		Returns a boolean value for whether the robot is here or not.
		This is done by returning x being equal to the x position of the robot and...
		...returning y being equal to the the y position of the robot which is done...
		...in a boolean function either returning true or false
		**/ 
	}
	
	public void tryToMove(RobotArena arena) {
		int newX = xPos(), newY = yPos(); 
		/**
		newX is an integer which is the x position of the robot
		newY is an integer which is the y position of the robot
		**/
		switch (direction.getDirection()) { //Gets the direction of the robot
			case N: newY--; break; //If direction is N, then the robot goes up on the map (y goes down in value)
			case W: newX--; break; //If direction is W, then the robot goes left on the map (x goes down in value)
			case S: newY++; break; //If direction is S, then the robot goes down on the map (y goes up in value)
			case E: newX++; break; //If direction is E, then the robot goes right on the map (y goes up in value)
		}
		if (arena.canMoveHere(newX, newY)) { //Checks if the robot can move to the new position (newX and newY)
			xPos = newX; //If true then the robots x position is updated
			yPos = newY; //If true then the robots y position is updated
		}
		else {
			direction = direction.next(); //If not true, the robots direction is changed
		}
	}
	
	public void displayRobot(ConsoleCanvas canvas) { 
		if (xPos() >= 0 && xPos() < canvas.array[0].length && yPos() >= 0 && yPos() < canvas.array.length) { 
			//Checks if the robots coordinates are within the canvas
			System.out.print("Displaying robot: "); //Outputs that the robot is displayed
			System.out.println(toString()); //Outputs a statement
			canvas.array[yPos][xPos] = ID().charAt(1); //Puts the robot ID
		}
		else {
			System.out.printf("\nRobot is out of bounds: ",toString()); //Outputs that the robot is out of bounds with a statement
		}
	}
	
	public String toString() {
		return "Robot ID: " +ID()+", coordinates are: ("+xPos()+","+yPos()+"), direction is: "+direction()+".\n"; 
		//Returns a statement stating the ID, x position and y position, as well as direction of a robot
	}

}
