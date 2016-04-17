package cn.agilean.wifi.cukes;


import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExampleStepDefinitions {
	List<String> animalsThis;
	List<String> animalsTableThis;
	@Given("the following animals: (.*)")
	public void the_following_animals(List<String> animals) {
		animalsThis = animals;
	}
	
	@Given("the following animals table:")
	public void the_following_animals_table(List<String> animals) {
		animalsTableThis = animals;
	}
	
	@When("^I check the value(?:s)?$")
    public void checkThem() {
        // just a stub
    }

    @Then("^following animals should be: (.*)")
    public void checkAnimals(final List<String> animals) {
    	Assert.assertThat(animalsThis, CoreMatchers.is(animals));
    }
    
    @Then("^following animals table should be:")
    public void checkAnimalsTable(final List<String> animals) {
    	Assert.assertThat(animalsThis, CoreMatchers.is(animals));
    }
}
