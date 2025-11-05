package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HorizontalSliderPage;

public class HorizontalSliderTest extends BasicSetupTest {
    @Test
    public void sliderValueShouldChange() {
        HorizontalSliderPage page = new HorizontalSliderPage(driver);
        page.open();

        String start = page.getCurrentValue();
        page.movesSliderRight(4);

        String end = page.getCurrentValue();
        Assert.assertNotEquals(start, end, "Слайдер не змінив положення");
    }
}
