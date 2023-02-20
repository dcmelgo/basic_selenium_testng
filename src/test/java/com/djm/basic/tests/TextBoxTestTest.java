package com.djm.basic.tests;

import com.djm.basic.pages.TextBoxPage;
import org.testng.annotations.Test;
import com.djm.basic.utils.ConfigReader;

public class TextBoxTestTest extends BaseTest {
    private TextBoxPage textBoxPage;

    @Test
    public void submitEntry() {
        try {
            String name = ConfigReader.getDataValue("name");
            String email = ConfigReader.getDataValue("email");
            String caddress = ConfigReader.getDataValue("current");
            String paddress = ConfigReader.getDataValue("permanent");

            textBoxPage.enter_full_name(name);
            textBoxPage.enter_email(email);
            textBoxPage.enter_cAddress(caddress);
            textBoxPage.enter_pAddress(paddress);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}