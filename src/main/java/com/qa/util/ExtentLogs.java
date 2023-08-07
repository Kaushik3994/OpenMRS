package com.qa.util;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import java.util.Objects;

public class ExtentLogs {
    static String[] data;

    public static void log(String message) {
        if (Objects.nonNull(ExtentCucumberAdapter.getCurrentScenario())) {
            ExtentCucumberAdapter.addTestStepLog("* " + message);
            Log.info(ScenarioFactory.getCurrentScenarioTestCaseIDs() + " " + message);
        }
        else{
            Log.info(ExtentCucumberAdapter.getCurrentScenario() + "is null");
        }
    }

    //    public static void filteredLog(String message) throws IOException {
//        data = ReusableMethods.getCredentials(Integer.parseInt(Objects.requireNonNull(
//                GlobalProperties.getGlobalConstantMessage("excelRowDataNum"))));
//        String filteredMessage = message.replace(data[1], "********");
//        if(Objects.nonNull(ExtentCucumberAdapter.getCurrentScenario()))
//            ExtentCucumberAdapter.addTestStepLog("* " + filteredMessage);
//        Log.info(ScenarioFactory.getCurrentScenarioTestCaseIDs()+" "+filteredMessage);
//    }
    public static void logWarn(String message) {
        if (Objects.nonNull(ExtentCucumberAdapter.getCurrentScenario())) {
            ExtentCucumberAdapter.addTestStepLog("* " + message);
            ExtentCucumberAdapter.getCurrentScenario().log(Status.WARNING, message);
            Log.warn(ScenarioFactory.getCurrentScenarioTestCaseIDs() + " " + message);
        }
        else{
            Log.info(ExtentCucumberAdapter.getCurrentScenario() + "is null. Cannot log warning.");
        }
    }

    public static void logPass(String message) {
        if (Objects.nonNull(ExtentCucumberAdapter.getCurrentScenario())) {
            ExtentCucumberAdapter.addTestStepLog("* " + message);
            ExtentCucumberAdapter.getCurrentScenario().log(Status.PASS, message);
            Log.pass(ScenarioFactory.getCurrentScenarioTestCaseIDs() + " " + message);
        }
        else{
            Log.info(ExtentCucumberAdapter.getCurrentScenario() + "is null. Cannot log pass status.");
        }
    }

    public static void logFail(String message) throws FrameworkException {
        if (Objects.nonNull(ExtentCucumberAdapter.getCurrentScenario())) {
            ExtentCucumberAdapter.addTestStepLog("* " + message);
            ExtentCucumberAdapter.getCurrentScenario().log(Status.FAIL, message);
            Log.error(ScenarioFactory.getCurrentScenarioTestCaseIDs() + " " + message);
            throw new FrameworkException(message);
        }
        else{
            Log.info(ExtentCucumberAdapter.getCurrentScenario() + "is null. Cannot log fail status.");
        }
    }
}
