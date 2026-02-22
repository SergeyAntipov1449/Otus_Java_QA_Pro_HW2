package otus.steps;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.CoursePage;
import scoped.GuiceScoped;

@ScenarioScoped
public class CoursePageSteps {
  @Inject
  private CoursePage coursePage;
  @Inject
  private GuiceScoped guiceScoped;

  @Тогда("Открыта страница выбранного курса")
  public void checkCoursePageByName() {
    Assertions.assertEquals(guiceScoped.requiredCourseName, coursePage.getCourseName());
  }

}
