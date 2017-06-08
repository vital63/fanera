package com.springapp.wood.util.excel;

import com.springapp.wood.domain.LaminatedParticleBoard;
import com.springapp.wood.domain.RawPlywood;
import com.springapp.wood.domain.Wood;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Iterator;

public class ParserExcelWood  {
    
    private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) 
            workbook = new XSSFWorkbook(inputStream);
        else if (excelFilePath.endsWith("xls")) 
            workbook = new HSSFWorkbook(inputStream);
        
        return workbook;
    }
    
    private static final String RAW_PLYWOOD = "raw plywood";
    private static final String LAMINATED_PARTICLE_BOARD = "laminated particle board";
    
    public static Wood readWood(File file) throws IOException{
        
        try(FileInputStream fis = new FileInputStream(file))
        {
            Workbook workbook = getWorkbook(fis,file.getPath());
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            DataFormatter df = new DataFormatter();

            String id = getId(df.formatCellValue(rowIterator.next().getCell(1)).trim());

            Wood wood;
            String type = df.formatCellValue(rowIterator.next().getCell(1)).trim().toLowerCase();
            switch (type) {
                case RAW_PLYWOOD:
                    wood = new RawPlywood();
                    wood.setId(id);
                    readSize(wood, rowIterator, df);
                    readRawPlywoodParameters((RawPlywood) wood, rowIterator, df);
                    break;
                case LAMINATED_PARTICLE_BOARD:
                    wood = new LaminatedParticleBoard();
                    wood.setId(id);
                    readSize(wood, rowIterator, df);
                    readLaminatedParticleBoardParameters((LaminatedParticleBoard) wood, rowIterator, df);
                    break;
                default:
                    throw new IOException("Invalid wood type");
            }

            wood.setUrl(getUrl(wood.getId())); 
            
            wood.setPrice(doubleFromCell(rowIterator, df));

            wood.setPhoto1(df.formatCellValue(rowIterator.next().getCell(1)).trim());
            wood.setPhoto2(df.formatCellValue(rowIterator.next().getCell(1)).trim());
            wood.setPhoto3(df.formatCellValue(rowIterator.next().getCell(1)).trim());
            wood.setPhoto4(df.formatCellValue(rowIterator.next().getCell(1)).trim());
            wood.setPhoto5(df.formatCellValue(rowIterator.next().getCell(1)).trim());

            wood.setNumBoardsInPackage(intFromCell(rowIterator, df));
            wood.setNumPackagesInVehicle(intFromCell(rowIterator, df));
            
            wood.setDescriptionEn(df.formatCellValue(rowIterator.next().getCell(1)).trim());
            
            return wood;
        }
    }
    
    private static String getUrl(String str){
        return replaceSpecStringsBySubstring(str, "-");
    }
    
    private static String getId(String str){
        return replaceSpecStringsBySubstring(str, "_");
    }
    
    private static String  replaceSpecStringsBySubstring(String str, String substring){
        return str.replaceAll(" ",  substring)
                .replaceAll("'",    substring)
                .replaceAll("\"",   substring)
                .replaceAll(",",    substring)
                .replaceAll(":",    substring)
                .replaceAll(";",    substring)
                .replaceAll("\\.",  substring)
                .replaceAll("&",    substring)
                .replaceAll("/",    substring)
                .replaceAll("\\|",  substring) // ??
                .replaceAll("!",    substring)
                .replaceAll("\\?",  substring)
                .replaceAll("\\(",  substring)
                .replaceAll("\\)",  substring)
                .replaceAll("---",  substring)
                .replaceAll("--",   substring)
                .replaceAll("--",   substring);
    }
    
    private static int intFromCell (Iterator<Row> rowIterator, DataFormatter df ){
        int tt;
        Row tmp = rowIterator.next();
        try {
            tt = (int)tmp.getCell(1).getNumericCellValue();
        } catch (Exception e) {
            tt = Integer.parseInt( df.formatCellValue(tmp.getCell(1)).trim());
        }
        return tt;
    }
    
    private static double doubleFromCell (Iterator<Row> rowIterator, DataFormatter df ){
        Row tmp = rowIterator.next();
        ParseException parseException = null;
        try {
            return (double)tmp.getCell(1).getNumericCellValue();
        } 
        catch (Exception e) {
            for(Character sep: new Character[]{'.', ','})
                try{
                    return getDecimalFormatForDecimalSeparator(sep).parse(df.formatCellValue(tmp.getCell(1)).trim()).doubleValue();
                } 
                catch(ParseException pe){
                    parseException = pe; //continue cycle;
                }
        }
        
        throw new Error(parseException);
    }
    
    private static DecimalFormat getDecimalFormatForDecimalSeparator(Character separator)
    {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(separator);
        decimalFormat.setDecimalFormatSymbols(symbols);
        return decimalFormat;
    }
    
    private static boolean booleanFromSpecValue (Iterator<Row> rowIterator, DataFormatter df, 
            String trueS, String falseS ) throws IOException
    {
        String valueS = df.formatCellValue(rowIterator.next().getCell(1)).trim().toLowerCase();
        if (valueS.endsWith(trueS.toLowerCase())) 
            return true;
        else if (valueS.endsWith(falseS.toLowerCase()))
            return false;
        else
            throw new IOException("Invalid value " + valueS + " can not be converted to boolean type!");
    }
    
    private static void readSize(Wood wood, Iterator<Row> rowIterator, DataFormatter df){
        wood.setLength(intFromCell(rowIterator, df));
        wood.setWidth(intFromCell(rowIterator, df));
        wood.setThickness(intFromCell(rowIterator, df));
    }
    
    private static void readRawPlywoodParameters(RawPlywood rpw, Iterator<Row> rowIterator, DataFormatter df) throws IOException
    {
        rpw.setGrade(df.formatCellValue(rowIterator.next().getCell(1)).trim());
        rpw.setIsSanded(booleanFromSpecValue(rowIterator, df, "sanded", "unsanded"));
        rpw.setIsWaterResistance(booleanFromSpecValue(rowIterator, df, "FK", "-"));
    }
    
    private static void readLaminatedParticleBoardParameters(LaminatedParticleBoard lpb, Iterator<Row> rowIterator, DataFormatter df) {
        lpb.setLaminatedColor(df.formatCellValue(rowIterator.next().getCell(1)).trim());
    }
}