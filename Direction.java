package Project;

import java.util.Random;

public enum Direction {

    N, E, S, W; //Uses 4 directions which are N for North, E for East, S for South and W for West

    public Direction next() {
        int currentIndex = this.ordinal(); 
        //currentIndex is the returned value of the current enum constant in the enum declaration
        Direction currentDirection = values()[(currentIndex + 1) % values().length]; 
        //Computes the index of the next direction whilst ensuring the next direction is within the enum, else it returns to the start direction of the enum
        return currentDirection; //Returns the current direction
    }

    public Direction previous() { 
        int currentIndex = this.ordinal(); 
        //currentIndex is the returned value of the current enum constant in the enum declaration
        Direction currentDirection = values()[(currentIndex + values().length - 1) % values().length];
        //Computes the index of the previous direction whilst ensuring the previous direction is within the enum, else it returns to the last direction of the enum
        return currentDirection; //Returns the current direction
    }

    public Direction getDirection() {
        int currentIndex = this.ordinal(); 
        //currentIndex is the returned value of the current enum constant in the enum declaration
        Direction currentDirection = values()[(currentIndex)]; 
        //Gets the value for the current direction
        return currentDirection; //Returns the current direction
    }
    
    public static Direction randomDirection() {
        Random random = new Random(); //Initializes a new Random object.
        Direction[] directions = Direction.values(); //Retrieve all constants from the Direction enum.
        return directions[random.nextInt(directions.length)]; //Selects and return a random Direction.
    }

}
