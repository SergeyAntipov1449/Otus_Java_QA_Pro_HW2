package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scoped.GuiceScoped;

@Path("/")
//@PathTemplate("/$1")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(css = "nav span[title='Обучение']")
  private WebElement educationMenu;

  public void showEducationMenu() {
    actions.moveToElement(educationMenu).build().perform();
  }
}
