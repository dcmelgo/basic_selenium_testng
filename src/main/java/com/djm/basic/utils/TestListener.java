package com.djm.basic.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.djm.basic.tests.BaseTest;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, getMarkupForTestFailure(result));
        test.get().log(Status.FAIL, result.getThrowable());

        // Take screenshot on test failure
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
                Reporter.log("<br><img src='" + screenshotPath + "' height='400' width='400'/><br>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private synchronized String getMarkupForTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();
        StringBuilder errorDetails = new StringBuilder();
        errorDetails.append(throwable.toString()).append("\n");
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            errorDetails.append("\n").append(stackTraceElement.toString());
        }
        return "<pre>" + errorDetails.toString() + "</pre>";
    }

    private synchronized String captureScreenshot(String methodName) {
        String screenshotName = methodName + "_" + System.currentTimeMillis() + ".png";
        TakesScreenshot driver = (TakesScreenshot) BaseTest.driver;
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("screenshots", screenshotName);

        try {
            Files.createDirectories(destinationPath.getParent());
            Files.move(screenshotFile.toPath(), destinationPath);
            return destinationPath.toAbsolutePath().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}