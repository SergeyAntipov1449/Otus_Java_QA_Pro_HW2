package commons;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import scoped.GuiceScoped;
import utilits.Waiters;

public abstract class AbsCommons {
  public WebDriver driver;
  protected Waiters webDriverWait;
  protected Actions actions;

  @Inject
  public AbsCommons(GuiceScoped guiceScoped) {
    this.driver = guiceScoped.driver;
    this.webDriverWait = new Waiters(driver);
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }
}
