package com.qa.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FrameworkException extends RuntimeException{

    public FrameworkException(String message){
        super(message, null, true, false);
        Log.info(ScenarioFactory.getCurrentScenarioTestCaseIDs()+message);
    }

    public FrameworkException(String message, Throwable e){
        super(message, null, true, false);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        Log.info(ScenarioFactory.getCurrentScenarioTestCaseIDs()+sw.toString());
    }
}
