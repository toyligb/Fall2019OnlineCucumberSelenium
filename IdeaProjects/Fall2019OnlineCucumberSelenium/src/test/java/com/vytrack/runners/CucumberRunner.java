package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // glue - path to the package with step definition methods
        glue = "com/vytrack/step_definitions",
        // features - path to the folder/directory with feature files.
        features = "src/test/resources/features",
        // stands for checking if every test step has code implementation
        // if true - DON"T run test, just check if every step is implemented
        // if false - test will run
        dryRun = false,
        strict = false,
        tags = "@create_car_ddt",
        plugin = {
                "html:target/default-report",
                "json:target/cucumber1.json"
        }
)
public class CucumberRunner {
}
