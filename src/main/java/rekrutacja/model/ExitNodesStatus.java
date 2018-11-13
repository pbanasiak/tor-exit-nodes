package rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExitNodesStatus {

  @JsonProperty("tor_exit_nodes_count")
  @ApiModelProperty("Count of exit nodes, ip addresses of nodes can be duplicated")
  private Integer torExitNodesCount;
}
