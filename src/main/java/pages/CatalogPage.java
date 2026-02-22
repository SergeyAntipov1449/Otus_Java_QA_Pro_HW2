package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scoped.GuiceScoped;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Path("/catalog/courses/")
public class CatalogPage extends AbsBasePage<CatalogPage> {
  @Inject
  public CatalogPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public WebElement getCourseByName(String courseName) {
    List<WebElement> courseNamesList = driver.findElements(By.cssSelector("section .sc-zzdkm7-0"));
    Optional<WebElement> searchResult = courseNamesList.stream()
        .filter(coursePlate -> coursePlate.findElement(By.cssSelector("h6 div")).getText()
            .equalsIgnoreCase(courseName))
        .findFirst();
    if (!searchResult.isEmpty()) {
      return searchResult.get();
    }
    throw new RuntimeException(String.format("Курс %s не найден", courseName));
  }

  public List<WebElement> getDisplayedCourses() {
    List<WebElement> courses;
    return courses = driver.findElements(By.cssSelector("section .sc-zzdkm7-0"));
  }

  public LocalDate getCourseStartDate(WebElement coursePlate) {
    String startDate = coursePlate.findElement(By.cssSelector("div.sc-1x9oq14-0 div.sc-hrqzy3-1"))
        .getText().split("·")[0]
        .trim();
    String pattern = "dd MMMM, yyyy";
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(new Locale("ru"));

      return LocalDate.parse(startDate, formatter);
    } catch (DateTimeParseException e) {
      throw new RuntimeException(e);
    }
  }

  public String getCourseName(WebElement coursePlate) {
    return coursePlate.findElement(By.cssSelector("h6 div")).getText();
  }

  public void printPricesCourseExtremum(List<WebElement> coursePlates) {
    TreeMap<Integer, String> coursesData = new TreeMap<>();
    for (WebElement course : coursePlates) {
      Map.Entry<Integer, String> entry = this.getCoursePageData(course).firstEntry();
      if (entry != null && !coursesData.containsKey(entry.getKey())) {
        coursesData.put(entry.getKey(), entry.getValue());
      }
    }
    Map.Entry<Integer, String> minPriceCourse = coursesData.firstEntry();
    Map.Entry<Integer, String> maxPriceCourse = coursesData.lastEntry();

    System.out.println(String.format("Самый дешёвый курс: %s - %d руб.", minPriceCourse.getValue(), minPriceCourse.getKey()));
    System.out.println(String.format("Самый дорогой курс: %s - %d руб.", maxPriceCourse.getValue(), maxPriceCourse.getKey()));
  }

  public TreeMap<Integer, String> getCoursePageData(WebElement coursePlate) {
    TreeMap<Integer, String> result = new TreeMap<>();
    String coursePageURL = coursePlate.getAttribute("href");
    try {
      Document document = Jsoup.connect(coursePageURL).get();

      String name = document.select(".sc-153sikp-7").text();
      int price = Integer.parseInt(document.select(".sc-153sikp-11").text().split(" ")[0]);

      result.put(price, name);

    } catch (Exception e) {
      return new TreeMap<>();
    }
    return result;
  }
}
