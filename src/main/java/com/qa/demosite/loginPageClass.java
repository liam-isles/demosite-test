package com.qa.demosite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class loginPageClass {

	Actions action;
	@FindBy(name="username") private WebElement usernameInput;
	@FindBy(name="password") private WebElement passwordInput;
	@FindBy(name="FormsButton2") private WebElement saveButton;
	
	public void addUser() {
		
	}
	
	
}
