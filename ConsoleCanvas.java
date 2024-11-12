package Project;

public class ConsoleCanvas {
    
    private int xSize, ySize; //xSize is width, ySize is height
    char[][] array; //array is a 2D array
    private String studentID = "32010160"; //studentID is 32010160

    ConsoleCanvas(int xSize, int ySize) {
        this.xSize = xSize; //Width
        this.ySize = ySize; //Height
        this.array = new char[ySize][xSize]; //Initialize array as ySize (rows) by xSize (columns)
        //Initialize the canvas by filling in the array with spaces
        for (int i = 0; i < ySize; i++) { //Loop through the rows (height)
            for (int j = 0; j < xSize; j++) { //Loop through the columns (width)
                array[i][j] = ' '; //Put [space] for each item when initializing the canvas
            }
        }
        //Set borders
        for (int j = 0; j < xSize; j++) { //Loop through the width
            array[0][j] = '#'; // Top border
            array[ySize - 1][j] = '#'; //Bottom border
        }
        for (int i = 0; i < ySize; i++) { //Loop through the height
            array[i][0] = '#'; // Left border
            array[i][xSize - 1] = '#'; //Right border
        }
        // Center the student ID on the top border
        int idStartPos = (xSize - studentID.length()) / 2; //Make sure the starting position is correct by 
        for (int i = 0; i < studentID.length(); i++) { //Parses through each character of the studentID
            if (idStartPos + i >= 0 && idStartPos + i < xSize) { //Ensure idStartPos + i doesn't exceed xSize
                array[0][idStartPos + i] = studentID.charAt(i); //Puts each character from the studentID into the designated place in the array
            }
        }
        
        toString(); //Outputs a message
    }
    
    public void showIt() { //Display the canvas
        for (int i = 0; i < ySize; i++) { //Loop through rows (height)
            for (int j = 0; j < xSize; j++) { //Loop through columns (width)
                System.out.print(array[i][j]); //Output the item
            }
            System.out.println(); //Move to the next line after each row
        }
    }
    
    public String toString() { // Provide size information
        return "The size is: " + xSize + " by " + ySize + "."; //Returns the x and y size of the canvas
    }
}
