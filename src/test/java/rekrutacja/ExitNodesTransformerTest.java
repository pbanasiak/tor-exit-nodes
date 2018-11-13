package rekrutacja;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rekrutacja.logic.ExitNodesTransformer;
import rekrutacja.model.ExitNode;
import rekrutacja.util.ExitNodesProvider;

@RunWith(MockitoJUnitRunner.class)
public class ExitNodesTransformerTest {

  @InjectMocks
  private ExitNodesTransformer exitNodesTransformer;

  @Test
  public void shouldTransformExitNodesFromFile() throws Exception {
    //given
    String exitNodesStrig = ExitNodesProvider.givenExitNodesString();
    String exitNodeAddress = "195.176.3.20";

    //when
    Map<String, List<ExitNode>> exitNodesMap = exitNodesTransformer.toExitNodesMap(exitNodesStrig);

    //then
    assertThat(exitNodesMap).isNotNull();
    assertThat(exitNodesMap.size()).isEqualTo(859);
    List<ExitNode> totalExitNodes = exitNodesMap.values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
    assertThat(totalExitNodes.size()).isEqualTo(920);

    List<ExitNode> firstExitNodesList = exitNodesMap.get(exitNodeAddress);
    assertThat(firstExitNodesList.size()).isEqualTo(2);
    ExitNode firstExitNode = firstExitNodesList.get(0);
    assertThat(firstExitNode.getId()).isEqualTo("08CE3DBFDAA27DB6C044A677AF68D7235C2AFC85");
    assertThat(firstExitNode.getPublished()).isEqualTo("2018-10-11 04:55:56");
    assertThat(firstExitNode.getLastStatus()).isEqualTo("2018-10-11 06:03:41");
    assertThat(firstExitNode.getIpAddress()).isEqualTo(exitNodeAddress);
    assertThat(firstExitNode.getDate()).isEqualTo("2018-10-11 06:09:30");

  }


}

