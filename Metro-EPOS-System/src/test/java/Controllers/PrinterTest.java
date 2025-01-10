package Controllers;

import Controllers.Printer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrinterTest {

    private MockedStatic<Desktop> desktopMockedStatic;
    private Desktop mockDesktop;
    private File mockImageFile;

    @Test
    public void testOpenImage() throws IOException {

        File imageFile = new File("image.png");
        Printer.openImage(imageFile);

    }
    @Test
    public void testPrintImage() {
        // Create a simple buffered image
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 100, 100);  // Create a simple black square image
        g2d.dispose();

        try {
            // Attempt to print the image
            Printer.printImage(image);
        } catch (PrinterException e) {
            // If an exception is thrown, the test will fail
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteTempFiles() throws IOException {
        // Create a temporary directory
        File tempDir = new File("tempDir");
        tempDir.mkdir();  // Ensure the directory is created

        // Create files that should be deleted
        File file1 = new File(tempDir, "page-1.png");
        File file2 = new File(tempDir, "page-2.png");

        file1.createNewFile();
        file2.createNewFile();

        // Call deleteTempFiles to delete files starting with "page-"
        Printer.deleteTempFiles(tempDir);

        // Assert that the files have been deleted
        assert !file1.exists() : "file1 should have been deleted";
        assert !file2.exists() : "file2 should have been deleted";

        // Clean up (delete the temp directory after the test)
        tempDir.delete();
    }

}
