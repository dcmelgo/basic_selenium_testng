package com.djm.basic.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("Document Title ?");
            sparkReporter.config().setReportName("Report Name ?");
            sparkReporter.config().setTheme(Theme.DARK);
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
