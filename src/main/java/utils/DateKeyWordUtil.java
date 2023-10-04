package utils;

import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateKeyWordUtil {
    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE"); 
        return dateFormat.format(calendar.getTime());
    }
    
    public static List<String> getKeywordsNumber(String keyNo) {
        List<String> keywordsNo = new ArrayList<>();

        try (FileInputStream file = new FileInputStream("src/main/java/resources/Excel.xlsx");
            Workbook workbook = WorkbookFactory.create(file)) {

            String currentDay = getCurrentDay();
            Sheet sheet = workbook.getSheet(currentDay);

            for (Row row : sheet) {
                Cell cell = row.getCell(1); 
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String keywordNo = cell.getStringCellValue();
                    keywordsNo.add(keywordNo);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return keywordsNo;
    }

    public static List<String> getKeywordsForDay(String cellData) {
        List<String> keywords = new ArrayList<>();

        try (FileInputStream file = new FileInputStream("src/main/java/resources/Excel.xlsx");
            Workbook workbook = WorkbookFactory.create(file)) {

            String currentDay = getCurrentDay();
            Sheet sheet = workbook.getSheet(currentDay);

            for (Row row : sheet) {
                Cell cell = row.getCell(2); 
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String keyword = cell.getStringCellValue();
                    keywords.add(keyword);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return keywords;
    }
}