package stepdefs;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@Scenario1")
	public void beforeScenario1(){
		System.out.println("Running Scenario1 tag...");
	}
	
	@Before("@Scenario2")
	public void beforeScenario2(){
		System.out.println("Running Scenario2 tag...");
	}

}
