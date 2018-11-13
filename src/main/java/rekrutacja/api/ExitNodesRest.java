package rekrutacja.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rekrutacja.logic.ExitNodesRepository;
import rekrutacja.model.ExitNode;
import rekrutacja.model.ExitNodesStatus;

@RestController
@AllArgsConstructor
public class ExitNodesRest {

  private ExitNodesRepository exitNodesRepository;

  @ApiOperation("Verifies if provided ipAddress is tor exit node")
  @ApiResponses({@ApiResponse(code = 200, message = "Provided IP is a Tor exit node"),
                    @ApiResponse(code = 404, message = "Provided IP is not a Tor exit node")})
  @RequestMapping(path = "/{ipAddress:[\\d.]+}", method = RequestMethod.HEAD)
  public void getMetaInfo(@ApiParam(value = "IPv4 address to be verified") @PathVariable String ipAddress) {
    exitNodesRepository.find(ipAddress);
  }

  @ApiOperation("Verifies if provided ipAddress is tor exit node and returns exit node details")
  @ApiResponses({@ApiResponse(code = 200, message = "Provided IP is a Tor exit node"),
                    @ApiResponse(code = 404, message = "Provided IP is not a Tor exit node")})
  @GetMapping("/{ipAddress:[\\d.]+}")
  public List<ExitNode> getDetails(@ApiParam(value = "IPv4 address to be verified") @PathVariable String ipAddress) {
    return exitNodesRepository.find(ipAddress);
  }

  @ApiOperation("Verifies if app is working")
  @ApiResponses({@ApiResponse(code = 200, message = "App is working correctly")})
  @RequestMapping(path = "/status", method = RequestMethod.HEAD)
  public void getStatusMetaInfo() {

  }

  @ApiOperation("Returns app status and exit nodes count")
  @ApiResponses({@ApiResponse(code = 200, message = "App is working correctly")})
  @GetMapping(path = "/status")
  public ExitNodesStatus getStatusDetails() {
    return
        ExitNodesStatus.builder()
            .torExitNodesCount(exitNodesRepository.getAll().size())
            .build();
  }
}
