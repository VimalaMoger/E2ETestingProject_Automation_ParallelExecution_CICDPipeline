package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHelper {

    public static ExtentReports reports;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh-mm_ss");

    public static ExtentReports getReportsObject(){
        String reportPath ="./reports/"+dtf.format(LocalDateTime.now());
        //report generation type of extent report
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Test Results");
        extentSparkReporter.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size','-webkit-fill-available');");

        reports = new ExtentReports();
        reports.attachReporter(extentSparkReporter);   //sending spark reporter object
        reports.setSystemInfo("Tester is: ","Vimala Moger");
        return reports;

    }

}
