package com.web.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.web.qa.base.TestBase;

public class HomePage extends TestBase {

	// Initializing the Page Objects:

	public HomePage() {
		PageFactory.initElements(driver, this);

	}
}