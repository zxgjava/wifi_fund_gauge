package cn.agilean.wifi.cukes;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDefinitions {
	private String username;
	private String password;
	
	@Given("^Prepared user with username <\"([^\"]*)\"> and password <\"([^\"]*)\">$")
	public void prepared_user_with_username_and_password(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Then("^Open login page$")
	public void open_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		// just a stub
	}

	@Then("^Fill login form with username <\"([^\"]*)\"> and password <\"([^\"]*)\"> and click login$")
	public void fill_login_form_with_username_and_password_and_click_login(String usernameStr, String passwordStr) {
		Assert.assertThat(username, CoreMatchers.is(usernameStr));
		Assert.assertThat(password, CoreMatchers.is(passwordStr));
	}

	@Then("^Check we logged in with username <\"([^\"]*)\">$")
	public void check_we_logged_in_with_username(String usernameStr) {
		Assert.assertThat(username, CoreMatchers.is(usernameStr));
	}
}
