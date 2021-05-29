package fhcampuswien.ac.at.selenium;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/fhcampuswien.ac.at.selenium.features",
        glue = "fhcampuswien.ac.at.selenium.stepdefinitions",
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber.html"})
public class RunCucumberTest {

}
