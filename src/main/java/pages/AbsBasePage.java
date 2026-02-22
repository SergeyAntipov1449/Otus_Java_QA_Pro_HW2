package pages;

import annotations.Path;
import com.google.inject.Inject;
import commons.AbsCommons;
import exceptions.AnnotationNotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import scoped.GuiceScoped;

public abstract class AbsBasePage<T> extends AbsCommons {
  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String baseUrl = System.getProperty("base.url");

  @FindBy(xpath = "//*[contains(text(), 'Посещая наш сайт')]/following-sibling::div//button")
  protected WebElement notificationButton;

  public String getPath() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    throw new AnnotationNotFoundException();
  }

  public T open() {
    driver.get(baseUrl + getPath());
    return (T) this;
  }

  public void closeNotification() {
    webDriverWait.waitForCondition(ExpectedConditions.stalenessOf(notificationButton), 4);
    notificationButton.click();
  }
}
