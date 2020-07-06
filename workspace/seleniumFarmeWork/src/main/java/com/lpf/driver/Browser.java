package com.lpf.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser{
	/*##############################################################################################################
	 * 功能：初始化一个IE浏览器对象
	 * 输入：无
	 * 输出：IE浏览器对象
	 */
	public static WebDriver getIEDriver(){
		//设置IEDriverServer的路径
		System.setProperty("webdriver.ie.driver", "Lib\\IEDriverServer_Win32_3.5.0\\IEDriverServer.exe");
		//设置IE路径
		System.setProperty("webdriver.ie.bin", "C:\\Program Files\\Internet Explorer\\iexplore.exe");
		DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
		//设置浏览器安全性
		ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCaps.setCapability("ignoreProtectedModeSettings", true);
		ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:Tabs");

		WebDriver driver = new InternetExplorerDriver(ieCaps);
		//设置全局等待时间为5秒
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//IE窗口最大化
		driver.manage().window().maximize();
		//指定IE窗口的大小
		//Dimension dimension = new Dimension(1366,768);
		//driver.manage().window().setSize(dimension);
		return driver;
	}

	/*##############################################################################################################
	 * 功能：初始化一个IE浏览器对象
	 * 输入：无
	 * 输出：IE浏览器对象
	 */
	public static WebDriver getTypicalIEDriver(){
		//设置IEDriverServer的路径
		System.setProperty("webdriver.ie.driver", "Lib\\IEDriverServer_Win32_3.5.0\\IEDriverServer.exe");
		//设置IE路径
		System.setProperty("webdriver.ie.bin", "C:\\Program Files\\internet explorer\\iexplore.exe");
		WebDriver driver = new InternetExplorerDriver();
		//IE窗口最大化
		driver.manage().window().maximize();
		//设置全局等待时间为5秒
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/*##############################################################################################################
	 * 功能：初始化一个chrome浏览器对象
	 * 输入：无
	 * 输出：chrome浏览器对象
	 */
	public static WebDriver getChromeDriver() {
		//设置chromedriver的路径
		System.setProperty("webdriver.chrome.driver","Lib\\chromedriver_32&64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		//通过配置参数禁止data;的出现
		//options.addArguments("--user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");
		//通过配置参数删除“您使用的是不受支持的命令行标记：--ignore-certificate-errors。稳定性和安全性会有所下降。”提示
		//options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
		//启动Chrome浏览器
		WebDriver driver = new ChromeDriver(options);
		//谷歌窗口最大化
		driver.manage().window().maximize();
		//设置全局等待时间为5秒
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/*##############################################################################################################
	 * 功能：初始化一个firefox浏览器对象
	 * 输入：无
	 * 输出：firefox浏览器对象
	 */
	public static WebDriver getFirefoxDriver() {
		//设置火狐的路径
		System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		//启动火狐浏览器
		WebDriver driver = new FirefoxDriver();
		//火狐最大化
		driver.manage().window().maximize();
		//设置全局等待时间为5秒
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
