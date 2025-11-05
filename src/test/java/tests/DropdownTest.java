package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DropdownPage;

public class DropdownTest extends BasicSetupTest {
    @Test
    public void selectOption2() {
        DropdownPage page = new DropdownPage(driver);
        page.open();
        page.selectOption2();

        Assert.assertEquals(page.getSelectedOption(), "Option 2", "Option 2 не обрано");
    }
}
