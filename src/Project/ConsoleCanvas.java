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
        for (int i = 0; i < ySize; i++) { //Loop through rows
            for (int j = 0; j < xSize; j++) { //Loop through columns
                //Set borders or fill with spaces
                if (i == 0 || i == ySize - 1) { //Top or bottom border
                    array[i][j] = '#'; //Fill border with #
                } 
                else if (j == 0 || j == xSize - 1) { //Left or right border
                    array[i][j] = '#'; //Fill border with #
                } 
                else {
                    array[i][j] = ' '; //Inside the canvas
                }
            }
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
