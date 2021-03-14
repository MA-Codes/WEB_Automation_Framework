package com.web.qa.pages;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.web.qa.base.TestBase;
import com.web.qa.util.LoadExcel;


public class LoginPage extends TestBase{
		
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
		
	
	public static void signin_excel(String id) throws Throwable
	{
		String FileName=System.getProperty("user.dir")+ "/src/main/java/com/web/qa/testdata/TestData.xlsx";
		String[] req= {"username","password","launguage"};
		String[] username=LoadExcel.Excel_cellvalue(FileName,req[0]);
		String[] password=LoadExcel.Excel_cellvalue(FileName,req[1]);
		String[] language=LoadExcel.Excel_cellvalue(FileName,req[2]);
		for(int i=0;i<=username.length-1;i++)
		{
			signin(username[i],password[i],language[i]);
			logout();
		}
		
	}
	
	@FindBy(xpath = "//input[@id='authUser']")
	static
	WebElement username;
	@FindBy(xpath = "//input[@id='clearPass']")
	static
	WebElement passwd;
	@FindBy(xpath = "//select[@name='languageChoice']")
	static
	WebElement languag;
	@FindBy(xpath = "//div[@title='Current user']")
	static
	WebElement currentuser;
	@FindBy(xpath = "//button[@type='submit']")
	static
	WebElement login;
	@FindBy(xpath = "//li[@data-bind='click: logout']")
	static
	WebElement logout;
	
	
	public static void signin(String id, String password,String language)
	{
		username.sendKeys(id);
		passwd.sendKeys(password);
		Select languageselect=new Select(languag);
		languageselect.selectByValue(language);
		login.click();
	}
	
	public static void logout()
	{
		currentuser.click();
		logout.click();
	}
	
}
