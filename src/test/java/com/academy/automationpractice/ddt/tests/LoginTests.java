package com.academy.automationpractice.ddt.tests;

import com.academy.util.PropertyManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "authProvider")
    public void testAuthCorrect(String email, String password, String userNameExpected) throws Exception {
        System.out.println("Start 'testAuthCorrect'");

        manager.goTo().home();
        manager.session().login();

        manager.verify().userIsLoggedIn(userNameExpected);
        manager.session().logout();

        System.out.println("Complete 'testAuthCorrect'");
    }

    @Test(dataProvider = "incorrectLoginProvider")
    // TODO
    public void testAuthIncorrect(String email, String password, String errorMsgExpected) {
        System.out.println("Start 'testAuthIncorrect'");
        System.out.println(String.format("email: %s, password:%s, errorMsg:%s", email, password, errorMsgExpected));
        manager.goTo().home();
        manager.session().loginAs(email, password);
        String errMessageActual = manager.session().getErrMessage();
        assertThat(errMessageActual, equalTo(errorMsgExpected));
        System.out.println("Complete 'testAuthIncorrect'");
    }

    @DataProvider(name="authProvider")
    private Object[][] authProvider() {
        return new Object[][]{
                {PropertyManager.from("automation").getProperty("username"), PropertyManager.from("automation").getProperty("automation.password"), "OLEG AFANASIEV"}
        };
    }

    @DataProvider(name = "incorrectLoginProvider")
    public Object[][] provideIncorrectAuthData() {
        String authDataPath = PropertyManager.from("automation").getProperty("auth.incorrect.data");

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

            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
