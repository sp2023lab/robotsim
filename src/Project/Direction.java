package Project;

import java.util.Random;

public enum Direction {
    
	N, E, S, W; //Directions: N (North), E (East), S (South), W (West)

	
    //Get the next direction in the enum
    public Direction next() {
        return values()[(this.ordinal() + 1) % values().length];
        //Increment index, wrap around using modulus operator
    }

    
    //Get the previous direction in the enum
    public Direction previous() {
        return values()[(this.ordinal() + values().length - 1) % values().length];
        //Decrement index, wrap around using modulus operator
    }

    
    //Get the current direction (redundant in this context but kept as provided)
    public Direction getDirection() {
        return this; //Return the current enum constant
    }

    
    //Get a random direction
    public static Direction randomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)]; //Select a random direction from the enum constants
    }
    
}
