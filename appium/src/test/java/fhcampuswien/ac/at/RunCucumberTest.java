package fhcampuswien.ac.at;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/fhcampuswien.ac.at.features",
        glue = "fhcampuswien.ac.at.stepdefinitions",
// The json reports are saved into the main protect target in order to create a single report. Same by Selenium.
        plugin = {"pretty", "json:../target/cucumber_appium.json", "html:target/cucumber.html"})
public class RunCucumberTest {

}
