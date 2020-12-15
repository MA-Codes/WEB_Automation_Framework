package com.web.qa.pages;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.web.qa.base.TestBase;




public class LandingPage extends TestBase {

	// Initializing the Page Objects:	
	public LandingPage() {
		PageFactory.initElements(driver, this);

	}

// Validating all available links in landing page
	
	public void link_validation() throws InterruptedException {
//		Alert alert=driver.switchTo().alert();
//		alert.dismiss();
//		driver.findElement(By.xpath("//button[contains(text(),'✕']")).click();
	 List<WebElement> atag=driver.findElements(By.tagName("a"));
	 System.out.println(atag.size());
	 String hashurl=prop.getProperty("url")+"#";
	 for(int i=0;i<atag.size();i++)
	 {
		 System.out.println(atag.get(i).getAttribute("href"));
		 if(atag.get(i).getAttribute("href") != null)
		 {	
			 if(atag.get(i).getAttribute("href") !=hashurl )
			 {
		 atag.get(i).click();
		 Thread.sleep(200);
		 if(driver.findElement(By.xpath("//div[@class='nav-sprite hmenu-close-icon']")).isDisplayed())
		 {
			 driver.findElement(By.xpath("//div[@class='nav-sprite hmenu-close-icon']")).click();
		 }
			 }
		 }
		 else
		 {
			 System.out.println("null link found");
		 }
	 }
		
	}
//  Validate all buttons in landing page
	
}
