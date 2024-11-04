import java.io.PrintWriter;
import java.util.*;

public class ImageEditor {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            scanner.close();
        }
    }

    //Reads and validates the ppm file and returns
    //a 2D array containing the pixel RGB values
    //
    //The number of elements in each row of the array
    //will be 3 * the number of columns of pixels, since
    //3 integers are used to represent each pixel.
    //
    //Returns null, if any of the following are true
    //  - the first token in the file is not the string "P3"
    //  - the second token in the file, which represents the 
    //    number of columns, is not a positive integer
    //  - the third token in the file, which represents the 
    //    number of rows, is not a positive integer
    //  - the fourth token in the file is not the integer 255
    //  - the fourth token is not followed by 
    //    rows * cols * 3 integer values in the range 0 - 255
    //Any remaining tokens may be ignored. 
    //
    //Throws an IllegalArgumentException with the message
    //"Null file" if in is null   
    public static int[][] getPixelValues(Scanner in) {
        return null;        
    }

    //Inverts each of the RGB values in the pixels array
    //
    //Throws an IllegalArgumentException with the message
    //"Null array" if pixels is null
    //
    //Throws an IllegalArgumentException with the message
    //"Invalid array" if the number of elements in row 0
    //is not a multiple of 3
    //
    //Throws an IllegalArgumentException with the message
    //"Jagged array" if the pixels array is "jagged", i.e.,
    //each row does not have the same number of elements as
    //every other row.
    //
    //NOTE: You must check for invalid parameters (arguments) in the order given above.
    public static void invert(int[][] pixels) {    

    }

    //Converts the RGB values in the pixels array to high contrast
    //
    //Throws an IllegalArgumentException with the message
    //"Null array" if pixels is null
    //
    //Throws an IllegalArgumentException with the message
    //"Invalid array" if the number of elements in row 0
    //is not a multiple of 3
    //    
    //Throws an IllegalArgumentException with the message
    //"Jagged array" if the pixels array is "jagged", i.e.,
    //each row does not have the same number of elements as
    //every other row.
    //
    //NOTE: You must check for invalid parameters (arguments) in the order given above.
    public static void highContrast(int[][] pixels) {
                                
    }

    //Converts the RGB values in the pixels array to grey scale
    //
    //Throws an IllegalArgumentException with the message
    //"Null array" if pixels is null
    //
    //Throws an IllegalArgumentException with the message
    //"Invalid array" if the number of elements in row 0
    //is not a multiple of 3
    //
    //Throws an IllegalArgumentException with the message
    //"Jagged array" if the pixels array is "jagged", i.e.,
    //each row does not have the same number of elements as
    //every other row.
    //
    //NOTE: You must check for invalid parameters (arguments) in the order given above.
    public static void greyScale(int[][] pixels) {
                                    
    }

    //Outputs the following lines to out
    //Line 1: P3
    //Line 2: number of columns followed by a single space followed by the number of rows
    //Line 3: 255
    //followed by lines that contain the rows of pixels. Each row of pixels 
    //must be on a separate line with one space between
    //each RGB value on the line, but no space after the last value on the line.
    //
    //Throws an IllegalArgumentException with the message
    //"Null file" if out is null
    //
    //Throws an IllegalArgumentException with the message
    //"Null array" if pixels is null
    //
    //Throws an IllegalArgumentException with the message
    //"Invalid array" if the number of elements in row 0
    //is not a multiple of 3
    //
    //Throws an IllegalArgumentException with the message
    //"Jagged array" if the pixels array is "jagged", i.e.,
    //each row does not have the same number of elements as
    //every other row.
    //
    //NOTE: You must check for invalid parameters (arguments) in the order given above.
    public static void outputPPM(PrintWriter out, int[][] pixels) {
    
    }
}