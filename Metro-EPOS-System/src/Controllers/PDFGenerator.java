package Controllers;

import Models.DataBaseHandler;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static Views.UIHandler.DisplayChart;

public class PDFGenerator {

    public static File generateBillPDF(Bill bill) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        try {
            // Add bill header
            document.add(new Paragraph("Metro POS Bill").setBold().setFontSize(18));
            document.add(new Paragraph("--------------------------------------------------"));

            // Add bill details
            document.add(new Paragraph("Bill Number: " + bill.getBillid()));

            document.add(new Paragraph("Total Bill: " + bill.getTotalbill() + " PKR"));
            document.add(new Paragraph("Additional Charges: " + bill.getAdditionalCharges() + " PKR"));
            document.add(new Paragraph("Discount: " + bill.getDiscount() + " PKR"));
            document.add(new Paragraph("Sales Tax: " + bill.getSalesTaxAmount() + " PKR"));
            document.add(new Paragraph("Paid Amount: " + bill.getCashAmount() + " PKR"));
            document.add(new Paragraph("Return Amount: " + bill.getReturnAmount() + " PKR"));
            document.add(new Paragraph("--------------------------------------------------"));

            // Add product list table
            float[] columnWidths = {100, 150, 100, 100, 100};
            Table table = new Table(columnWidths);
            table.addCell("Product ID");
            table.addCell("Product Name");
            table.addCell("Quantity");
            table.addCell("Unit Price");
            table.addCell("Total Price");

            // Add products to table
            ArrayList<Product> productList = bill.getProductList();
            for (Product product : productList) {
                table.addCell(String.valueOf(product.getProductId()));
                table.addCell(product.getProductName());
                table.addCell(String.valueOf(product.getStockQuantity()));
                table.addCell(String.valueOf(product.getSalePrice()));
                table.addCell(String.valueOf(product.getSalePrice() * product.getStockQuantity()));
            }

            document.add(table);

            // Add footer
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph("Thank you for shopping with Metro!").setItalic());
        } finally {
            document.close();
        }


        File[] imageFiles = PDFGenerator.convertPDFToImages(outputStream);

        if (imageFiles.length > 0) {
            Printer.openImage(imageFiles[0]);

            BufferedImage image = ImageIO.read(imageFiles[0]);
            Printer.printImage(image);
        }

        return new File(System.getProperty("java.io.tmpdir"));

    }


    public static void generateChartPDF(ChartPanel[] chartPanels, ArrayList<String> productStockList,
                                        ArrayList<Integer> monthlyProfits, ArrayList<String> billCalculations,
                                        String pdfFilePath, int annualProfit, int budgetRemaining) {
        try (PDDocument document = new PDDocument()) {
            // Add a styled cover page
            addCoverPage(document, "Enhanced Business Report");

            // Add charts to individual pages
            addChartPages(document, chartPanels);

            // Add product stock and monthly profit data as tables
            addDataTablesPage(document, "Product Stock Status", productStockList, "Monthly Profit Data", monthlyProfits);

            // Add Bill calculations as a table
            addDataTablePage(document, "Bill Calculations", billCalculations);

            // Add the last page with financial summary (Annual Profit, Budget Remaining) and remarks
            addFinancialSummaryAndRemarksPage(document, annualProfit, budgetRemaining);

            // Save the PDF to the specified file
            document.save(pdfFilePath);
            System.out.println("PDF generated successfully at: " + pdfFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while generating PDF.");
        }
    }

    private static void addCoverPage(PDDocument document, String title) throws IOException {
        PDPage coverPage = new PDPage(PDRectangle.A4);
        document.addPage(coverPage);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, coverPage)) {
            contentStream.setNonStrokingColor(230, 240, 255); // Light blue background
            contentStream.addRect(0, 0, coverPage.getMediaBox().getWidth(), coverPage.getMediaBox().getHeight());
            contentStream.fill();

            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(title);
            contentStream.newLineAtOffset(0, -40);
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            contentStream.showText("Generated on: " + java.time.LocalDate.now());
            contentStream.endText();
        }
    }

    private static void addChartPages(PDDocument document, ChartPanel[] chartPanels) throws IOException {
        for (ChartPanel chartPanel : chartPanels) {
            if (chartPanel != null) {
                PDPage chartPage = new PDPage(PDRectangle.A4);
                document.addPage(chartPage);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, chartPage)) {
                    contentStream.setNonStrokingColor(245, 245, 245); // Light grey background
                    contentStream.addRect(0, 0, chartPage.getMediaBox().getWidth(), chartPage.getMediaBox().getHeight());
                    contentStream.fill();

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 780);
                    contentStream.showText("Performance Chart");
                    contentStream.endText();

                    // Add the chart as an image
                    BufferedImage chartImage = chartPanel.getChart().createBufferedImage(500, 350);
                    File tempImageFile = File.createTempFile("chart", ".png");
                    ImageIO.write(chartImage, "png", tempImageFile);

                    PDImageXObject pdImage = PDImageXObject.createFromFile(tempImageFile.getAbsolutePath(), document);
                    contentStream.drawImage(pdImage, 50, 400, 500, 350);

                    tempImageFile.delete();
                }
            }
        }
    }

    private static void addDataTablesPage(PDDocument document, String title1, ArrayList<String> tableData1,
                                          String title2, ArrayList<Integer> tableData2) throws IOException {
        PDPage dataPage = new PDPage(PDRectangle.A4);
        document.addPage(dataPage);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, dataPage)) {
            contentStream.setNonStrokingColor(240, 255, 240); // Light green background
            contentStream.addRect(0, 0, dataPage.getMediaBox().getWidth(), dataPage.getMediaBox().getHeight());
            contentStream.fill();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText(title1);
            contentStream.newLineAtOffset(0, -30);
            contentStream.endText();

            // Draw the first table (Product Stock Status)
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float yPosition = 720;
            float marginLeft = 50;
            float columnWidth = 180;
            float rowHeight = 20;

            // Draw the table header
            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.beginText();
            contentStream.newLineAtOffset(marginLeft, yPosition);
            contentStream.showText("Product");
            contentStream.newLineAtOffset(columnWidth, 0);
            contentStream.showText("Stock");
            contentStream.endText();

            // Draw the table content
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            yPosition -= rowHeight;
            for (String row : tableData1) {
                contentStream.beginText();
                contentStream.newLineAtOffset(marginLeft, yPosition);
                String[] columns = row.split(",");
                if (columns.length >= 2) {
                    contentStream.showText(columns[0]); // Product name
                    contentStream.newLineAtOffset(columnWidth, 0);
                    contentStream.showText(columns[1]); // Stock quantity
                }
                contentStream.endText();
                yPosition -= rowHeight;
            }

            // Draw the second table (Monthly Profits)
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition - 30);
            contentStream.showText(title2);
            contentStream.endText();

            yPosition -= 50; // Adjust for the second table's start
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(marginLeft, yPosition);
            contentStream.showText("Month");
            contentStream.newLineAtOffset(columnWidth, 0);
            contentStream.showText("Profit");
            contentStream.endText();

            yPosition -= rowHeight;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (int i = 0; i < tableData2.size(); i++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(marginLeft, yPosition);
                contentStream.showText("Month " + (i + 1));
                contentStream.newLineAtOffset(columnWidth, 0);
                contentStream.showText(String.valueOf(tableData2.get(i))); // Profit
                contentStream.endText();
                yPosition -= rowHeight;
            }
        }
    }

    private static void addDataTablePage(PDDocument document, String title, ArrayList<String> tableData) throws IOException {
        PDPage dataPage = new PDPage(PDRectangle.A4);
        document.addPage(dataPage);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, dataPage)) {
            contentStream.setNonStrokingColor(255, 245, 245); // Light pink background
            contentStream.addRect(0, 0, dataPage.getMediaBox().getWidth(), dataPage.getMediaBox().getHeight());
            contentStream.fill();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText(title);
            contentStream.endText();

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            float yPosition;
                yPosition =720;
            System.out.println(title);
            float marginLeft = 50;
            float columnWidth = 180;
            float rowHeight = 20;

            // Draw the table header
            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.beginText();
            contentStream.newLineAtOffset(marginLeft, yPosition);
            contentStream.showText("Bill No");
            contentStream.newLineAtOffset(columnWidth, 0);
            contentStream.showText("Amount");
            contentStream.endText();

            // Draw the table content
            yPosition -= rowHeight;
            for (String row : tableData) {
                contentStream.beginText();
                contentStream.newLineAtOffset(marginLeft, yPosition);
                String[] columns = row.split(",");
                if (columns.length >= 2) {
                    contentStream.showText(columns[0]); // Bill number
                    contentStream.newLineAtOffset(columnWidth, 0);
                    contentStream.showText(columns[1]); // Amount
                }
                contentStream.endText();
                yPosition -= rowHeight;
            }
        }
    }

    private static void addFinancialSummaryAndRemarksPage(PDDocument document, int annualProfit, int budgetRemaining) throws IOException {
        PDPage summaryPage = new PDPage(PDRectangle.A4);
        document.addPage(summaryPage);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, summaryPage)) {
            contentStream.setNonStrokingColor(240, 255, 240); // Light green background
            contentStream.addRect(0, 0, summaryPage.getMediaBox().getWidth(), summaryPage.getMediaBox().getHeight());
            contentStream.fill();

            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Financial Summary & Remarks");
            contentStream.endText();

            // Financial data
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 650);
            contentStream.showText("Annual Profit: " + annualProfit);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Budget Remaining: " + budgetRemaining);
            contentStream.endText();

            // Remarks
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 550);
            contentStream.showText("Conclusion: The business has been operating successfully with steady profits and controlled costs.");
            contentStream.endText();
        }
    }

    public static File[] convertPDFToImages(ByteArrayOutputStream outputStream) throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        PDDocument document = PDDocument.load(new ByteArrayInputStream(outputStream.toByteArray()));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        File[] imageFiles = new File[document.getNumberOfPages()];

        for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
            File tempFile = new File(tempDir, "page-" + (pageIndex + 1) + ".png");
            ImageIO.write(image, "PNG", tempFile);
            imageFiles[pageIndex] = tempFile;
        }
        document.close();
        return imageFiles;
    }
}

