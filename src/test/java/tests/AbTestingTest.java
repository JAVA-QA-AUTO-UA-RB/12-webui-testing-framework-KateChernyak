package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AbTestingPage;

public class AbTestingTest extends BasicSetupTest {

    @Test
    public void headerShouldContainAbTestText(){
        AbTestingPage abTestingPage = new AbTestingPage(driver);
        abTestingPage.open();
        String text =  abTestingPage.getHeaderText();
        Assert.assertTrue(text.contains("A/B Test"), "Заголовок не містить тексту 'A/b Test'");
    }
}
