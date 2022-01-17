package idv.wilson.demo.bdd;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelloStepdefs {
	Hello hello = null;
	String hi = null;

	@Given("I have a greeting application with {string}")
	public void i_have_a_greeting_application_with(String string) {
	    hello = new Hello(string);
	}
	@When("I ask it to say hi")
	public void i_ask_it_to_say_hi() {
	    hi = hello.sayHi();
	}
	@Then("I Receive {string}")
	public void i_receive(String string) {
	   assertEquals(string, hi);
	}
}
