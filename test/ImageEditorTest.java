import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test ImageEditor methods
 *
 * @author Suzanne Balik
 * @author Amelia Saldino
 */
public class ImageEditorTest {

    /** One red pixel inverted */
    public static final int[][] ONE_RED_PIXEL_INVERTED = {{0, 255, 255}};

    /** One green pixel inverted */
    public static final int[][] ONE_GREEN_PIXEL_INVERTED = {{255, 0, 255}};

    /** One blue pixel inverted */
    public static final int[][] ONE_BLUE_PIXEL_INVERTED = {{255, 255, 0}};

    /** One black pixel inverted */
    public static final int[][] ONE_BLACK_PIXEL_INVERTED = {{255, 255, 255}};
    
    /** One red pixel high contrast */
    public static final int[][] ONE_RED_PIXEL_HIGH_CONSTRAST = {{255, 0, 0}};

    /** One green pixel high contrast */
    public static final int[][] ONE_GREEN_PIXEL_HIGH_CONSTRAST = {{0, 255, 0}};

    /** One blue pixel high contrast */
    public static final int[][] ONE_BLUE_PIXEL_HIGH_CONSTRAST = {{0, 0, 255}};

    /** One yellow pixel high contrast */
    public static final int[][] ONE_YELLOW_PIXEL_HIGH_CONSTRAST = {{255, 255, 0}};
    
    /** One red pixel grey scale */
    public static final int[][] ONE_RED_PIXEL_GREY_SCALE = {{85, 85, 85}};
    
    /** One green pixel grey scale */
    public static final int[][] ONE_GREEN_PIXEL_GREY_SCALE = {{85, 85, 85}};

    /** One blue pixel grey scale */
    public static final int[][] ONE_BLUE_PIXEL_GREY_SCALE = {{85, 85, 85}};

    /** One yellow pixel grey scale */
    public static final int[][] ONE_YELLOW_PIXEL_GREY_SCALE = {{170, 170, 170}};
 
    /**
     * Test inverting one red pixel
     */
    @Test
    public void testInvert1() {
        String description = "Invert 1: one red pixel";
        int[][] oneRedPixel = {{255, 0, 0}};
        ImageEditor.invert(oneRedPixel);
        assertArrayEquals(ONE_RED_PIXEL_INVERTED, oneRedPixel, description);
    }

    /**
     * Test inverting one green pixel
     */
    @Test
    public void testInvert2() {
        String description = "Invert 2: one green pixel";
        int[][] oneGreenPixel = {{0, 255, 0}};
        ImageEditor.invert(oneGreenPixel);
        assertArrayEquals(ONE_GREEN_PIXEL_INVERTED, oneGreenPixel, description);
    }

    /**
     * Test inverting one blue pixel
     */
    @Test
    public void testInvert3() {
        String description = "Invert 3: one blue pixel";
        int[][] oneBluePixel = {{0, 0, 255}};
        ImageEditor.invert(oneBluePixel);
        assertArrayEquals(ONE_BLUE_PIXEL_INVERTED, oneBluePixel, description);
    }

    /**
     * Test inverting one black pixel
     */
    @Test
    public void testInvert4() {
        String description = "Invert 4: one black pixel";
        int[][] oneBlackPixel = {{0, 0, 0}};
        ImageEditor.invert(oneBlackPixel);
        assertArrayEquals(ONE_BLACK_PIXEL_INVERTED, oneBlackPixel, description);
    }

    /**
     * Test converting one red pixel to high contrast
     */
    @Test
    public void testHighContrast1() {
        String description = "High Constrast 1: one red pixel";
        int[][] oneRedPixel = {{255, 0, 0}};
        ImageEditor.highContrast(oneRedPixel);
        assertArrayEquals(ONE_RED_PIXEL_HIGH_CONSTRAST, oneRedPixel, description);
    }

    /**
     * Test converting one green pixel to high contrast
     */
    @Test
    public void testHighContrast2() {
        String description = "High Constrast 2: one green pixel";
        int[][] oneGreenPixel = {{0, 200, 0}};
        ImageEditor.highContrast(oneGreenPixel);
        assertArrayEquals(ONE_GREEN_PIXEL_HIGH_CONSTRAST, oneGreenPixel, description);
    }

    /**
     * Test converting one blue pixel to high contrast
     */
    @Test
    public void testHighContrast3() {
        String description = "High Constrast 3: one blue pixel";
        int[][] oneBluePixel = {{0, 0, 200}};
        ImageEditor.highContrast(oneBluePixel);
        assertArrayEquals(ONE_BLUE_PIXEL_HIGH_CONSTRAST, oneBluePixel, description);
    }

