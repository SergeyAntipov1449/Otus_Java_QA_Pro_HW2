package otus.steps;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebElement;
import pages.CatalogPage;
import scoped.GuiceScoped;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ScenarioScoped
public class CatalogPageSteps {

  private WebElement requiredCoursePlate;
  private List<WebElement> coursePlates;
  @Inject
  private CatalogPage catalogPage;
  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Открыть страницу каталога курсов")
  public void openCatalogPage() {
    catalogPage.open();
    catalogPage.closeNotification();
  }

  @И("Найти курс по названию: {string}")
  public void getCourseByName(String requiredCourseName) {
    guiceScoped.requiredCourseName = requiredCourseName;
    requiredCoursePlate = catalogPage.getCourseByName(requiredCourseName);

  }

  @Если("Нажать на плашку найденного курса")
  public void clickOnCoursePlate() {
    requiredCoursePlate.click();
  }

  @И("Найти курсы начинающиеся {string} или позже")
  public void getCoursesStartingAfter(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate inputDate = LocalDate.parse(date, formatter);

    coursePlates = catalogPage.getDisplayedCourses().stream()
        .filter(course -> {
              LocalDate startDate = catalogPage.getCourseStartDate(course);
              return startDate.isAfter(inputDate) || startDate.equals(inputDate);
            }
        )
        .toList();
    if (coursePlates.isEmpty()) {
      throw new RuntimeException("Подходящих курсов не найдено");
    }
  }

  @Тогда("Выведены в консоль название и дату старта курсов")
  public void printCourseStartDateAndName() {
    this.coursePlates.stream()
        .forEach(course ->
            System.out.println(String.format("Имя курса %s - Дата старта %s", catalogPage.getCourseName(course), (catalogPage.getCourseStartDate(course)).toString())));
  }

  @Затем("Открыт каталог курсов")
  public void getDisplayedCourses() {
    coursePlates = catalogPage.getDisplayedCourses();
  }

  @Тогда("Вывести в консоль данные самого дорогого и дешового курсов")
  public void printPricesCourseExtremum() {
    catalogPage.printPricesCourseExtremum(coursePlates);
  }
}

