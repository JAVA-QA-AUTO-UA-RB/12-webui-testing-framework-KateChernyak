package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;

public class AddRemoveElementsTest extends BasicSetupTest {
    @Test
    public void AddAndRemoveElements() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver);
        page.open();

        page.addElements(3);
        Assert.assertEquals(page.getDeleteButtonsCount(), 3, "Повинно бути 3 кнопки Delete");

        page.removeAllElements();
        Assert.assertEquals(page.getDeleteButtonsCount(), 0, "Усі кнопки мають бути видалені");
    }
}