    /**
     * Test converting one yellow pixel to high contrast
     */
    @Test
    public void testHighContrast4() {
        String description = "High Constrast 4: one yellow pixel";
        int[][] oneYellowPixel = {{200, 200, 0}};
        ImageEditor.highContrast(oneYellowPixel);
        assertArrayEquals(ONE_YELLOW_PIXEL_HIGH_CONSTRAST, oneYellowPixel, description);
    }

    /**
     * Test converting one red pixel to grey scale
     */
    @Test
    public void testGreyScale1() {
        String description = "Grey Scale 1: one red pixel";
        int[][] oneRedPixel = {{255, 0, 0}};
        ImageEditor.greyScale(oneRedPixel);
        assertArrayEquals(ONE_RED_PIXEL_GREY_SCALE, oneRedPixel, description);
    }

    /**
     * Test converting one green pixel to grey scale
     */
    @Test
    public void testGreyScale2() {
        String description = "Grey Scale 2: one green pixel";
        int[][] oneGreenPixel = {{0, 255, 0}};
        ImageEditor.greyScale(oneGreenPixel);
        assertArrayEquals(ONE_GREEN_PIXEL_GREY_SCALE, oneGreenPixel, description);
    }

    /**
     * Test converting one blue pixel to grey scale
     */
    @Test
    public void testGreyScale3() {
        String description = "Grey Scale 3: one blue pixel";
        int[][] oneBluePixel = {{0, 0, 255}};
        ImageEditor.greyScale(oneBluePixel);
        assertArrayEquals(ONE_BLUE_PIXEL_GREY_SCALE, oneBluePixel, description);
    }

    /**
     * Test converting one yellow pixel to grey scale
     */
    @Test
    public void testGreyScale4() {
        String description = "Grey Scale 4: one yellow pixel";
        int[][] oneYellowPixel = {{255, 255, 0}};
        ImageEditor.greyScale(oneYellowPixel);
        assertArrayEquals(ONE_YELLOW_PIXEL_GREY_SCALE, oneYellowPixel, description);
    }
    
    /**
     * Test the ImageEditor methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.invert(null), "ImageEditor.invert(null)");
        assertEquals("Null array", exception.getMessage(),
                     "Testing ImageEditor.invert(null) - exception message");

        int[][] invalid1 = {{1, 2, 3, 4}, {5, 6, 7}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.invert(invalid1), "ImageEditor.invert(invalid1)");
        assertEquals("Invalid array", exception.getMessage(),
                     "Testing ImageEditor.invert(invalid1) - exception message"); 
        
        int[][] jagged1 = {{1, 2, 3}, {4, 5}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.invert(jagged1), "ImageEditor.invert(jagged1)");
        assertEquals("Jagged array", exception.getMessage(),
                     "Testing ImageEditor.invert(jagged1) - exception message");
        
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.highContrast(null), "ImageEditor.highContrast(null)");
        assertEquals("Null array", exception.getMessage(),
                     "Testing ImageEditor.highContrast(null) - exception message");

        int[][] invalid2 = {{1, 2, 3, 4}, {5, 6, 7}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.highContrast(invalid2), "ImageEditor.highContrast(invalid2)");
        assertEquals("Invalid array", exception.getMessage(),
                     "Testing ImageEditor.highConstrast(invalid2) - exception message");         
        
        int[][] jagged2 = {{1, 2, 3}, {4, 5}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.highContrast(jagged2), "ImageEditor.highContrast(jagged2)");
        assertEquals("Jagged array", exception.getMessage(),
                     "Testing ImageEditor.highContrast(jagged2) - " +
                     "exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.greyScale(null), "ImageEditor.greyScale(null)");
        assertEquals("Null array", exception.getMessage(),
                     "Testing ImageEditor.greyScale(null) - exception message"); 

        int[][] invalid3 = {{1, 2, 3, 4}, {5, 6, 7}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.greyScale(invalid3), "ImageEditor.greyScale(invalid3)");
        assertEquals("Invalid array", exception.getMessage(),
                     "Testing ImageEditor.greyScale(invalid3) - exception message"); 
                     
        int[][] jagged3 = {{1, 2, 3}, {4, 5, 6, 7, 8, 9}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.greyScale(jagged3), "ImageEditor.greyScale(jagged3)");
        assertEquals("Jagged array", exception.getMessage(),
                     "Testing ImageEditor.greyScale(jagged3) - exception message");
    }
    
    /**
     * Tests getPixelValues 
     */
    @Test
    public void testGetPixelValues() {
        
        // You do NOT need to add additional getPixelValues tests. Just make sure these pass!
        
        int[][] validPixels = {{1,2,3}, {4,5,6}};
        assertArrayEquals(validPixels, 
                          ImageEditor.getPixelValues(new Scanner("P3 1 2 255 1 2 3 4 5 6")), 
                          "Tests correct PPM file");
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.getPixelValues(null), "ImageEditor.getPixelValues(null)");
            
