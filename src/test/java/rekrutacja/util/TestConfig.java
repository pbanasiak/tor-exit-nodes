package rekrutacja.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import rekrutacja.connector.TorProjectConnector;

@TestConfiguration
public class TestConfig {

  @Bean
  public TorProjectConnector torProjectConnector() throws Exception {
    TorProjectConnector torProjectConnector = mock(TorProjectConnector.class);
    when(torProjectConnector.getExitNodesString()).thenReturn(ExitNodesProvider.givenExitNodesString());
    return torProjectConnector;
  }
}
