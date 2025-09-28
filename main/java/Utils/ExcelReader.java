package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static Object[][] getSheetData(String filePath) {
        try (InputStream is = new FileInputStream(filePath);
             Workbook wb = new XSSFWorkbook(is)) {

            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            List<String> headers = new ArrayList<>();
            List<Object[]> records = new ArrayList<>();

            if (!rowIterator.hasNext()) {
                throw new RuntimeException("Excel sheet is empty");
            }

            // header
            Row headerRow = rowIterator.next();
            for (Cell c : headerRow) {
                headers.add(c.getStringCellValue());
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Object[] arr = new Object[headers.size()];
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        arr[i] = "";
                    } else {
                        if (cell.getCellType() == CellType.STRING) {
                            arr[i] = cell.getStringCellValue();
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                arr[i] = cell.getLocalDateTimeCellValue().toLocalDate().toString();
                            } else {
                                arr[i] = String.valueOf((long) cell.getNumericCellValue());
                            }
                        } else {
                            arr[i] = cell.toString();
                        }
                    }
                }
                records.add(arr);
            }

            Object[][] result = new Object[records.size()][headers.size()];
            for (int i = 0; i < records.size(); i++) {
                result[i] = records.get(i);
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage(), e);
        }
    }
}
