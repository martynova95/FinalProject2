package UiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPostsPage extends AbstractPage {

    public MyPostsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Home']")
    private WebElement button_home;
    @FindBy(xpath = "//a[@href='/about']")
    private WebElement button_about;
    @FindBy(xpath = "//a[@href='/contact']")
    private WebElement button_contact;
    @FindBy(xpath = "//li[contains(@class, 'surface')]")
    private WebElement button_hello;
    @FindBy(xpath = "//span[contains(text(), 'Profile')]")
    private WebElement button_menu_profile;
    @FindBy(xpath = "//span[contains(text(), 'Login')]")
    private WebElement button_menu_login;
    @FindBy(xpath = "//span[contains(text(), 'Logout')]")
    private WebElement button_menu_logout;
    @FindBy(xpath = "//h1[text()='Blog']")
    private WebElement title_Blog;
    @FindBy(xpath = "//button[@id='create-btn']")
    private WebElement button_create;
    @FindBy(xpath = "//i[contains(text(), 'sort')][2]/parent::button")
    private WebElement button_order;
    @FindBy(xpath = "//button[contains(@class, 'switch')]")
    private WebElement button_switch_not_my_posts;
    @FindBy(xpath = "//a[contains(text(), 'Previous Page')]")
    private WebElement button_previous_page;
    @FindBy(xpath = "//a[contains(text(), 'Next Page')]")
    private WebElement button_next_page;

    @FindBy(xpath = "//a[contains(@class, 'post')][1]")
    private WebElement post1;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/img")
    private WebElement post1_img;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/h2")
    private WebElement post1_title;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/div")
    private WebElement post1_description;

    @FindBy(xpath = "//a[contains(@class, 'post')][2]")
    private WebElement post2;
    @FindBy(xpath = " //a[contains(@class, 'post')][2]/img")
    private WebElement post2_img;
    @FindBy(xpath = "//a[contains(@class, 'post')][2]/h2")
    private WebElement post2_title;
    @FindBy(xpath = "//a[contains(@class, 'post')][2]/div")
    private WebElement post2_description;

    @FindBy(xpath = "//a[contains(@class, 'post')][3]")
    private WebElement post3;
    @FindBy(xpath = " //a[contains(@class, 'post')][3]/img")
    private WebElement post3_img;
    @FindBy(xpath = "//a[contains(@class, 'post')][3]/h2")
    private WebElement post3_title;
    @FindBy(xpath = "//a[contains(@class, 'post')][3]/div")
    private WebElement post3_description;

    @FindBy(xpath = "//a[contains(@class, 'post')][4]")
    private WebElement post4;
    @FindBy(xpath = " //a[contains(@class, 'post')][4]/img")
    private WebElement post4_img;
    @FindBy(xpath = "//a[contains(@class, 'post')][4]/h2")
    private WebElement post4_title;
    @FindBy(xpath = "//a[contains(@class, 'post')][4]/div")
    private WebElement post4_description;

    @FindBy(xpath = "//p")
    private WebElement message;


    public MyPostsPage clickHome() {
        button_home.click();
        return this;
    }

    public String getTitle_Blog() {
        return title_Blog.getText();
    }

    public MyPostsPage change_order() {
        button_order.click();
        return this;
    }

    public MyPostsPage switchToNotMyPosts() {
        button_switch_not_my_posts.click();
        return this;
    }

    public MyPostsPage previousPage() {
        button_previous_page.click();
        return this;
    }

    public MyPostsPage nextPage() {
        button_next_page.click();
        return this;
    }

    public WebElement getPost1() {
        return post1;
    }
    public WebElement getPost2() {
        return post2;
    }
    public WebElement getPost3() {
        return post3;
    }
    public WebElement getPost4() {
        return post4;
    }

    public WebElement getPost1_title() {
        return post1_title;
    }
    public WebElement getPost2_title() {
        return post2_title;
    }
    public WebElement getPost3_title() {
        return post3_title;
    }
    public WebElement getPost4_title() {
        return post4_title;
    }

    public WebElement getPost1_description() {
        return post1_description;
    }
    public WebElement getPost2_description() {
        return post2_description;
    }
    public WebElement getPost3_description() {
        return post3_description;
    }
    public WebElement getPost4_description() {
        return post4_description;
    }

    public WebElement getPost1_img() {
        return post1_img;
    }
    public WebElement getPost2_img() {
        return post2_img;
    }
    public WebElement getPost3_img() {
        return post3_img;
    }
    public WebElement getPost4_img() {
        return post4_img;
    }

    public String getPost1_img_src() {
        return post1_img.getAttribute("src").toString();
    }

    public double getImg1AspectRatio() {
        double height = getPost1_img().getSize().height;
        double width = getPost1_img().getSize().width;
        double aspectRatio = height / width;
        return aspectRatio;
    }
    public double getImg2AspectRatio() {
        double height = getPost2_img().getSize().height;
        double width = getPost2_img().getSize().width;
        double aspectRatio = height / width;
        return aspectRatio;
    }
    public double getImg3AspectRatio() {
        double height = getPost3_img().getSize().height;
        double width = getPost3_img().getSize().width;
        double aspectRatio = height / width;
        return aspectRatio;
    }
    public double getImg4AspectRatio() {
        double height = getPost4_img().getSize().height;
        double width = getPost4_img().getSize().width;
        double aspectRatio = height / width;
        return aspectRatio;
    }

    public String getMessage() {
        return message.getText();
    }
}
