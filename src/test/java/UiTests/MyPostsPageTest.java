package UiTests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Authorization on the GeekTest website Tests")
public class MyPostsPageTest extends AbstractTest {

    private void saveScreen(String name) throws IOException {
        File file = MyUtils.makeScreenshot(getDriver(),name + " " + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Test
    @DisplayName("Availability of posts")
    void postTest() throws IOException, InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Наличие постов");
        assertTrue(myPostsPage.getPost1().isDisplayed());
        assertTrue(myPostsPage.getPost2().isDisplayed());
        assertTrue(myPostsPage.getPost3().isDisplayed());
        assertTrue(myPostsPage.getPost4().isDisplayed());
    }

    @Test
    @DisplayName("The presence of post titles")
    public void postsTitleTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Наличие заголовков постов");
        assertTrue(myPostsPage.getPost1_title().isDisplayed());
        assertTrue(myPostsPage.getPost2_title().isDisplayed());
        assertTrue(myPostsPage.getPost3_title().isDisplayed());
        assertTrue(myPostsPage.getPost4_title().isDisplayed());
    }

    @Test
    @DisplayName("The presence of a description of posts")
    public void postsDescriptionTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Наличие описания постов");
        assertTrue(myPostsPage.getPost1_description().isDisplayed());
        assertTrue(myPostsPage.getPost2_description().isDisplayed());
        assertTrue(myPostsPage.getPost3_description().isDisplayed());
        assertTrue(myPostsPage.getPost4_description().isDisplayed());
    }

    @Test
    @DisplayName("Availability of images of posts")
    public void postsImgTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Наличие изоброжений постов");
        assertTrue(myPostsPage.getPost1_img().isDisplayed());
        assertTrue(myPostsPage.getPost2_img().isDisplayed());
        assertTrue(myPostsPage.getPost3_img().isDisplayed());
        assertTrue(myPostsPage.getPost4_img().isDisplayed());
    }

    @Test
    @DisplayName("The presence of a placeholder in the absence of an image")
    void imgPlaceholderTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Наличие заглушки при отсутствии изображения");
        assertTrue(myPostsPage.getPost1_img().isDisplayed());
        assertEquals("https://test-stand.gb.ru/placeholder/800x600.gif", myPostsPage.getPost1_img_src());
    }

    @Test
    @DisplayName("Image aspect ratio 2:3")
    public void postAspectRatioTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?page=2");
        Thread.sleep(3000);
        saveScreen("Соотношение сторон изображений 2:3");
        assertEquals("0.6666666666666667", myPostsPage.getImg1AspectRatio());
        assertEquals("0.6666666666666667", myPostsPage.getImg2AspectRatio());
        assertEquals("0.6666666666666667", myPostsPage.getImg3AspectRatio());
        assertEquals("0.6666666666666667", myPostsPage.getImg4AspectRatio());
    }

    @Test
    @DisplayName("Go to the next page")
    public void nextPageTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        myPostsPage.nextPage();
        Thread.sleep(2000);
        saveScreen("Переход на следующую страницу");
        assertEquals("https://test-stand.gb.ru/?page=2", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Go to the previous page")
    public void previousPageTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?page=2");
        Thread.sleep(3000);
        myPostsPage.previousPage();
        Thread.sleep(3000);
        saveScreen("Переход на предыдущую страницу");
        assertEquals("https://test-stand.gb.ru/?page=1", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Go to the previous page from the first page")
    public void firstPageTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        myPostsPage.previousPage();
        Thread.sleep(2000);
        saveScreen("Переход на предыдущую страницу с первой страницы");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Go to the next page from the last page")
    public void lastPageTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?page=2");
        Thread.sleep(3000);
        myPostsPage.nextPage();
        Thread.sleep(2000);
        saveScreen("Переход на следующую страницу с последней страницы");
        assertEquals("https://test-stand.gb.ru/?page=2", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Changing the sorting of posts")
    void orderTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        myPostsPage.change_order();
        Thread.sleep(3000);
        saveScreen("Сортировка в порядке убывания");
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=DESC", getDriver().getCurrentUrl());
        myPostsPage.change_order();
        Thread.sleep(3000);
        saveScreen("Сортировка в порядке возрастания");
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=ASC", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Default sorting")
    void orderDefaultTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?sort=createdAt&order=ASC");
        Thread.sleep(3000);
        String firstImgSrc = myPostsPage.getPost1_img_src();
        myPostsPage.clickHome();
        Thread.sleep(2000);
        saveScreen("Сортировка по-умолчанию");
        assertEquals(firstImgSrc, myPostsPage.getPost1_img_src());
    }

    @Test
    @DisplayName("The transition is not to my posts")
    public void NotMyPostsTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        myPostsPage.switchToNotMyPosts();
        Thread.sleep(3000);
        saveScreen("Переход на чужие посты");
        assertEquals("https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Click on the Home button to go to the main page")
    public void homeTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC");
        Thread.sleep(3000);
        myPostsPage.clickHome();
        Thread.sleep(2000);
        saveScreen("Переход по кнопке Home на главную страницу");
        assertEquals("Blog", myPostsPage.getTitle_Blog());
    }

    @Test
    @DisplayName("Message about the absence of posts")
    void postsTest() throws InterruptedException, IOException {
        MyPostsPage myPostsPage = new MyPostsPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMinChar();
        Thread.sleep(1000);
        saveScreen("Сообщение об отсутствии постов");
        assertEquals("No items for your filter", myPostsPage.getMessage());
    }
}
