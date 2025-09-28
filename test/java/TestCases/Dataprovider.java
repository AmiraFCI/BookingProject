package TestCases;

import Utils.ExcelReader;
import org.testng.annotations.DataProvider;

import java.nio.file.Paths;

public class Dataprovider {
   @DataProvider(name = "datesData")
    public static Object[][] datesData() {
        String path = Paths.get("src", "test", "resources", "testdata.xlsx").toString();
        return ExcelReader.getSheetData(path);
    }
}
