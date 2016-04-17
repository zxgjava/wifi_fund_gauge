package cn.agilean.wifi.cukes;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(features={"features/"})
@CucumberOptions(features={"features/"}, plugin = {"pretty", "html:target/cucumber"})
public class CucumberStarterIT {
}