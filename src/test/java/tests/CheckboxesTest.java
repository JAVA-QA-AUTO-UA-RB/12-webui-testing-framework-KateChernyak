package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckboxesPage;


public class CheckboxesTest extends BasicSetupTest {
    @Test
    public void allCheckBoxesShouldBeSelected() {
        CheckboxesPage page = new CheckboxesPage(driver);
        page.open();
        page.selectAll();

        Assert.assertTrue(page.areAllSelected(), "Не всі чекбокси обрані");
    }
}
