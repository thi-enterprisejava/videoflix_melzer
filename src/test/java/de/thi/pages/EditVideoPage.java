package de.thi.pages;

import de.thi.videoflix.domain.Video;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

@Location("editvideo.xhtml")
public class EditVideoPage extends AbstractPage {

    @FindBy(xpath = "//input[contains(@id, 'vname'")
    private WebElement videoName;

    @FindBy(xpath = "//input[contains(@id, 'studio'")
    private WebElement studio;

    @FindBy(xpath = "//input[contains(@id, 'director'")
    private WebElement director;

    @FindBy(xpath = "//input[contains(@id, 'year'")
    private WebElement year;

    @FindBy(xpath = "//input[contains(@id, 'isseries'")
    private WebElement isseries;

    @FindBy(xpath = "//input[contains(@id, 'genre'")
    private List<WebElement> genres;

    @FindBy(xpath = "//input[contains(@id, 'descr'")
    private WebElement description;

    public void setVideo(Video video){
        videoName.sendKeys(video.getName());
        studio.sendKeys(video.getStudio());
        director.sendKeys(video.getDirector());
        year.sendKeys(String.valueOf(video.getYear()));
        isseries.click();

        List<String> g = Collections.singletonList("Drama");
        for (String genre : g) {
            for (WebElement webElement : genres) {
                if (genre.equals(webElement.findElement(By.xpath(".//option")).getText())) {
                    webElement.findElement(By.xpath(".//input[@type='checkbox']")).click();
                }
            }
        }
        description.sendKeys(video.getDescription());
    }

    public void doEditVideo() {
        guardHttp(getButtonByLabel("add.save")).click();
    }

    public void assertOnPage() {
        assertTitle("edit.heading");
    }
}
