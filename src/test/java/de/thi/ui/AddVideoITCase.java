package de.thi.ui;

import de.thi.data.DataFactory;
import de.thi.pages.AddVideoPage;
import de.thi.pages.ListVideosPage;
import de.thi.videoflix.domain.Video;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class AddVideoITCase extends AbstractITCase {

    @Drone
    private WebDriver browser;

    @Page
    private AddVideoPage addVideoPage;

    @Test
    public void TestAddVideo(@InitialPage ListVideosPage listVideosPage){
        final Video testVideo = DataFactory.createTestVideo();
        /*listVideosPage.doAddVideo();
        addVideoPage.assertOnPage();
        addVideoPage.setVideo(testVideo);
        addVideoPage.doAddVideo();
        listVideosPage.assertOnPage();
        listVideosPage.assertVideoName(testVideo.getName());
        listVideosPage.assertDirectorName(testVideo.getDirector());
        listVideosPage.assertStudioName(testVideo.getStudio());
        listVideosPage.assertYear(String.valueOf(testVideo.getYear()));*/
    }

}