package rekrutacja.logic;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rekrutacja.exception.ExitNodesNotFound;
import rekrutacja.model.ExitNode;

@Component
@AllArgsConstructor
@Slf4j
public class ExitNodesRepository {

  private ExitNodesLoader exitNodesLoader;

  public List<ExitNode> find(String ipAddress) {
    log.info("Looking for exit node {}", ipAddress);
    return Optional.ofNullable(exitNodesLoader.getExitNodes().get(ipAddress))
        .orElseThrow(ExitNodesNotFound::new);
  }

  public List<ExitNode> getAll() {
    return exitNodesLoader.getExitNodes().values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }
}
