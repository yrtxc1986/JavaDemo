package idv.wilson.demo.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:hello_world.feature", plugin = {"html:../report/cucumber/hello-report.html"})
public class GreetingTest {





}
