package Controllers;


import com.itextpdf.io.source.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.jfree.chart.ChartPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PDFGeneratorTest {

    private Bill mockBill;
    private Product mockProduct;

    @BeforeEach
    public void setup() {
        // Create mock bill
        mockBill = Mockito.mock(Bill.class);
        Mockito.when(mockBill.getBillid()).thenReturn(Integer.valueOf("12345"));
        Mockito.when(mockBill.getTotalbill()).thenReturn((int) 1000.0);
        Mockito.when(mockBill.getAdditionalCharges()).thenReturn((int) 50.0);
        Mockito.when(mockBill.getDiscount()).thenReturn(100.0);
        Mockito.when(mockBill.getSalesTaxAmount()).thenReturn((int) 150.0);
        Mockito.when(mockBill.getCashAmount()).thenReturn((int) 1200.0);
        Mockito.when(mockBill.getReturnAmount()).thenReturn((int) 50.0);

        // Create mock products
        mockProduct = Mockito.mock(Product.class);
        Mockito.when(mockProduct.getProductId()).thenReturn(1);
        Mockito.when(mockProduct.getProductName()).thenReturn("Product A");
        Mockito.when(mockProduct.getStockQuantity()).thenReturn(10);
        Mockito.when(mockProduct.getSalePrice()).thenReturn((int) 100.0);
    }

    @Test
    public void testGenerateBillPDF() throws Exception {
        // Simulate a bill with product list
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(mockProduct);
        Mockito.when(mockBill.getProductList()).thenReturn(productList);

        // Generate the PDF
        File generatedFile = PDFGenerator.generateBillPDF(mockBill);

        // Check that the file is created
        assertNotNull(generatedFile);
        assertTrue(generatedFile.exists());
    }

    @Test
    public void testGenerateChartPDF() throws IOException {
        // Create mock chart panels
        ChartPanel[] mockChartPanels = new ChartPanel[1];

        // Create a valid JFreeChart object
        JFreeChart mockChart = ChartFactory.createLineChart("Test Chart", "X", "Y", null);

        // Mock ChartPanel to return the valid chart
        mockChartPanels[0] = Mockito.mock(ChartPanel.class);
        Mockito.when(mockChartPanels[0].getChart()).thenReturn(mockChart);

        // Create data lists for product stock, monthly profits, and bill calculations
        ArrayList<String> productStockList = new ArrayList<>();
        productStockList.add("Product A,100");

        ArrayList<Integer> monthlyProfits = new ArrayList<>();
        monthlyProfits.add(5000);

        ArrayList<String> billCalculations = new ArrayList<>();
        billCalculations.add("12345,1000");

        // File path to save the PDF
        String pdfFilePath = System.getProperty("java.io.tmpdir") + "/chart_report.pdf";

        // Call generateChartPDF method
        PDFGenerator.generateChartPDF(mockChartPanels, productStockList, monthlyProfits, billCalculations, pdfFilePath, 10000, 2000);

        // Check if the file is created
        File pdfFile = new File(pdfFilePath);
        assertTrue(pdfFile.exists());
        assertTrue(pdfFile.getName().endsWith(".pdf"));
    }
    @Test
    public void testAddCoverPage() throws IOException {
        // Create a temporary PDDocument
        PDDocument document = new PDDocument();

        // Add cover page
        PDPage coverPage = new PDPage();
        document.addPage(coverPage);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, coverPage)) {
            contentStream.setNonStrokingColor(230, 240, 255); // Light blue background
            contentStream.addRect(0, 0, coverPage.getMediaBox().getWidth(), coverPage.getMediaBox().getHeight());
            contentStream.fill();

            contentStream.setNonStrokingColor(0, 0, 0); // Black text
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Enhanced Business Report");
            contentStream.endText();
        }

        document.save(System.getProperty("java.io.tmpdir") + "/cover_page_test.pdf");
        document.close();

        // Verify the file was saved
        File generatedFile = new File(System.getProperty("java.io.tmpdir") + "/cover_page_test.pdf");
        assertTrue(generatedFile.exists());
    }

    @Test
    public void testAddChartPages() throws IOException {
        // Create a mock JFreeChart object
        JFreeChart mockChart = Mockito.mock(JFreeChart.class);

        // Create mock chart panel and mock the getChart() method to return the mock JFreeChart
        ChartPanel[] mockChartPanels = new ChartPanel[1];
        mockChartPanels[0] = Mockito.mock(ChartPanel.class);
        Mockito.when(mockChartPanels[0].getChart()).thenReturn(mockChart);

        // Mock the createBufferedImage method of JFreeChart to return a non-null image
        BufferedImage mockImage = new BufferedImage(500, 350, BufferedImage.TYPE_INT_ARGB);
        Mockito.when(mockChart.createBufferedImage(500, 350)).thenReturn(mockImage);

        // Create a temporary PDDocument to hold the chart
        PDDocument document = new PDDocument();

        // Add chart pages
        PDFGenerator.addChartPages(document, mockChartPanels);

        // Check that a page is added to the document
        assertEquals(1, document.getNumberOfPages());

        // Save the generated PDF
        document.save(System.getProperty("java.io.tmpdir") + "/chart_pages_test.pdf");
        document.close();

        File generatedFile = new File(System.getProperty("java.io.tmpdir") + "/chart_pages_test.pdf");
        assertTrue(generatedFile.exists());
    }


    @Test
    public void testAddDataTablesPage() throws IOException {
        // Prepare test data for product stock and monthly profits
        ArrayList<String> productStockList = new ArrayList<>();
        productStockList.add("Product A,100");

        ArrayList<Integer> monthlyProfits = new ArrayList<>();
        monthlyProfits.add(5000);

        // Create a temporary PDDocument
        PDDocument document = new PDDocument();

        // Add data table page
        PDFGenerator.addDataTablesPage(document, "Product Stock Status", productStockList, "Monthly Profit Data", monthlyProfits);

        // Check that a page is added
        assertEquals(1, document.getNumberOfPages());

        document.save(System.getProperty("java.io.tmpdir") + "/data_tables_test.pdf");
        document.close();

        File generatedFile = new File(System.getProperty("java.io.tmpdir") + "/data_tables_test.pdf");
        assertTrue(generatedFile.exists());
    }


}
