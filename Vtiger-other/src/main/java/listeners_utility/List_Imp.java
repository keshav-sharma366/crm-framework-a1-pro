package listeners_utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_class.BaseClass;
import generic_utility.JavaUtility;
public class List_Imp implements ISuiteListener, ITestListener {
	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		String time=JavaUtility.dateTime();
//		long time = System.currentTimeMillis();

		ExtentSparkReporter spark = new ExtentSparkReporter("./reports/" + time + ".html");
		spark.config().setDocumentTitle("sauce demo login");
		spark.config().setReportName("login report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);

		report.setSystemInfo("ATE", "Keshav");
		report.setSystemInfo("Browser", "edge");
		report.setSystemInfo("Window", "11");

	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodName + " is failed");
		
//		take ss 
		TakesScreenshot tks = (TakesScreenshot) BaseClass.sdriver;
		String ss = tks.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(ss);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName + " is skipped");
	}

	@Override
	public void onFinish(ISuite suite) {
//		report backup
		report.flush();
	}
}