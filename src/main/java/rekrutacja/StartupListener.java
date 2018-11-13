package rekrutacja;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import rekrutacja.logic.ExitNodesLoader;

@Component
@AllArgsConstructor
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

  private ExitNodesLoader exitNodesLoader;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    exitNodesLoader.getExitNodes();
  }

}
