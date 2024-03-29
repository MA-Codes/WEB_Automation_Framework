package com.web.qa.base;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.web.qa.util.TestUtil;
import com.web.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
		public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/web"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		String os=System.getProperty("os.name");
		
		if(browserName.equals("chrome"))
		{
			if(os.contains("Windows"))
			{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/linux/chromedriver");
			}
			//To handle location popup 
			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();			
			caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			prefs.put("profile.default_content_setting_values.notifications", 1);
//			prefs.put("profile.default_content_settings.popups", 0);
//			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\Chrome_Downloads");
//			prefs.put("safebrowsing.enabled", "true");
//			options.addArguments("--window-size=1920,1080");
			options.addArguments("--start-maximized");
			options.addArguments("--headless");
//			options.setExperimentalOption("prefs", prefs);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
			
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));	
	}
	
//To Capture Eclipse Console Logs to text file and attach in mail	
	public static BufferedWriter bwcc;
	public static void ConsoleLogs(String met_name) throws IOException {
        System.out.println("================== Console LOGS =======================");
        String logpath=System.getProperty("user.dir")+"\\logs\\"+"\\Consolelogs_"+met_name+".txt";
         bwcc = new BufferedWriter(new FileWriter(logpath,true));
        bwcc.write(WebEventListener.toText);
		System.out.println("======================================================");
		bwcc.close();
        
    }
	
// To Capture Browser Console Logs to a text file and attach in mail	
	public static void logBrowserConsoleLogs(String met_name) throws IOException {
        System.out.println("================== BROWSER LOGS =======================");
        String logpath=System.getProperty("user.dir")+"\\logs\\"+met_name+".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(logpath,true));
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            bw.write(met_name+" ----> "+new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage()+ System.lineSeparator());
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        bw.close();
        System.out.println("=======================================================");
    }
	
	
	public void closeBrowser() throws IOException
	{
		
		driver.quit();
	}
	
	
}
