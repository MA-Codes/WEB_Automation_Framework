package com.web.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.web.qa.base.TestBase;

public class LoginPage extends TestBase{
		
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public void signin(String id, String password) {
		System.err.println(id);
		System.out.println(password);	
	}
	
	
}
