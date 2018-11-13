package rekrutacja.logic;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rekrutacja.connector.TorProjectConnector;
import rekrutacja.model.ExitNode;
import rekrutacja.util.ConstantsAware;

@Component
@AllArgsConstructor
@Slf4j
public class ExitNodesLoader implements ConstantsAware {

  private TorProjectConnector torProjectConnector;
  private ExitNodesTransformer exitNodesTransformer;

  @Cacheable(CACHE_NAME)
  public Map<String, List<ExitNode>> getExitNodes() {
    log.info("Refreshing exit nodes has started");
    Map<String, List<ExitNode>> exitNodesMap = exitNodesTransformer
        .toExitNodesMap(torProjectConnector.getExitNodesString());
    log.info("Refreshing exit nodes has ended");
    return exitNodesMap;
  }

}
