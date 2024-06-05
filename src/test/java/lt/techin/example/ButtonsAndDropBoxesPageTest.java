package lt.techin.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ButtonsAndDropBoxesPageTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webdriveruniversity.com/Actions/index.html");
    }

    //Test 1
    @Test
    void testDragAndDrop() {
        ButtonsAndDropBoxesPage buttonsAndDropBoxesPage = new ButtonsAndDropBoxesPage(driver);

        assertTrue(buttonsAndDropBoxesPage.isDropboxColorChanged(), "Dropbox color has changed after drag and drop");
        assertTrue(buttonsAndDropBoxesPage.isDropboxTextChanged(), "Dropbox text has changed after drag and drop");


    }

    //Test 2
    @Test
    void testDoubleClick() {
        ButtonsAndDropBoxesPage buttonsAndDropBoxesPage = new ButtonsAndDropBoxesPage(driver);
        buttonsAndDropBoxesPage.isDoubleClickButtonClickedTwice();
        assertTrue(buttonsAndDropBoxesPage.hasDoubleClickButtonColorChanged(), "Section color has changed after double click");
    }

    //Test 3
    @Test
    void testHover() {
        ButtonsAndDropBoxesPage buttonsAndDropBoxesPage = new ButtonsAndDropBoxesPage(driver);
        buttonsAndDropBoxesPage.isFirstHoverElementDisplayed();
        buttonsAndDropBoxesPage.isSecondHoverElementDisplayed();
        buttonsAndDropBoxesPage.isThirdHoverElementDisplayed();
        /*
        assertTrue(buttonsAndDropBoxesPage.isFirstSubElementVisible(), "First hover element sub-elements are not visible");
        assertTrue(buttonsAndDropBoxesPage.isSecondSubElementVisible(), "Second hover element sub-elements are not visible");
        assertTrue(buttonsAndDropBoxesPage.isThirdSubElementVisible(), "Third hover element sub-elements are not visible");
        */
    }

    //Test 4
    @Test
    void testClickAndHold() {
        ButtonsAndDropBoxesPage buttonsAndDropBoxesPage = new ButtonsAndDropBoxesPage(driver);
        buttonsAndDropBoxesPage.isClickAnHoldOnButtonDisplayed();
        //assertTrue(buttonsAndDropBoxesPage.isClickAndHoldColorChanged(),"Section color did change after click");
    }


    @AfterEach
    public void close() throws InterruptedException{
        Thread.sleep(5000);
        driver.close();
    }




}
