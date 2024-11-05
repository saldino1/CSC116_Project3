import java.io.*;
import java.util.*;

/**
 * ImageEditor class changes images
 * Inverst, high contrast or greyscale options
 * @author Amelia Saldino
 */
public class ImageEditor {
    /** Color threshold for high contrast transformation. */
    private static final int HIGH_CONTRAST_THRESHOLD = 128;

    /** Maximum color value for PPM files. */
    private static final int MAX_COLOR_VALUE = 255;

    /** Number of Values in RGB */
    private static final int NUM_VAL_RGB = 3;

    /** Number of correct cmd line args */
    private static final int VALID_CMD = 3;

    /**
     * Main method
     * Expects three command-line arguments: flag, input file, and output file.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if(args.length != VALID_CMD) {
            System.out.println("Usage: java -cp bin ImageEditor {-I|-H|-G} infile outfile");
            return;
        }

        String flag = args[0];
        String infile = args[1];
        String outfile = args[2];

        if (!flag.equals("-I") && !flag.equals("-H") && !flag.equals("-G")) {
            System.out.println("Usage: java -cp bin ImageEditor {-I|-H|-G} infile outfile");
            return;
        }

        if(!infile.endsWith(".ppm")) {
            System.out.println("Invalid input file extension");
            return;
        }

        if(!outfile.endsWith(".ppm")) {
            System.out.println("Invalid output file extension");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        

        try {
            FileInputStream file = new FileInputStream(infile);
            file.close();
        } catch (IOException e) {
            System.out.println("Unable to access input file: " + infile);
            scanner.close();
            return;
        }

        if (fileExists(outfile)) {
            try (Scanner keyBoardScanner = new Scanner(System.in)) {
                System.out.print(outfile + " exists - OK to overwrite(y,n)?: ");
                String response = keyBoardScanner.nextLine();
                if (!response.trim().toLowerCase().startsWith("y")) {
                    keyBoardScanner.close();
                    return;
                }
            }
        } 

        try (FileOutputStream fos = new FileOutputStream(outfile);
             PrintWriter writer = new PrintWriter(fos)) {
            
            FileInputStream testFile = new FileInputStream(infile);
        
            Scanner inTesting = new Scanner(testFile);
            if(!validInputFile(inTesting)) {
                System.out.println("Invalid input file");
                inTesting.close();
                return;
            }
            inTesting.close();

            FileInputStream file = new FileInputStream(infile);
            Scanner in = new Scanner(file);

            int[][] pixels = getPixelValues(in);
            
            if(flag.equals("-I")) {
                invert(pixels);
            }
            if(flag.equals("-H")) {
                highContrast(pixels);
            }
            if(flag.equals("-G")) {
                greyScale(pixels);
            }
            outputPPM(writer, pixels);
            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot create output file");
            scanner.close();
            return;
        }
        catch (IOException e) {
            System.out.println("Cannot create output file");
            scanner.close();
            return;
        }
        
        scanner.close();
    }

    /**
     * Checks if a file exists
     * @param filename name of the file to check
     * @return true if the file exists, false otherwise
     */
    private static boolean fileExists(String filename) {
        try (FileInputStream file = new FileInputStream(filename)) {
            file.close();
            return true; 
        } catch (IOException e) {
            return false; 
        }
    }

    /**
     * Checks if inputfile is correctly formatted
     * @param in scanner object to read first part of file
     * @return true if the file is valid, false otherwise
     * @throws IllegalArgumentException if scanner is null
     */
    private static boolean validInputFile(Scanner in) {
        if (in == null) {
            throw new IllegalArgumentException("Null scanner");
        }

        String format = in.next();
        if (!format.equals("P3")) {
            return false; 
        }

        if(!in.hasNextInt()) {
            return false;
        }
        int cols = in.nextInt();
        if (cols <= 0) {
            return false; 
        }

        if(!in.hasNextInt()) {
            return false;
        }
        int rows = in.nextInt();
        if (rows <= 0) {
            return false; 
        }

        if(!in.hasNextInt()) {
            return false;
        }
        int maxColorVal = in.nextInt();
        if (maxColorVal != MAX_COLOR_VALUE) {
            return false; 
        }
        return true;
    }