        assertEquals("Null file", exception.getMessage(),
                     "Testing ImageEditor.getPixelValues(null) " +
                     "- exception message");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P2 1 2 255 1 2 3 4 5 6")), 
                   "Tests ppm file with invalid type");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 abc 2 255 1 2 3 4 5 6")), 
                   "Tests ppm file with non-integer cols");
                   
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 -5 2 255 1 2 3 4 5 6")), 
                   "Tests ppm file with non-positive cols");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 abc 255 1 2 3 4 5 6")), 
                   "Tests ppm file with non-integer rows");
                   
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 -3 255 1 2 3 4 5 6")), 
                   "Tests ppm file with non-positive rows");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 2 180 1 2 3 4 5 6")), 
                   "Tests ppm file with invalid max value");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 2 255 1 2 3 4")), 
                   "Tests ppm file with too few values");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 2 255 1 2 3 x 5 6")), 
                   "Tests ppm file with invalid RGB noninteger value");
        
        assertNull(ImageEditor.getPixelValues(new Scanner("P3 1 2 255 1 2 3 -4 5 6")), 
                   "Tests ppm file with invalid RGB integer value");
        
    }

    /**
     * Tests outputPPM
     */
    @Test
    public void testOutputPPM() {
        
        // You do NOT need to add additional outputPPM tests. Just make sure these pass!

        int[][] validPixels = {{1,2,3}, {4,5,6}};        
        
        try {

            PrintWriter out = new PrintWriter(
                              new FileOutputStream("test-files/obscureFilename.ppm"));
            ImageEditor.outputPPM(out, validPixels);
            out.close();
            Scanner in = new Scanner(new FileInputStream("test-files/obscureFilename.ppm"));
            assertEquals("P3", in.nextLine());
            assertEquals("1 2", in.nextLine());
            assertEquals("255", in.nextLine());
            assertEquals("1 2 3", in.nextLine());
            assertEquals("4 5 6", in.nextLine());
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not create output file or could not open input file");
        }
          
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> ImageEditor.outputPPM(null, validPixels ), 
                  "ImageEditor.outputPPM(null, validPixels )");
            
        assertEquals("Null file", exception.getMessage(),
                     "Testing ImageEditor.outputPPM(null, validPixels ) " +
                     "- exception message");
          
        try {
            final PrintWriter out1 = new PrintWriter(
                                     new FileOutputStream("test-files/obscureFilename.ppm"));
            exception = assertThrows(IllegalArgumentException.class,
                () -> ImageEditor.outputPPM(out1, null), "ImageEditor.outputPPM(out1, null)");
            assertEquals("Null array", exception.getMessage(),
                         "Testing ImageEditor.outputPPM(out1, null) - exception message");
            out1.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not create test-files/obscureFilename.ppm");
        }
        

        
        try {
            int[][] invalid = {{1, 2, 3, 4}, {5, 6, 7}};
            final PrintWriter out1 = new PrintWriter(
                                     new FileOutputStream("test-files/obscureFilename.ppm"));
            exception = assertThrows(IllegalArgumentException.class,
                () -> ImageEditor.outputPPM(out1, invalid), "ImageEditor.outputPPM(out1, invalid)");
            assertEquals("Invalid array", exception.getMessage(),
                         "Testing ImageEditor.outputPPM(out1, invalid) - " +
                         "exception message"); 
            out1.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not create test-files/obscureFilename.ppm");
        }
        
        try {
            int[][] jagged = {{1, 2, 3}, {4, 5, 6, 7, 8, 9}};
            final PrintWriter out1 = new PrintWriter(
                                     new FileOutputStream("test-files/obscureFilename.ppm"));
            exception = assertThrows(IllegalArgumentException.class,
                () -> ImageEditor.outputPPM(out1, jagged), "ImageEditor.outputPPM(out1, jagged)");
            assertEquals("Jagged array", exception.getMessage(),
                         "Testing ImageEditor.outputPPM(out1, jagged) - exception message");
            out1.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not create test-files/obscureFilename.ppm");
        }
        try {
            Path path = Path.of("test-files/obscureFilename.ppm");
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
        catch (IOException e) {
            System.out.println("Could not delete test-files/obscureFilename.ppm");
        }
        
    }
}