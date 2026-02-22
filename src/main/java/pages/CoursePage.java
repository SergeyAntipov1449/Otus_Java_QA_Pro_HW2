package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import scoped.GuiceScoped;

public class CoursePage extends AbsBasePage<CoursePage> {
  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public String getCourseName() {

    return driver.findElement(By.cssSelector(".sc-s2pydo-6 h1")).getText();
  }
}