    /**
     * Reads PPM turning it into 2d int array
     * @param in Scanner for the input file
     * @return a 2D array of pixel values, null if validation impossible
     * @throws IllegalArgumentException if the input is invalid
     */
    public static int[][] getPixelValues(Scanner in) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }

        String format = in.next();
        if (!format.equals("P3")) {
            System.out.println(format);
            return null; 
        }

        if(!in.hasNextInt()) {
            System.out.println("Bello20001");
            return null;
        }
        int cols = in.nextInt();
        if (cols <= 0) {
            System.out.println("Bello5");
            return null; 
        }

        if(!in.hasNextInt()) {
            System.out.println("Bello4");
            return null;
        }
        int rows = in.nextInt();
        if (rows <= 0) {
            System.out.println("Bello3");
            return null; 
        }

        if(!in.hasNextInt()) {
            System.out.println("Bello2");
            return null;
        }
        int maxColorVal = in.nextInt();
        if (maxColorVal != MAX_COLOR_VALUE) {
            System.out.println("Bello");
            return null; 
        }

        int[][] pixelValues = new int[rows][cols * NUM_VAL_RGB]; 

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols * NUM_VAL_RGB; j++) {
                if (in.hasNextInt()) {
                    int value = in.nextInt();
                    pixelValues[i][j] = value; 
                }
            }
            
        }

        return pixelValues;
    }

    /**
     * Inverts the pixel array
     * @param pixels The 2D array of pixel values
     * @throws IllegalArgumentException if the pixels array is null, invalid, or jagged
     */
    public static void invert(int[][] pixels) {   
         
        if(pixels == null) {
            throw new IllegalArgumentException("Null array");
        }
        if(pixels.length > 0 && pixels[0].length % NUM_VAL_RGB != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }

        for(int i = 0; i < pixels.length; ++i) {
            for(int j = 0; j < pixels[i].length; ++j) {
                pixels[i][j] = MAX_COLOR_VALUE - pixels[i][j];
            }
        }
    }

    /**
     * Converts to high contrast
     * @param pixels The 2D array of pixel values
     * @throws IllegalArgumentException if the pixels array is null, invalid, or jagged
     */
    public static void highContrast(int[][] pixels) {
        if(pixels == null) {
            throw new IllegalArgumentException("Null array");
        }
        if(pixels.length > 0 && pixels[0].length % NUM_VAL_RGB != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }
        for(int i = 0; i < pixels.length; ++i) {
            for(int j = 0; j < pixels[i].length; ++j) {
                if(pixels[i][j] < HIGH_CONTRAST_THRESHOLD) {
                    pixels[i][j] = 0;
                }
                else {
                    pixels[i][j] = MAX_COLOR_VALUE;
                }
            }
        }
        
    }

    /**
     * Converts to greyscalez
     * @param pixels The 2D array of pixel values
     * @throws IllegalArgumentException if the pixels array is null, invalid, or jagged
     */
    public static void greyScale(int[][] pixels) {
        if(pixels == null) {
            throw new IllegalArgumentException("Null array");
        }
        if(pixels.length > 0 && pixels[0].length % NUM_VAL_RGB != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }

        for(int i = 0; i < pixels.length; ++i) {
            for(int j = 0; j < pixels[i].length; ++j) {
                if (j % NUM_VAL_RGB != 0) {
                    continue;
                }
                int average = (pixels[i][j] + pixels[i][j + 1] + pixels[i][j + 2]) / 3;
                pixels[i][j] = average;
                pixels[i][j + 1] = average;
                pixels[i][j + 2] = average;
            }
        }
    }

    /**
     * Outputs the pixel values to file
     * @param out The PrintWriter to output
     * @param pixels The 2D array of pixel values
     * @throws IllegalArgumentException if the 
     * output writer or pixels array is null, invalid, or jagged
     */
    public static void outputPPM(PrintWriter out, int[][] pixels) {
        if (out == null) {
            throw new IllegalArgumentException("Null file");
        }
        if(pixels == null) {
            throw new IllegalArgumentException("Null array");
        }
        if(pixels.length > 0 && pixels[0].length % NUM_VAL_RGB != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }
        out.println("P3");
        out.println(pixels[0].length / NUM_VAL_RGB + " " + pixels.length);
        out.printf("%d\n", MAX_COLOR_VALUE);

        for(int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                out.print(pixels[i][j]);
                if (j < pixels[i].length - 1) {
                    out.print(" "); 
                }
            }
            out.println();
        }
    }
}
