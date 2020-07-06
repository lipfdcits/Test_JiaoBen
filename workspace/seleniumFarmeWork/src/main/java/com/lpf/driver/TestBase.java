package com.lpf.driver;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;

public class TestBase {
	private static WebDriver driver = null;
	public static WebDriver getDriver(){
		return driver;
	}

	static
	{
		String browser = null;
		try {
			browser = XmlFun.readNote("src\\config\\config.xml", "browser");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(browser.equals("IE")){
			if(driver == null){
				driver = Browser.getIEDriver();
			}
		}else if(browser.equals("Chrome")){
			if(driver == null){
				driver = Browser.getChromeDriver();
			}
		}else if(browser.equals("FireFox")){
			if(driver == null){
				driver = Browser.getFirefoxDriver();
			}
		}
	}

	public void TestBase(){
	}


}