package scoped;

import com.google.inject.Singleton;
import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  public WebDriver driver = new WebDriverFactory().create();
  public String requiredCourseName;
}
