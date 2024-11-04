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
    /**
     * Main method
     * Expects three command-line arguments: flag, input file, and output file.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if(args.length != 3) {
            System.err.println("Usage: java -cp bin ImageEditor {-I|-H|-G} infile outfile");
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
            System.err.println("Invalid input file extension");
            return;
        }

        if(!outfile.endsWith(".ppm")) {
            System.err.println("Invalid output file extension");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        

        try (FileInputStream fis = new FileInputStream(infile)) {

        } catch (IOException e) {
            System.err.println("Unable to access input file: " + infile);
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

            FileInputStream fis = new FileInputStream(infile);
            Scanner in = new Scanner(fis);
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
        try (FileInputStream fis = new FileInputStream(filename)) {
            return true; 
        } catch (IOException e) {
            return false; 
        }
    }

    /**
     * Reads PPM turning it into 2d int array
     * @param in Scanner for the input file
     * @return a 2D array of pixel values
     * @return null if validation impossible
     * @throws IllegalArgumentException if the input is invalid
     */
    public static int[][] getPixelValues(Scanner in) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }

        String format = in.next();
        if (!format.equals("P3")) {
            return null; 
        }

        int cols = in.nextInt();
        if (cols <= 0) {
            return null; 
        }

        int rows = in.nextInt();
        if (rows <= 0) {
            return null; 
        }

        int maxColorVal = in.nextInt();
        if (maxColorVal != MAX_COLOR_VALUE) {
            return null; 
        }

        int[][] pixelValues = new int[rows][cols * 3]; 

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols * 3; j++) {
                if (in.hasNextInt()) {
                    int value = in.nextInt();
                    if (value < 0 || value > MAX_COLOR_VALUE) {
                        return null; 
                    }
                    pixelValues[i][j] = value; 
                } else {
                    return null; // Not enough values
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
        if(pixels.length > 0 && pixels[0].length % 3 != 0) {
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
        if(pixels.length > 0 && pixels[0].length % 3 != 0) {
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
        if(pixels.length > 0 && pixels[0].length % 3 != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }

        for(int i = 0; i < pixels.length; ++i) {
            for(int j = 0; j < pixels[i].length; ++j) {
                if (j % 3 != 0) {
                    continue;
                }
                int average = (pixels[i][j] + pixels[i][j+1] + pixels[i][j+2])/3;
                pixels[i][j] = average;
                pixels[i][j+1] = average;
                pixels[i][j+2] = average;
            }
        }
    }

    /**
     * Outputs the pixel values to file
     * @param out The PrintWriter to output
     * @param pixels The 2D array of pixel values
     * @throws IllegalArgumentException if the output writer or pixels array is null, invalid, or jagged
     */
    public static void outputPPM(PrintWriter out, int[][] pixels) {
        if (out == null) {
            throw new IllegalArgumentException("Null file");
        }
        if(pixels == null) {
            throw new IllegalArgumentException("Null array");
        }
        if(pixels.length > 0 && pixels[0].length % 3 != 0) {
            throw new IllegalArgumentException("Invalid array");
        }
        for(int i = 0; i < pixels.length; ++i) {
            if(pixels[0].length != pixels[i].length) {
                throw new IllegalArgumentException("Jagged array");
            }
        }
        out.println("P3");
        out.println(pixels[0].length / 3 + " " + pixels.length); // number of columns and rows
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
