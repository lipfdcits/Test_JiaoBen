package lpf.driver;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import lpf.driver.tools.JxlFun;
import lpf.driver.tools.TestBase;
import lpf.driver.tools.XmlFun;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReportExcelType extends TestListenerAdapter{
	private String reportPath;
	private int line_numbers = 3;
	/*##############################################################################################################
	 * 功能：测试开始之前自动调用，生成测试报告的文件
	 * 输入：ITestContext对象
	 * 输出：
	 * 日期：
	 */
	@Override
	public void onStart(ITestContext testContext) {
		File reportDir = new File("com.lpf.test-output/customize-report");
		if (!reportDir.exists()) {
			reportDir.mkdirs();
		}
		String reportName = getNowDate2()+".xls";
		reportPath = reportDir+"/"+reportName;
		try {
			JxlFun.createXls(reportPath, "测试报告");
			JxlFun.setColumnView(reportPath, "测试报告");
			JxlFun.mergeCells(reportPath, "测试报告", 1, 1, 4, 1);
			JxlFun.appendFormatTitle(reportPath, "测试报告", 1, 1, "测试用例执行结果");
			JxlFun.appendFormatHead(reportPath, "测试报告", 2, 1, "测试用例名");
			JxlFun.appendFormatHead(reportPath, "测试报告", 2, 2, "执行结果");
			JxlFun.appendFormatHead(reportPath, "测试报告", 2, 3, "异常信息");
			JxlFun.appendFormatHead(reportPath, "测试报告", 2, 4, "耗时");
		} catch (WriteException | IOException | BiffException e) {
			e.printStackTrace();
		}
	}

	/*##############################################################################################################
	 * 功能：每一个测试用例执行成功后自动调用，写入结果信息
	 * 输入：ITestResult对象
	 * 输出：
	 * 日期：
	 */
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		StringBuilder sb = new StringBuilder();
		sb.append(iTestResult.getMethod().getRealClass()+" . "+System.getProperty("line.separator")+iTestResult.getMethod().getMethodName());
		try {
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 1, sb.toString());
			JxlFun.appendFormatPass(reportPath, "测试报告", line_numbers, 2);
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 3, "测试通过");
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 4, getTestDuration(iTestResult));
		} catch (BiffException | WriteException | IOException e) {
			e.printStackTrace();
		}
		line_numbers++;
	}


	/*##############################################################################################################
	 * 功能：每一个测试用例跳过后自动调用，写入结果信息
	 * 输入：ITestResult对象
	 * 输出：
	 * 日期：
	 */
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Throwable throwable = iTestResult.getThrowable();
		StringBuilder sb = new StringBuilder();
		sb.append(iTestResult.getMethod().getRealClass()+" . "+System.getProperty("line.separator")+iTestResult.getMethod().getMethodName());
		try {
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 1, sb.toString());
			JxlFun.appendFormatSkip(reportPath, "测试报告", line_numbers, 2);
			JxlFun.appendFormatSkipText(reportPath, "测试报告", line_numbers, 3, throwable.getMessage());
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 4, getTestDuration(iTestResult));
		} catch (BiffException | WriteException | IOException e) {
			e.printStackTrace();
		}
		line_numbers++;
	}

	/*##############################################################################################################
	 * 功能：每一个测试用例执行失败后自动调用，写入结果信息
	 * 输入：ITestResult对象
	 * 输出：
	 * 日期：
	 */
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Throwable throwable = iTestResult.getThrowable();
		StringBuilder sb = new StringBuilder();
		sb.append(iTestResult.getMethod().getRealClass()+" . "+System.getProperty("line.separator")+iTestResult.getMethod().getMethodName());
		try {
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 1, sb.toString());
			JxlFun.appendFormatFail(reportPath, "测试报告", line_numbers, 2);

			//获取出错的行号信息
			StackTraceElement[] stackTraceElement = throwable.getStackTrace();
			String message1 = "";
			for(StackTraceElement ste : stackTraceElement){
				if(ste.toString().contains("testCases") || ste.toString().contains("commonFunction")){
					message1 = message1 + System.getProperty("line.separator") + ste.toString();
				}
			}

			//获取前300个字符的错误信息
			String message2 = throwable.getMessage().substring(0, 300);

			//写入测试报告中
			JxlFun.appendFormatFailText(reportPath, "测试报告", line_numbers, 3,message2 + System.getProperty("line.separator") + message1);
			JxlFun.appendFormatText(reportPath, "测试报告", line_numbers, 4, getTestDuration(iTestResult));
		} catch (BiffException | WriteException | IOException e) {
			e.printStackTrace();
		}
		line_numbers++;

		//根据ScreentShot_ON_OFF参数开关截图，ON，截图；OFF,不截图
		try {
			if (XmlFun.readNote("bin\\config\\config.xml", "ScreentShot_ON_OFF").equals("ON")){
				takeScreentShot(iTestResult);
			}
		} catch (DocumentException e) {
		}
	}

	/*##############################################################################################################
	 * 功能：截图,当需要时调用该方法
	 * 输入：ITestResult对象
	 * 输出：
	 * 日期：
	 */
	public void takeScreentShot(ITestResult iTestResult) {
		String SCREEN_SHOT_PATH = "com.lpf.test-output/screen-shot";
		String SCREEN_SHOT_NAME = iTestResult.getName()+".jpg";
		File dir = new File(SCREEN_SHOT_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String SCREEN_SHOT = dir.getAbsolutePath()+"/"+SCREEN_SHOT_NAME;

		WebDriver driver = TestBase.getDriver();

		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile (srcFile,new File(SCREEN_SHOT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*##############################################################################################################
	 * 功能：测试完成后自动调用，写入结果信息
	 * 输入：ITestContext对象
	 * 输出：
	 * 日期：
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		try {
			JxlFun.mergeCells(reportPath, "测试报告", 1, line_numbers, 4, line_numbers);//合并最后一行空行
			JxlFun.appendFormatHead(reportPath, "测试报告", line_numbers, 1, "统计信息");
			line_numbers++;
			JxlFun.mergeCells(reportPath, "测试报告", 2, line_numbers, 4, line_numbers);//合并显示执行时间的单元格
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers, 2, "执行完成时间：  "+getNowDate());
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers++, 1, "总用例："+allTestsSize(testContext));
			JxlFun.mergeCells(reportPath, "测试报告", 2, line_numbers, 4, line_numbers);//合并显示执行时间的单元格
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers, 2, "总耗时：  "+getTestDuration(testContext));
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers++, 1, "通过数："+passedTestsSize(testContext));
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers++, 1, "失败数："+failedTestsSize(testContext));
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers++, 1, "跳过例："+skippedTestsSize(testContext));
			JxlFun.appendFormatStatistical(reportPath, "测试报告", line_numbers++, 1, "执行通过率："+getPassPercent(testContext));
		} catch (BiffException | WriteException | IOException e) {
			e.printStackTrace();
		}
		getTestDuration(testContext);
	}

	/*##############################################################################################################
	 * 功能：获取用例总数
	 * 输入：ITestResult
	 * 输出：
	 * 日期：2017-11-1
	 */
	public int allTestsSize(ITestContext testContext) {
		return testContext.getAllTestMethods().length;
	}

	/*##############################################################################################################
	 * 功能：获取用例通过数
	 * 输入：ITestResult
	 * 输出：
	 * 日期：2017-11-1
	 */
	public int passedTestsSize(ITestContext testContext) {
		return testContext.getPassedTests().size();
	}

	/*##############################################################################################################
	 * 功能：获取用例失败数
	 * 输入：ITestResult
	 * 输出：
	 * 日期：2017-11-1
	 */
	public int failedTestsSize(ITestContext testContext) {
		return testContext.getFailedTests().size();
	}

	/*##############################################################################################################
	 * 功能：获取用例跳过数
	 * 输入：ITestResult
	 * 输出：
	 * 日期：2017-11-1
	 */
	public int skippedTestsSize(ITestContext testContext) {
		return testContext.getSkippedTests().size();
	}

	/*##############################################################################################################
	 * 功能：获取测试用例的执行通过率
	 * 输入：ITestResult
	 * 输出：
	 * 日期：2017-11-10
	 */
	public String getPassPercent(ITestContext testContext) {
		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(4);
		num.setMinimumFractionDigits(2);
		String passPercent = num.format((double)(passedTestsSize(testContext))/(double)(allTestsSize(testContext)));
		return passPercent;
	}

	/*##############################################################################################################
	 * 功能：获取用例的执行耗时
	 * 输入：ITestResult
	 * 输出：minutes+"分"+seconds+"秒"
	 * 日期：2017-11-1
	 */
	public String getTestDuration(ITestResult iTestResult) {
		long start = iTestResult.getStartMillis();
		long end =  iTestResult.getEndMillis();
		long duration = end-start;
		long minutes = (duration % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (duration % (1000 * 60)) / 1000;
		String time = minutes+"分"+seconds+"秒";
		return time;
	}

	/*##############################################################################################################
	 * 功能：获取整 个测试的的执行耗时
	 * 输入：testContext
	 * 输出：hour+"时"+minutes+"分"+seconds+"秒"
	 * 日期：2017-11-8
	 */
	public String getTestDuration(ITestContext testContext) {
		Date start = testContext.getStartDate();
		long starttime = start.getTime();
		Date end =  testContext.getEndDate();
		long endtime = end.getTime();
		long duration = endtime-starttime;

		long hour = duration / (60 * 60 * 1000);
		long minutes = (duration / (60 * 1000)) - hour * 60;
		long seconds = duration/1000 - hour*60*60 - minutes*60;
		String time = hour+"时"+minutes+"分"+seconds+"秒";
		return time;
	}

	/*##############################################################################################################
	 * 功能：获取当前日期时间"yyyy-MM-dd HH:mm:ss"
	 * 输入：
	 * 输出：
	 * 日期：2017-11-1
	 */
	public String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*##############################################################################################################
	 * 功能：获取当前日期时间"yyyy-MM-dd HH:mm:ss"
	 * 输入：
	 * 输出：
	 * 日期：2017-11-1
	 */
	public String getNowDate2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*##############################################################################################################
	 * 功能：获取当前日期"yyyy-MM-dd"
	 * 输入：
	 * 输出：
	 * 日期：2017-11-1
	 */
	public static String getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*##############################################################################################################
	 * 功能：获取当前时间"HH:mm:ss"
	 * 输入：
	 * 输出：
	 * 日期：2017-11-1
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}