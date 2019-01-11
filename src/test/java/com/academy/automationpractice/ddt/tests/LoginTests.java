package com.academy.automationpractice.ddt.tests;

import com.academy.automation.framework.util.PropertyManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "authProvider")
    public void testAuthCorrect(String userNameExpected) throws Exception {
        manager.goTo().home();
        manager.session().login();

        manager.verify().userIsLoggedIn(userNameExpected);
        manager.session().logout();
    }

    @Test(dataProvider = "incorrectLoginProvider1")
       public void testAuthIncorrect(String email, String password, String errorMsgExpected) {
        manager.goTo().home();
        manager.session().loginAs(email, password);
        String errMessageActual = manager.session().getErrMessage();
        assertThat(errMessageActual, equalTo(errorMsgExpected));
    }

    // TODO move user
    @DataProvider(name="authProvider")
    private Object[][] authProvider() {
        return new Object[][]{
                {"OLEG AFANASIEV"}
        };
    }

    @DataProvider(name = "incorrectLoginProvider1")
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
