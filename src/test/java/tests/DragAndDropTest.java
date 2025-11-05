package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

public class DragAndDropTest extends BasicSetupTest {
    @Test
    public void shouldSwapColumns() {
        DragAndDropPage page = new DragAndDropPage(driver);
        page.open();

        String before = page.getColumnAText();
        page.dragAtoB();
        String after = page.getColumnAText();
        Assert.assertNotEquals(after, before, "Елементи не помінялися місцями");
    }
}
