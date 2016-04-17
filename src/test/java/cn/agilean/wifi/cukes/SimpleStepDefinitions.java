package cn.agilean.wifi.cukes;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

public class SimpleStepDefinitions {
	private char theLetter;

    @Given("^the letter '([A-Za-z])'$")
    public void gimmeALetter(final char theLetter) {
        this.theLetter = theLetter;
    }

    @When("^I check the letter(?:s)?$")
    public void checkThem() {
        // just a stub
    }

    @Then("^the letter should be '([A-Za-z])'$")
    public void checkTheLetter(final char aLetter) {
    	Assert.assertThat(theLetter, CoreMatchers.is(aLetter));
    }
}