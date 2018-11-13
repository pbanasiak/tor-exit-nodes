package rekrutacja.logic;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rekrutacja.util.ConstantsAware;

@Component
@AllArgsConstructor
@Slf4j
public class ExitNodesRefreshTask implements ConstantsAware {

  private ExitNodesLoader exitNodesLoader;
  private CacheManager cacheManager;

  @Scheduled(cron = "${cache.refresh.cron}")
  public void refreshCache() {
    log.info("Exit nodes refresh task has started");
    cacheManager.getCache(CACHE_NAME).clear();
    exitNodesLoader.getExitNodes();
    log.info("Exit nodes refresh task has ended");
  }
}
