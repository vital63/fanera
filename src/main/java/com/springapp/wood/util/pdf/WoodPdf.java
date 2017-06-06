package com.springapp.wood.util.pdf ;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springapp.wood.domain.LaminatedParticleBoard;
import com.springapp.wood.domain.RawPlywood;
import com.springapp.wood.domain.Wood;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.context.MessageSource;

public class WoodPdf 
{
    private Locale locale;
    private MessageSource messageSource;
    private BaseFont baseFont;

    public WoodPdf(Locale locale, MessageSource messageSource, String fontPath) {
        this.locale = locale;
        this.messageSource = messageSource;
        try{
            //include font file with supporting cyrillic
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch(IOException | DocumentException e){
            throw new Error("Can not create baseFont for path " + fontPath);
        }
    }
    
    private PdfPTable getHeaderTable(String path) throws DocumentException, IOException 
    {
        PdfPTable headerTable = new PdfPTable(3);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{2f,0.5f, 1.5f});
        
//cell1
        Image imgLogo = Image.getInstance(path + "/assets/images/sen_zan_traid.jpg");
        PdfPCell cell1 = new PdfPCell(imgLogo,true);
        cell1.setBorder(Rectangle.NO_BORDER);
//cell2
        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(Rectangle.NO_BORDER);

        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14);
        Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);
        greenFont.setColor(new BaseColor(89, 178, 16));

//cell3
        PdfPCell cell3 = new PdfPCell();
        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk("Wood\n",font);
        paragraph.add(chunk);
        chunk = new Chunk("www.vmccnc.com/wood",greenFont);
        paragraph.add(chunk);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        cell3.addElement(paragraph);
        cell3.setBorder(Rectangle.NO_BORDER);

        headerTable.addCell(cell3);
        headerTable.addCell(cell2);
        headerTable.addCell(cell1);

        return headerTable;
    }

    private void setForWhoData(Document document, String model, String type) throws DocumentException 
    {
        Font fontBoldBig = new Font(Font.FontFamily.TIMES_ROMAN,22,Font.BOLD);
        Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN,13);

        Paragraph paragraph = new Paragraph("\n" + model + " (Type: " + type +")", fontBoldBig);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        paragraph = new Paragraph();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Chunk chunk = new Chunk("\n" + dateFormat.format(new Date()), fontNormal);
        paragraph.add(chunk);

        document.add(paragraph);
    }

    private PdfPTable getItemTable(String path, Wood wood, boolean showPrice) throws DocumentException, IOException 
    {
        PdfPTable itemTable = new PdfPTable(2);
        
        itemTable.setWidthPercentage(100);
        itemTable.setSpacingBefore(20f);
        itemTable.setWidths(new float[]{1f, 1f});

        Font font = new Font(baseFont, 12);

        PdfPCell[] itemCells = new PdfPCell[2];

        String[] images = {wood.getPhoto1(), wood.getPhoto2()};
        insertTwoImages(path, itemTable, itemCells, images);

        insertRow(itemCells, font, itemTable, getFromLocale("woodall.type"), wood.getType());
        insertRow(itemCells, font, itemTable, getFromLocale("woodall.length"), wood.getLength());
        insertRow(itemCells, font, itemTable, getFromLocale("woodall.width"), wood.getWidth());
        insertRow(itemCells, font, itemTable, getFromLocale("woodall.thickness"), wood.getThickness());

        if(wood.isRawPlywood())
        {
            RawPlywood rawPlywood = (RawPlywood)wood; 
            insertRow(itemCells, font, itemTable, getFromLocale("woodone.grade"), rawPlywood.getGrade());        
            insertRow(itemCells, font, itemTable, getFromLocale("woodone.sanded"), rawPlywood.getIsSandedAsString());        
            insertRow(itemCells, font, itemTable, getFromLocale("woodone.water_resistance"), rawPlywood.getIsWaterResistanceAsString());        
        }
        
        if(wood.isLaminatedParticleBoard())
            insertRow(itemCells, font, itemTable, getFromLocale("woodone.laminated_color"), ((LaminatedParticleBoard)wood).getLaminatedColor());

        insertRow(itemCells, font, itemTable, getFromLocale("woodone.num_boards_in_package"), wood.getNumBoardsInPackage());
        insertRow(itemCells, font, itemTable, getFromLocale("woodone.num_packages_in_vehicle"), wood.getNumPackagesInVehicle());
        
        if(showPrice)
            insertRow(itemCells, font, itemTable, "Price", wood.getPrice());
        
        return itemTable;
    }

    private String getFromLocale(String s){
        return messageSource.getMessage(s, null, locale);
    }
    
    private void insertTwoImages(String path, PdfPTable itemTable, PdfPCell[] itemCells, String[] imageNames){
        for(int i=0; i<2; i++)
        {
            try {
                Image img = Image.getInstance(path + "/assets/images/products/" + imageNames[i]);
                img.setScaleToFitHeight(false);
                itemCells[i] = new PdfPCell(img, true);
            } catch (Exception e) {
                itemCells[i] = new PdfPCell(new Paragraph(""));
            }

            itemCells[i].setPadding(1);
            itemCells[i].setBorderColor(new BaseColor(22, 22, 22));
            itemTable.addCell(itemCells[i]);
        }
    }
    
    private void insertRow(PdfPCell[] itemCells, Font font, PdfPTable itemTable, String name, Integer value) {
        String valS = Integer.valueOf(value).toString();
        insertRow(itemCells, font, itemTable, name, valS);
    }
    
    private void insertRow(PdfPCell[] itemCells, Font font, PdfPTable itemTable, String name, Double value) {
        String valS = Double.valueOf(value).toString();
        insertRow(itemCells, font, itemTable, name, valS);
    }
    
    private void insertRow(PdfPCell[] itemCells, Font font, PdfPTable itemTable, String name, String value) {
        itemCells[0] = new PdfPCell(new Paragraph(name, font));
        itemCells[1] = new PdfPCell(new Paragraph(value, font));
        for (int i = 0; i < 2; i++)
        {
            itemCells[i].setBackgroundColor(new BaseColor(238, 238, 238));
            itemCells[i].setBorderColor(new BaseColor(22, 22, 22));
            itemTable.addCell(itemCells[i]);
        }
    }

    private Paragraph getFooterParagraph() {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14);
        Font fontSmall = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Font fontSmallGreen = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        fontSmallGreen.setColor(new BaseColor(89, 178, 16));

        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk("\nIndustrial Lighting: ",fontSmall);
        paragraph.add(chunk);
        chunk = new Chunk("\nPhone: +375 (29) 255-88-88",fontSmall);
        paragraph.add(chunk);
        
        chunk = new Chunk("\nwww.vmccnc.com/wood",fontSmallGreen);
        paragraph.add(chunk);
        
        paragraph.setAlignment(Element.ALIGN_LEFT);
        return paragraph;
    }

    public String createPdfWood(String path, Wood wood, String company, String director, boolean showPrice) throws Exception 
    {
        Document document = new Document(PageSize.A4,50,50,50,50);
        String pathPdf = path + "/offer-single.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(pathPdf));
        document.open();

        document.add(getHeaderTable(path));
        setForWhoData(document, wood.getName(), wood.getType());
        document.add(getItemTable(path, wood, showPrice));
        document.add(getFooterParagraph());

        document.close();
        return pathPdf;
    }

}