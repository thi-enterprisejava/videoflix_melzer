package de.thi.pages;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.soap.SAAJResult;

import static org.junit.Assert.assertEquals;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;

@Location("listVideos.xhtml")
public class ListVideosPage extends AbstractPage {
    @FindBy(xpath = "//tbody/tr[last()]/td[1]")
    private WebElement lastVideoName;

    //
    @FindBy(xpath = "//tbody/tr[last()]/td[2]")
    private WebElement lastVideoStudio;

    @FindBy(xpath = "//tbody/tr[last()]/td[3]")
    private WebElement lastVideoDirector;

    @FindBy(xpath = "//tbody/tr[last()]/td[4]")
    private WebElement lastVideoYear;

    @FindBy(xpath = "//tbody/tr[last()]/td[5]/a")
    private WebElement lastEditFormButton;

    @FindBy(xpath = "//tbody/tr[last()]/td[6]/a")
    private WebElement lastDetailsFormButton;

    public void assertOnPage() {
        assertTitle("listvids.heading");
    }

    public void assertVideoName(String videoName) {
        assertEquals(videoName, lastVideoName.getText());
    }

    public void assertStudioName (String studioName) {
        assertEquals(studioName, lastVideoStudio.getText());
    }

    public void assertDirectorName (String directorName) {
        assertEquals(directorName, lastVideoDirector.getText());
    }

    public void assertYear (String year) {
        assertEquals(year, lastVideoYear.getText());
    }

    public void doAddVideo() {
        WebElement addVideoButton = getButtonByLabel("listvids.new");
        guardHttp(addVideoButton).click();
    }

    public void doEditVideo() {
        guardHttp(lastEditFormButton).click();
    }

    public void doShowDetails() {
        guardHttp(lastDetailsFormButton).click();
    }
}
