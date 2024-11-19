package Controllers;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

