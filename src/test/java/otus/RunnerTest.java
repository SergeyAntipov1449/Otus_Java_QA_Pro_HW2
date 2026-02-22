package otus;

import org.junit.platform.suite.api.*;

@Suite
@SelectPackages("otus")
@SelectClasspathResource("scenarious")
@IncludeEngines("cucumber")
@SuiteDisplayName("Otus scenarious")
@ConfigurationParameter(key = "cucumber.glu", value = "hooks, steps")
public class RunnerTest {
}
