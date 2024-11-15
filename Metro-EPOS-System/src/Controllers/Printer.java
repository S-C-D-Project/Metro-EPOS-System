package Controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class Printer {

        public static void openImage(File imageFile) {
                try {
                        if (Desktop.isDesktopSupported() && imageFile.exists()) {
                                Desktop.getDesktop().open(imageFile);
                                System.out.println("Opened: " + imageFile.getAbsolutePath());
                        }
                } catch (IOException e) {
                        System.err.println("Failed to open image: " + e.getMessage());
                }
        }

        public static void printImage(BufferedImage image) throws PrinterException {
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
                if (printServices.length > 0) {
                        printerJob.setPrintService(printServices[0]); // Select the first available printer
                }

                printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
                        if (pageIndex == 0) {
                                graphics.drawImage(image, 0, 0, null);
                                return Printable.PAGE_EXISTS;
                        } else {
                                return Printable.NO_SUCH_PAGE;
                        }
                });

                printerJob.print();
        }

        public static void deleteTempFiles(File tempDir) {
                for (File file : tempDir.listFiles()) {
                        if (file.getName().startsWith("page-")) {
                                if (file.delete()) {
                                        System.out.println("Deleted: " + file.getAbsolutePath());
                                } else {
                                        System.out.println("Failed to delete: " + file.getAbsolutePath());
                                }
                        }
                }
        }
}


// Open the PDF in the default viewer
               // viewPDF(outputStream.toByteArray());


       // }

//        private static void viewPDF(byte[] pdfData) throws Exception{
//                // Create a temporary file to hold the PDF data
//                File tempFile = File.createTempFile("bill", ".pdf");
//                tempFile.deleteOnExit(); // Delete the file when the program exits
//
//                // Write the PDF data to the temporary file
//                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//                    fos.write(pdfData);
//                }
//
//                // Open the temporary file in the default PDF viewer
//                if (Desktop.isDesktopSupported()) {
//                    Desktop.getDesktop().open(tempFile);
//                } else {
//                    System.err.println("Desktop environment not supported.");
//                }
//
//        }
 //   }



