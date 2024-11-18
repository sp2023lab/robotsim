package Project;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class File {
	
	private RobotArena myArena; //Instance of RobotArena to manage and interact with robots in the arena.

	
    public File(RobotArena arena) { 
        this.myArena = arena; //arena
    }

    
    public void createNewArena(int width, int height) {
        myArena = new RobotArena(width, height); //Create a new arena with new dimensions
        myArena.resetArray(); //Reset robots and counter
    }


    public void saveArena(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
        //Initializes FileWriter in append mode which writes to 'filename' and then automatically closes it after
            List<Robot> robots = myArena.getRobots(); //Gets all of the robots from the current arena and puts it in the robots list
            
            //Code here and fix the save arena
            
            writer.write("\nArena size is: " + myArena.xSize() + " by " + myArena.ySize() + ".\n"); //Writes to the file of the arena size
        
            if (robots.isEmpty()) { //Checks if the robot list is empty
                writer.write("No robots in the arena!\n"); //Outputs a message that there are no robots in the arena
            } 
            
            else {
            	
            	for (Robot robot : robots) { //Parses through all the robots
            		System.out.print("\n"+robot.toString()+".\n");
                    writer.write("Details of the robot: ID " + robot.ID() + ", with coordinates (" + robot.xPos() + "," + robot.yPos() + "), with direction facing " + robot.direction() + ".\n");
                    //Writes the details of the robot by its ID, x and y position and direction
                }
            
            }
            
            System.out.println("Writing to file was successful!"); //Outputs a message that writing to the file was successful

        } 

        catch (IOException e) { //Handles the I/O error
            System.out.println("Writing to file was unsuccessful!"); //Outputs a message that writing to the file was unsuccessful
            e.printStackTrace(); //Print the stack trace to help diagnose the cause of the IOException
        }
    }

    public RobotArena loadArena(String filename) { 
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename)); //Reads all of the lines in the file
            boolean arenaSizeSet = false; //arenaSizeSet is initialized to false
            
            if (lines.size() > 0) { //Checks if the number of lines in the file is greater than 0

            	for (int i = 0; i < lines.size(); i++) { //Parses through the lines in the file
                    String line = lines.get(i).trim(); //Gets all of the lines in the file whilst getting rid of whitespaces within the string
                    
                    if (line.startsWith("Arena size is:") && !arenaSizeSet) { //Checks if the line indicates the start of the arena and if the size hasn't been set yet
                        String[] parts = line.split(" "); //Splits the lines into parts using space as a delimieter
                        
                        if (parts.length >= 6) { //Ensures the splitting line has 6 parts or more 
                            
                        	try {
                                int xSize = Integer.parseInt(parts[3]); //Gets the width of the arena from the file
                                int ySize = Integer.parseInt(parts[5].replace(".", "")); //Gets the height of the arena from the file
                                myArena = new RobotArena(xSize, ySize); //Creates the arena with the width and height, both from the file
                                arenaSizeSet = true; //arenaSizeSet is set to true
                            } 
                        
                            catch (NumberFormatException e) { //Handles an error when a string doesn't have the appropriate format for converting a string to a numeric value
                                System.out.println("Error parsing arena size at line " + (i + 1) + ". Skipping..."); //Outputs a message stating there has been an error parsing the arena size 
                                continue; //Skips to the next iteration
                            }
                        } 
                        
                        else {
                            System.out.println("Invalid format for arena size at line " + (i + 1) + ". Skipping..."); //Outputs a message stating the arena size format is invalid
                            continue; //Skips to the next iteration
                        }
                    } 
                    
                    else if (line.startsWith("Details of the robot:")) { //Checks if the line starts with "Details of the robot:"
            
                    	try {
                            String[] robotParts = line.split("[ ,()]+"); //Splits the lines into parts using space, square brackets, brackets and a plus sign as a delimieter
                            System.out.println("Parsed parts: " + Arrays.toString(robotParts)); // Print the content of the array
                        
                            if (robotParts.length >= 14) { //Ensures that the splitting line has 14 parts or more
                                String id = robotParts[5]; //Gets the ID
                                int xPos = Integer.parseInt(robotParts[8]); //Gets the x position
                                int yPos = Integer.parseInt(robotParts[9]); //Gets the y position
                                Direction direction = Direction.valueOf(robotParts[13].replace(".", "")); //Gets the direction
                                Robot robot = new Robot(xPos, yPos, id, direction); //Sets up the robot
                                myArena.getRobots().add(robot); //Adds the robot from the file to the arena
                                myArena.incrementRobotCounter();
                            } 
                            
                            else {
                                System.out.println("Invalid format for robot details at line " + (i + 1) + ": " + line); //Outputs a message stating that there is an invalid format for the robot details
                            }
                        
                    	} 
                        
                    	catch (IllegalArgumentException e) { //Handles the error that a method has been passed an illegal or inappropriate argument
                            System.out.println("Error parsing robot details at line " + (i + 1) + ": " + line); //Outputs a message stating that there is an error for parsing the robot details
                            e.printStackTrace(); //Print the stack trace to help diagnose the cause of the IOExveption
                            continue; //Continues to the next iteration
                        }
        
                    }
                
            	}
                
            	if (arenaSizeSet) { //Checks if arenaSizeSet is true
                    System.out.println("Reading from file was successful!"); //Outputs a message where reading from the file was successful
                    return myArena; //Returns the arena
                } 
                
            	else {
                    System.out.println("Arena size information is missing or malformed in the file."); //Outputs a message where reading from the file was unsuccessful
                }

            }

        } 
        
        catch (IOException e) { //Handles the I/O error
            System.out.println("Writing to file was unsuccessful!"); //Outputs a message that writing to the file was unsuccessful
            e.printStackTrace(); //Print the stack trace to help diagnose the cause of the IOException
        }
        
        return null; //Returns null
    }

}
