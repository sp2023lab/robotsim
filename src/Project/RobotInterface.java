package Project;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
* Simple program to show arena with multiple robots
* @author uq010160
**/

public class RobotInterface { 
 
	private Scanner s; //s is the text scanner which can parse strings 
    private RobotArena myArena; //myArena is used to set the current arena
    private File fileHandler; //fileHandler is used to handler the files

    public RobotInterface() { 
        s = new Scanner(System.in); //Uses s as an input
        myArena = new RobotArena(40, 10); //Sets the size of the arena (width by height)
        fileHandler = new File(myArena); //Sets the file using the arena from the user
        char ch = ' '; //ch is a character with [space] input
        do {
            System.out.print("Enter (A)dd Robot, get (I)nformation,"
                    + "\n(D)isplay all robots, (M)ove all robots,"
                    + "\n(S)ave arena, (L)oad arena, (N)ew file," 
                    + "\nor e(X)it > "); 
            /*
            Outputs a message where the user can chose to add a robot, get the information of the arena, display...
            ...all the robots, move all the robots, save the arena, load the arena or exit.
            */
            ch = s.next().charAt(0); //Reads the next token from the scanner, then extract the first character, and assign it to ch.
            s.nextLine(); //Consume the rest of the current line to prepare the scanner for the next input.
            
            switch (ch) {
                case 'A': 
                case 'a':
                //This is if 'a' or 'A' is pressed
                    myArena.addRobot(); //Adds a robot to the arena
                    break;
                
                case 'I':
                case 'i':
                //This is if 'i' or 'I' is pressed
                    System.out.println(myArena); //Outputs the arena information
                    break;
                
                case 'D':
                case 'd':
                //This is if 'd' or 'D' is pressed
                	simulate(1); //Calls the simulate function where nTimes is 1
                    break;
                
                case 'M':
                case 'm':
                //This is if 'm' or 'M' is pressed
                	simulate(10); //Calls the simulate function where nTimes is 10
                    break;
                
                case 'S':
                case 's':
                //This is if 's' or 'S' is pressed
                    System.out.println("Enter the name of the file you would like to save > "); 
                    //Outputs a message asking a user to enter the name of the file they would like to save
                    String saveFilename = s.nextLine(); //Gets the name of the file as an input from the user
                    fileHandler.saveArena(saveFilename); //Saves the file using the input from the user
                    break;
                
                case 'L':
                case 'l':
                //This is if 'l' or 'L' is pressed
                    System.out.println("Enter the name of the file you would like to load > "); 
                    //Outputs a message asking the user to enter the name of the file they would like to load
                    String loadFilename = s.nextLine(); //Gets the name of the file as an input from the user
                    RobotArena loadedArena = fileHandler.loadArena(loadFilename); //Loads the file using the input from the user
                    if (loadedArena != null) { //Checks if loadedArena is not null
                        myArena = loadedArena; //The loaded arena is then set to myArena (which is the current arena)
                        fileHandler = new File(myArena); //Creates a new file instance which points to the location specified by myArena
                        System.out.println("Arena loaded successfully!"); //Outputs a message saying the arena has been successfully loaded
                        myArena.showRobots(); //Shows the robots in the current arena
                    }
                    break;
                
                case 'N':
                case 'n':
                    System.out.println("Enter new arena width > ");
                    int width = s.nextInt(); //Get width input
                    System.out.println("Enter new arena height > ");
                    int height = s.nextInt(); //Get height input
                    fileHandler.createNewArena(width, height); //Create a new arena and reset
                    myArena = new RobotArena(width, height); //Ensure myArena is updated with the new instance
                    System.out.println("New arena created - everything has been reset!");
                    break;
                
                case 'x':
                //This is if 'x' is pressed
                    ch = 'X'; //Sets ch to X
                    break;
            }
        } 
        
        while (ch != 'X'); //Checks if ch is not equal to X
        s.close(); //Closes the scanner
    }

    private void simulate(int nTimes) {
        for (int ct = 0; ct < nTimes; ct++) { //Iterate nTimes to simulate the animation cycle
            //nTimes is the total number of animation cycles to be displayed to the user
            myArena.moveAllRobots(); //Move all robots in the arena
            System.out.println(); //Print a blank line for separation in the output
            myArena.showRobots(); //Display the current positions of the robots
            
            try {
                TimeUnit.MILLISECONDS.sleep(200); //Pause for 200 milliseconds before the next update
            } 
            
            catch (InterruptedException e) { //Handle any interruption during the sleep
                e.printStackTrace(); //Print the stack trace for debugging
            }

        }

    }


    //Main code below
    public static void main(String[] args) { 
        new RobotInterface(); //Call the RobotInterface function
    }
}
