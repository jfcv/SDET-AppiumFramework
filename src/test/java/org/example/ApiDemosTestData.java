package org.example;

import org.testng.annotations.DataProvider;

public class ApiDemosTestData {

    @DataProvider(name = "ApiDemosTestData")
    public Object[][] getData() {
        Object[][] obj = new Object[][]{
                {"hello"},
                {"@#$%"},
                {"there"},
                {"&/()?¿"}
        };
        return obj;
    }

}
