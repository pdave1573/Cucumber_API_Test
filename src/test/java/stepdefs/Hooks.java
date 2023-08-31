package stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;

public class Hooks {
	
	@Before("@Scenario1")
	public void beforeScenario1(){
		System.out.println("Running Scenario1 tag...");
	}
	
	@Before("@Scenario2")
	public void beforeScenario2(){
		System.out.println("Running Scenario2 tag...");
	}
	
	@AfterAll
	public static void tearDown() {
		//SendMailSSLWithAttachment email = new SendMailSSLWithAttachment();
	}
}
