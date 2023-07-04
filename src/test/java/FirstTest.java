
import org.junit.Test;
import org.openqa.selenium.By;

import ui.*;


public class FirstTest extends CoreTestCase {

    /*УБРАТЬ ПОСЛЕ РЕФАКТОРИНГА<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    /*УБРАТЬ ПОСЛЕ РЕФАКТОРИНГА<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/



    @Test
    public void testCheckTitleElementInArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        String article_title_element = "org.wikipedia:id/view_page_title_text";
        MainPageObject.assertElementPresent(
                By.id(article_title_element),
                "We have not found " + article_title_element + " in " + search_line + " article"
        );

    }
}

