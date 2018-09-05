package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.page.AccountPage;
import com.academy.automationpractice.ddt.framework.page.HomePage;
import com.academy.automationpractice.ddt.util.PropertyManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "authProvider", enabled = false)
    public void testAuthCorrect(String email, String password) throws Exception {
        System.out.println("Start 'testAuthCorrect'");

        AccountPage accountPage =
                new HomePage(driver)
                        .clickSingIn()
                        .inputEmail(email)
                        .inputPassword(password)
                        .clickSingIn();

        String userNameLinkText = accountPage.getUserNameLinkText();
        assertEquals(userNameLinkText, "Oleg Afanasiev");
        accountPage.clickSignOut();

        System.out.println("Complete 'testAuthCorrect'");
    }

    @Test(dataProvider = "incorrectLoginProvider")
    public void testAuthIncorrect(String email, String password, String errorMsg) {
        System.out.println(String.format("email: %s, password:%s, errorMsg:%s", email, password, errorMsg));
    }

    @DataProvider(name="authProvider")
    private Object[][] authProvider() {
        return new Object[][]{
                {PropertyManager.getProperty("automation.username"), PropertyManager.getProperty("automation.password")}
        };
    }

    @DataProvider(name = "incorrectLoginProvider")
    public Object[][] provideIncorrectAuthData() {
        String authDataPath = PropertyManager.getProperty("auth.incorrect.data");

        try (XSSFWorkbook workbook = new XSSFWorkbook(authDataPath)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            Object[][] data = new Object[sheet.getLastRowNum()][3];

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                XSSFRow row = sheet.getRow(r);
                String email = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                String errMsg = row.getCell(2).getStringCellValue();

                data[r-1][0] = email;
                data[r-1][1] = password;
                data[r-1][2] = errMsg;
            }

            Arrays.stream(data).flatMap(Arrays::stream).forEach(System.out::println);
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
