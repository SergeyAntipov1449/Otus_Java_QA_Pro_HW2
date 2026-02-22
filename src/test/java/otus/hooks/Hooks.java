package otus.hooks;


import com.google.inject.Inject;
import io.cucumber.java.After;
import scoped.GuiceScoped;

public class Hooks {
  @Inject
  private GuiceScoped guiceScoped;

  @After
  public void init() {
    if (guiceScoped.driver != null) {
      guiceScoped.driver.close();
    }
  }
}
