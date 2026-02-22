package otus.steps;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.By;
import pages.MainPage;
import scoped.GuiceScoped;

@ScenarioScoped
public class MainPageSteps {
  @Inject
  private MainPage mainPage;
  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Открыть главнаю стираницу")
  public void openMainPage() {
    mainPage.open();
    mainPage.closeNotification();
  }

  @Если("Развенуть выпадающее меня Обучение")
  public void showEducationMenu() {
    mainPage.showEducationMenu();
  }

  @И("Нажать на категорию \"Подготовительные курсы\"")
  public void clickPreparatoryCourses() {
    guiceScoped.driver.findElement(By.xpath("//*[text()='Подготовительные курсы']")).click();
  }
}
