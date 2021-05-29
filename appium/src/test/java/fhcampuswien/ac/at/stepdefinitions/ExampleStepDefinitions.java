package fhcampuswien.ac.at.stepdefinitions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleStepDefinitions {
    private final AndroidDriver<AndroidElement> driver;

    public ExampleStepDefinitions() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability("appPackage", "at.oebb.ts");
        capabilities.setCapability("appActivity", "at.oebb.ts.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
        driver.findElementById("com.android.permissioncontroller:id/permission_deny_button").click();
        driver.findElementById("at.oebb.ts:id/recyclerview").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[4]/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.ImageView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Given("route from {string} was selected")
    public void routeFromWasSelected(String from) throws InterruptedException {
        MobileElement destination = driver.findElementByAccessibilityId("Destination");
        destination.sendKeys(from);
        (new TouchAction(driver)).tap(PointOption.point(546, 136)).perform();
        Thread.sleep(200L);
        (new TouchAction(driver)).tap(PointOption.point(413, 397)).perform();
    }

    @Given("route to {string} was selected")
    public void routeToWasSelected(String to) throws InterruptedException {
        MobileElement arrival = driver.findElementByAccessibilityId("arrival");
        arrival.sendKeys(to);
        (new TouchAction(driver)).tap(PointOption.point(534, 240)).perform();
        Thread.sleep(200L);
        (new TouchAction(driver)).tap(PointOption.point(464, 391)).perform();
    }

    @Given("date \"01.NEXT_MONTH\" was selected")
    public void dateWasSelected() {
        driver.findElementById("at.oebb.ts:id/header_time_from").click();
        driver.findElementByAccessibilityId("Date").click();
        driver.findElementByAccessibilityId(getFirstDayOfNextMonthDate()).click();
    }

    @Given("time \"09:00\" was selected")
    public void timeWasTyped() {
        driver.findElementByAccessibilityId("Time").click();
        driver.findElementById("at.oebb.ts:id/hours").click();
        (new TouchAction(driver)).tap(PointOption.point(290, 1183)).perform();
        driver.findElementById("at.oebb.ts:id/minutes").click();
        (new TouchAction(driver)).tap(PointOption.point(538, 827)).perform();
        driver.findElementByAccessibilityId("CONFIRM").click();
    }

    @When("\"One-way tickets\" was pressed")
    public void wasPressed() {
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[5]/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.ImageView").click();
    }

    @Then("Router Planner shows the values for: From {string}, To: {string}")
    public void routerPlannerShowsTheValuesForFromToDateTime(String from, String to) {
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[5]/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout[1]").click();
        driver.findElementByAccessibilityId("Journey Preview").click();

        final String fromHbf = driver.findElementById("at.oebb.ts:id/journey_preview_or_travel_assistant_item_departure_station_name").getText();
        final String toHbf = driver.findElementById("at.oebb.ts:id/journey_preview_or_travel_assistant_arrival_station_title_name").getText();

        assertEquals(from, fromHbf);
        assertEquals(to, toHbf);
    }

    private String getFirstDayOfNextMonthDate() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        nextMonth = LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), 1);
        return nextMonth.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }
}
