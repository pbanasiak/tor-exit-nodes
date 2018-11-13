package rekrutacja.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TorProjectConnector {

  private final String url;
  private final RestTemplate restTemplate;


  public TorProjectConnector(@Value("${tor.exit.addresses.url}") String url, RestTemplate restTemplate) {
    this.url = url;
    this.restTemplate = restTemplate;
  }

  public String getExitNodesString() {
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    return response.getBody();
  }

}
