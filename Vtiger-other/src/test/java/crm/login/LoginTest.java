package crm.login;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;

//@Listeners(listeners_utility.List_Imp.class)
public class LoginTest extends BaseClass {

    @Test(groups = "regression")
    public void testLogin() throws IOException, ParseException, InterruptedException {
        String pageTitle = driver.getTitle();
        Assert.assertTrue(
            pageTitle.toLowerCase().contains("vtiger"), 
            "Login failed! Actual page title was: " + pageTitle
        );

        System.out.println("Login Test Completed Successfully.");
    }
}