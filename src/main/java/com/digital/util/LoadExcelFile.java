package com.digital.util;



import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class LoadExcelFile {

    public static String[][] getData(String pathname) {
        XSSFRow row;
        XSSFCell cell;
        String[][] value = null;
        double[][] nums = null;

        try {

           // FileInputStream excelFile = new FileInputStream(new File("src/main/resources/test_personne.xlsx"));
            FileInputStream excelFile = new FileInputStream(new File(pathname));
            // Workbook workbook = new XSSFWorkbook(excelFile);
            //FileInputStream inputStream = new FileInputStream("TEST.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            // get sheet number
            int sheetCn = workbook.getNumberOfSheets();

            //for (int cn = 0; cn < sheetCn; cn++) {

            // get 0th sheet data
            XSSFSheet sheet = workbook.getSheetAt(0);

            // get number of rows from sheet
            int rows = sheet.getPhysicalNumberOfRows();

            // get number of cell from row
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println("rows"+rows);
            System.out.println("cells"+cells);

            value = new String[rows][cells];

            for (int r = 2; r <= 100; r++) {
               // r = r+10000;
                row = sheet.getRow(r); // bring row
                if (row != null) {
                    for (int c = 0; c < cells; c++) {
                        cell = row.getCell(c);
                        nums = new double[rows][cells];

                        if (cell != null) {

                            switch (cell.getCellType()) {

                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value[r][c] = cell.getCellFormula();
                                  //  System.out.println("CELL TYPE FORMULA"+value[r][c]);
                                    break;

                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        double d = cell.getNumericCellValue();
                                        Date date = DateUtil.getJavaDate(d);
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                       // System.out.println("date parser : "+format.format(date));
                                        value[r][c] = ""
                                                + format.format(date);
                                       // System.out.println("The cell contains a date value: " + cell.getDateCellValue());
                                    }else {
                                        value[r][c] = ""
                                                + cell.getNumericCellValue();
                                       // System.out.println("CELL TYPE NUMERIC"+value[r][c]);
                                    }

                                    break;

                                case XSSFCell.CELL_TYPE_STRING:
                                    value[r][c] = ""
                                            + cell.getStringCellValue();
                                  //  System.out.println("CELL TYPE STRING"+value[r][c]);
                                    break;

                                case XSSFCell.CELL_TYPE_BLANK:
                                    value[r][c] = "";
                                  //  System.out.println("CELL TYPE VIDE"+value[r][c]);
                                    break;

                                case XSSFCell.CELL_TYPE_ERROR:
                                    value[r][c] = ""+cell.getErrorCellValue();
                                   // System.out.println("CELL TYPE ERROR"+value[r][c]);
                                    break;
                                default:
                            }
                            // System.out.print(value[r][c]);

                        } else {
                            //   System.out.print("[null]\t");
                        }
                    } // for(c)
                    // System.out.print("\n");
                }
                System.out.println("ligne "+r+" ajouté");
            } // for(r)
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(value.length);
        return value;
    }
}
