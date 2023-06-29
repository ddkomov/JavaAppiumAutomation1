import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import ui.MainPageObject;
import ui.SearchPageObject;


public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present in the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        WebElement title_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSearchHaveText() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Cannot find text 'Search…' in searchline",
                5
        );
    }

    @Test
    public void testCompareArticleTitleAndCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']"),
                "Java",
                "Cannot find text 'Java' in search results",
                5
        );
        MainPageObject.assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "JavaScript",
                "Cannot find text 'JavaScript' in search results",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present in the page",
                5
        );
    }

    //    @Test
//    public void testSearchResultsCompareText() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//        List elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
//
//        for (int i = 0; i < elements.size(); i++) {
//
//        }
//
//
//
//
//    }
    @Test
    public void testSwipeArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20

        );
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        MainPageObject. waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSetValue(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into 'Name of the list' input",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find saved folder in 'My list'",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe article in folder of 'My list'"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "Linkin Park Discography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue("We found too few results!"
                , amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "xzczsavadbsbs";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result of label by the request " + search_line,
                15
        );
        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
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
                "Cannot find 'Object-oriented programming language input' topic searching by " + search_line,
                15
        );
        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );

        driver.runAppInBackground(2);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background input",
                5
        );
    }

    @Test
    public void testSaveTwoArticlesToMyLists() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String name_of_folder = "Learning programming 2";

        MainPageObject.waitForElementAndSetValue(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into 'Name of the list' input",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Selenium",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Testing framework for web applications']"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find option to add article to reading list named " + name_of_folder,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find saved folder in 'My list'",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe article in folder of 'My list'"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Selenium (software)']"),
                "Cannot find second article in 'My lists' after deleted first",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title of saved article in 'My lists' after deleted first",
                15
        );
    }

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

