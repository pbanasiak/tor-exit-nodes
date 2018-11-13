package rekrutacja.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExitNode implements Serializable {

  @ApiModelProperty("Exit Node Id")
  private String id;
  @ApiModelProperty("Exit Node published date-time")
  private String published;
  @ApiModelProperty("Exit Node last status date-time")
  private String lastStatus;
  @ApiModelProperty("Exit Node ip address")
  private String ipAddress;
  private String date;
}
