package tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pages.GooglePage;
import utils.DateKeyWordUtil;

public class ExcelDataTest extends BaseTest {

    @Test
    public void searchAndStoreResults() {
        String currentDay = DateKeyWordUtil.getCurrentDay();
        List<String> keywordsNo = DateKeyWordUtil.getKeywordsNumber(currentDay);
        List<String> keywords = DateKeyWordUtil.getKeywordsForDay(currentDay);
        GooglePage googlePage = new GooglePage(driver);
    
        try (FileInputStream file = new FileInputStream("src/main/java/resources/Excel.xlsx");
            Workbook workbook = new XSSFWorkbook(file)) {
    
            Sheet sheet = workbook.getSheet(currentDay);
            int firstRowNum = sheet.getFirstRowNum();
    
            int startRowNum = firstRowNum + 1;
    
            int keywordIndex = 0;
            for (int i = startRowNum; i < startRowNum + keywords.size(); i++) {
                String keywordNo = keywordsNo.get(keywordIndex);
                String keyword = keywords.get(keywordIndex);
    
                WebElement searchInput = googlePage.getSearchInput();
                searchInput.clear();
                searchInput.sendKeys(keyword);
                searchInput.click();
               
                List<WebElement> searchElements = googlePage.getSearchElements();
    
                String longestOption = "";
                String shortestOption = searchInput.getText();
    
                for (WebElement searchElement : searchElements) {
                    String option = searchElement.getText();
    
                    if (longestOption.isEmpty() || option.length() > longestOption.length()) {
                        longestOption = option;
                    }
    
                    if (shortestOption.isEmpty() || option.length() < shortestOption.length()) {
                        shortestOption = option;
                    }
                }
                Row row = sheet.createRow(i);
                
                Cell keywordNumber = row.createCell(1);
                Cell keywordName = row.createCell(2);
                Cell longestOptionCell = row.createCell(3);
                Cell shortestOptionCell = row.createCell(4);

                keywordNumber.setCellValue(keywordNo);
                keywordName.setCellValue(keyword);
                longestOptionCell.setCellValue(longestOption);
                shortestOptionCell.setCellValue(shortestOption);
    
                keywordIndex++;
            }
    
            FileOutputStream fileData = new FileOutputStream("src/main/java/resources/Excel.xlsx");
            workbook.write(fileData);
            fileData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }     
}
