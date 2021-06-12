package fhcampuswien.ac.at.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleStepDefinitions {
    private final WebDriver driver;

    public ExampleStepDefinitions() {
        BrowserUtil.SetDriverProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
        driver.get("https://fahrplan.oebb.at/bin/query.exe/en");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("route from {string} was selected")
    public void routeFromWasSelected(String from) {
        driver.findElement(By.id("HFS_from")).clear();
        driver.findElement(By.id("HFS_from")).sendKeys(from);
    }

    @Given("route to {string} was selected")
    public void routeToWasSelected(String to) {
        driver.findElement(By.id("HFS_to")).clear();
        driver.findElement(By.id("HFS_to")).sendKeys(to);
    }

    @Given("date \"01.NEXT_MONTH\" was selected")
    public void dateWasSelected() {
        driver.findElement(By.id("HFS_date_0")).clear();
        driver.findElement(By.id("HFS_date_0")).sendKeys(getFirstDayOfNextMonthDate());
    }

    @Given("time {string} was typed")
    public void timeWasTyped(String time) {
        driver.findElement(By.id("HFS_time_0")).clear();
        driver.findElement(By.id("HFS_time_0")).sendKeys(time);
    }

    @When("\"Search connection\" was pressed")
    public void wasPressed() {
        driver.findElement(By.name("start")).click();
    }

    @Then("Router Planner shows the values for: From {string}, To: {string}, Date \"01.NEXT_MONTH\", Time: {string}")
    public void routerPlannerShowsTheValuesForFromToDateTime(String from, String to, String time) {
        final String firstDayOfNextMonthDate = getFirstDayOfNextMonthDate();
        assertEquals(from, driver.findElement(By.xpath("//div[@id='HFSResult']/div/div/div/span[2]")).getText());
        assertTrue(driver.findElement(By.xpath(("//div[@id='HFSResult']/div/div/div[2]/span[2]"))).getText().contains(to), "To label should contain: " + to);
        assertTrue(driver.findElement(By.xpath(("//div[@id='HFSResult']/div/div[2]/div/span[2]"))).getText().contains(firstDayOfNextMonthDate), "Date label should contain: " + firstDayOfNextMonthDate);
        assertEquals(time, driver.findElement(By.xpath("//div[@id='HFSResult']/div/div[2]/div[2]/span[2]")).getText());
    }

    private String getFirstDayOfNextMonthDate() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        nextMonth = LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), 1);
        return nextMonth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("en")));
    }
}
