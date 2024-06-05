package lt.techin.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonsAndDropBoxesPage {

    @FindBy(xpath = "//*[@id='draggable']")
    private WebElement draggableBox;

    @FindBy(xpath = "//*[@id='droppable']")
    private WebElement dropboxDropHere;

    @FindBy(css = "#double-click")
    private WebElement doubleClickButton;

    @FindBy(xpath = "//div/div[4]/div/div[1]/button")
    private WebElement firstHoverElement;

    @FindBy(xpath = "//div/div[4]/div/div[2]/button")
    private WebElement secondHoverElement;

    @FindBy(xpath = "//div/div[4]/div/div[3]/button")
    private WebElement thirdHoverElement;

    @FindBy(xpath = "//div/div[5]")
    private WebElement clickAndHoldButton;

    private WebDriver driver;

    public ButtonsAndDropBoxesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Task 1: drag and drop function
    public void dragDraggableBoxIntoADropbox() {
        if (draggableBox.isDisplayed()) {
            Actions clickAndDrag = new Actions(driver);
            clickAndDrag.dragAndDrop(draggableBox, dropboxDropHere).perform();
            System.out.println("Drag and drop performed.");
            //wait for bg color to be changed
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.attributeToBeNotEmpty(dropboxDropHere, "background-color"));
        }else {
            System.out.println("Draggable box or dropbox is not displayed.");
        }
    }

    // Task 1 check whether the dropbox color has changed after the box has been dropped into it
    public boolean isDropboxColorChanged() {
        String beforeColor = dropboxDropHere.getCssValue("background-color");
        System.out.println("Before color: " + beforeColor);
        dragDraggableBoxIntoADropbox();

        //timeout duration
        long startTime = System.currentTimeMillis();
        long timeoutMillis = 5000; // 5 seconds timeout
        String afterColor = beforeColor;

        //loop that registers color changes or when timeout is reached
        while (afterColor.equals(beforeColor) && (System.currentTimeMillis() - startTime) < timeoutMillis) {
            afterColor = (String)((JavascriptExecutor)driver).executeScript("return getComputedStyle(arguments[0]).backgroundColor;", dropboxDropHere);
        }

        System.out.println("After color: " + afterColor);

        return !beforeColor.equals(afterColor);
    }
    //Task 1 check whether the dropbox text has changed
    public boolean isDropboxTextChanged() {
        String beforeText = dropboxDropHere.getText();
        dragDraggableBoxIntoADropbox();
        String afterText = dropboxDropHere.getText();
        return beforeText.equals(afterText);
    }

    // Task 2: Double Click
    public void isDoubleClickButtonClickedTwice() {
        if (doubleClickButton.isDisplayed()) {
            Actions doubleClick = new Actions(driver);
            doubleClick.doubleClick(doubleClickButton).perform();
        }
    }


    //Task 2 check whether the click and hold section color has changed
    public boolean hasDoubleClickButtonColorChanged() {
        WebElement section = driver.findElement(By.id("double-click"));
        String beforeColor = section.getCssValue("background-color");
        isDoubleClickButtonClickedTwice();
        String afterColor = section.getCssValue("background-color");
        return !beforeColor.equals(afterColor);
    }

    // Task 3: Hover
    public void isFirstHoverElementDisplayed() {
        if (firstHoverElement.isDisplayed()) {
            Actions hover = new Actions(driver);
            hover.moveToElement(firstHoverElement).pause(Duration.ofSeconds(3)).perform();
        }
    }

    public void isSecondHoverElementDisplayed() {
        if (secondHoverElement.isDisplayed()) {
            Actions hover = new Actions(driver);
            hover.moveToElement(secondHoverElement).pause(Duration.ofSeconds(3)).perform();
        }
    }

    public void isThirdHoverElementDisplayed() {
        if (thirdHoverElement.isDisplayed()) {
            Actions hover = new Actions(driver);
            hover.moveToElement(thirdHoverElement).pause(Duration.ofSeconds(3)).perform();
        }
    }

    //Task 3 verify whether the sub elements are being displayed on hover

    public boolean isFirstSubElementVisible() {
        WebElement subElement = driver.findElement(By.xpath("//*[@id=\"div-hover\"]/div[1]/div/a[@class=\"list-alert\"]"));
        Actions hover = new Actions(driver);
        hover.moveToElement(subElement).perform();
        return subElement.isDisplayed();
    }

    public boolean isSecondSubElementVisible() {
        WebElement subElement = driver.findElement(By.xpath("//*[@id=\"div-hover\"]/div[2]/div/a[@class=\"list-alert\"]"));
        Actions hover = new Actions(driver);
        hover.moveToElement(subElement).perform();
        return subElement.isDisplayed();
    }

    public boolean isThirdSubElementVisible() {
        WebElement subElement = driver.findElement(By.xpath("//*[@id=\"div-hover\"]/div[3]/div/a[@class=\"list-alert\"]"));
        Actions hover = new Actions(driver);
        hover.moveToElement(subElement).perform();
        return subElement.isDisplayed();
    }

    // Task 4: Click and Hold
    public void isClickAnHoldOnButtonDisplayed() {
        if (clickAndHoldButton.isDisplayed()) {
            Actions clickAndHold = new Actions(driver);
            clickAndHold.clickAndHold(clickAndHoldButton).perform();
        }
    }

    //Task 4 check whether the click and hold section color has changed
    public boolean isClickAndHoldColorChanged() {
        WebElement section = driver.findElement(By.id("double-click"));
        String beforeColor = section.getCssValue("style");
        isDoubleClickButtonClickedTwice();
        String afterColor = section.getCssValue("style");
        return !beforeColor.equals(afterColor);
    }




}
