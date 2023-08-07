package stepDefinition;

import io.cucumber.java.Scenario;

import java.util.List;
import java.util.stream.Collectors;

public class ScenarioFactory {

    private static ThreadLocal<Scenario> scenario = new ThreadLocal<>();

    public synchronized static void setScenario(Scenario sc){
        scenario.set(sc);
    }

    public synchronized static Scenario getCurrentScenario(){
        return scenario.get();
    }

    public synchronized static String getCurrentScenarioTestCaseIDs(){
        Scenario scenario = getCurrentScenario();
        String testCaseIDs = "";
        if(scenario != null){
            List<String> testCases = scenario
                    .getSourceTagNames()
                    .stream()
                    .filter(e -> e.contains("MS")).map(e -> e.trim().replaceAll("@", ""))
                    .collect(Collectors.toList());
            testCaseIDs = String.join("|", testCases);
        }
        return testCaseIDs;
    }
}
