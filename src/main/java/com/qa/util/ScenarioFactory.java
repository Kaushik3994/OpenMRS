package com.qa.util;

import io.cucumber.java.Scenario;

import java.util.List;
import java.util.stream.Collectors;

public class ScenarioFactory {

    public static ThreadLocal<Scenario> scenario = new ThreadLocal<>();

    public synchronized static void setScenario(Scenario sc){
        scenario.set(sc);
    }

    public synchronized static Scenario getCurrentScenario(){
        return scenario.get();
    }

    public synchronized static String getCurrentScenarioTestCaseIDs(){
        Scenario currentScenario = getCurrentScenario();
        String testCaseIDs = "";
        if(currentScenario != null){
            String testCaseID = currentScenario
                    .getSourceTagNames()
                    .stream()
                    .map(tag -> tag.replaceAll("@", "").trim()) // Remove "@" symbol
                    .filter(tag -> tag.matches("^[A-Za-z]\\d+$"))
                    .findFirst() // Find the first matching tag
                    .map(tag -> tag.trim()) // Trim the tag again after processing
                    .orElse(""); // Default value if no matching tag is found
        return testCaseID;
        }
        return null;
    }


}
