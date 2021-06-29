package fhcampuswien.ac.at.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OebbStepDefinitions {
    private final WebDriver driver;
    // using library encapsulated shadow-dom(it could be replaced by method: fhcampuswien.ac.at.stepdefinitions.OebbStepDefinitions.getShadowRootElement)
    private final Shadow shadow;

    public OebbStepDefinitions() {
        BrowserUtil.SetDriverProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        shadow = new Shadow(driver);
    }

    @Before
    public void setUp() { driver.get("https://www.oebb.at/"); }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Returns encapsulated shadow-dom
    public WebElement getShadowRootElement(WebElement element) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
    }

    // Select first entry station in drop-down
    private void pickFirstFromList() {
        var shadow1 = getShadowRootElement(driver.findElement(By.xpath("/html/body/div/div/main/div[2]/div[2]/oebb-from-to-form")));
        var shadow2 = getShadowRootElement(shadow1.findElement(By.cssSelector("#oebb-ftf-form > oebb-from-to-station-select")));
        shadow2.findElement(By.cssSelector("div > ul > li:nth-child(1) > button:nth-child(1)")).click();
    }

    // It will switch to the second tab, when it appears
    private void switchToSecondTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    // Type departure
    @Given("from {string} was selected")
    public void fromWasSelected(String from) {
        shadow.findElement("#oebb-ftf-from").clear();
        shadow.findElement("#oebb-ftf-from").sendKeys(from);
        pickFirstFromList();
    }

    // Type destination
    @Given("to {string} was selected")
    public void toWasSelected(String to) throws InterruptedException {
        shadow.findElement("#oebb-ftf-to").clear();
        shadow.findElement("#oebb-ftf-to").sendKeys(to);
        TimeUnit.SECONDS.sleep(3);
        pickFirstFromList();
    }

    @Given("date menu button was pressed")
    public void dateMenuButtonPressed() {
        shadow.findElement("#oebb-ftf-departure-button").click();
    }

    @Given("date {string} (in format TT.MM.YYYY) was selected")
    public void ticketDateWasSelected(String date) {
        final String checkDate = (date.equals("nextMonth")) ? getFirstDayOfNextMonthDateInEnglish() : date;
        shadow.findElement("#oebb-date-field-date").clear();
        shadow.findElement("#oebb-date-field-date").sendKeys(checkDate);
    }

    @Given("date settings saved")
    public void timeAndDateSettingsSaved() {
        var shadow1 = getShadowRootElement(driver.findElement(By.xpath("/html/body/div/div/main/div[2]/div[2]/oebb-from-to-form")));
        shadow1.findElement(By.cssSelector("#oebb-ftf-form > oebb-date-time-dialog > div > button")).click();
    }


    @Given("ticket book button was pressed")
    public void ticketBookPressed() {
        shadow.findElementByXPath("//*[@id='oebb-ftf-form']/div[3]/button[1]").click();
    }

    @When("ticket show tickets button was pressed")
    public void showTicketsPressed() throws InterruptedException {
        switchToSecondTab();
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("/html/body/ui-view/div/div/div/div/div/app-start-container/section/div[2]/travel-action-listing/div/div[2]/travel-action[1]/button")).click();
    }

    @Then("ticket screen shows price {string}")
    public void ticketScreenShowsPrice(String price) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        String expected = "€ " + price;
        assertEquals(expected, driver.findElement(By.xpath("/html/body/ui-view/root-content/router-outlet-wrapper/app-ticket-shop-main/div/div/div/div/timetable-container/div/div/timetable-connection[1]/div/div[1]/div[5]/span")).getText());
    }

    @When("route search button was pressed")
    public void routeSearchPressed() {
        shadow.findElementByXPath("//*[@id='oebb-ftf-form']/div[3]/button[2]").click();
    }

    @Then("route Planner shows the values for: From {string}, To: {string}, Date: {string}, Time: {string}")
    public void routerPlannerShowsTheValuesForFromToDateTime(String from, String to, String date, String time) {
        switchToSecondTab();
        final String checkDate = (date.equals("nextMonth")) ? getFirstDayOfNextMonthDateInEnglish() : date;
        assertTrue(driver.findElement(By.xpath(("//div[@id='HFSResult']/div/div/div/span[2]"))).getText().contains(from), "To label should contain: " + from);
        assertTrue(driver.findElement(By.xpath(("//div[@id='HFSResult']/div/div/div[2]/span[2]"))).getText().contains(to), "To label should contain: " + to);
        assertTrue(driver.findElement(By.xpath(("//div[@id='HFSResult']/div/div[2]/div/span[2]"))).getText().contains(checkDate), "Date label should contain: " + checkDate);
    }

    private String getFirstDayOfNextMonthDateInEnglish() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        nextMonth = LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), 1);
        return nextMonth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("en")));
    }
}
