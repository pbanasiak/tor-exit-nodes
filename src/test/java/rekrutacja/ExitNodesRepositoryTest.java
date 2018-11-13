package rekrutacja;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rekrutacja.exception.ExitNodesNotFound;
import rekrutacja.logic.ExitNodesLoader;
import rekrutacja.logic.ExitNodesRepository;
import rekrutacja.model.ExitNode;

@RunWith(MockitoJUnitRunner.class)
public class ExitNodesRepositoryTest {

  private static final String IP_ADDRESS = "IP_ADDRESS";
  @InjectMocks
  private ExitNodesRepository exitNodesRepository;
  @Mock
  private ExitNodesLoader exitNodesLoader;

  @Test
  public void shouldReturnExitNodesByIpAddress() {
    //given
    when(exitNodesLoader.getExitNodes()).thenReturn(new HashMap<>());

    //when
    assertThatThrownBy(() -> exitNodesRepository.find(IP_ADDRESS)).isInstanceOf(ExitNodesNotFound.class);

    //then
    verify(exitNodesLoader, times(1)).getExitNodes();
  }

  @Test
  public void shouldReturnAllExitNodes() {
    //given
    Map<String, List<ExitNode>> exitNodesMap = new HashMap<>();
    List<ExitNode> exitNodeList = new ArrayList<>();
    exitNodeList.add(new ExitNode());
    exitNodeList.add(new ExitNode());
    exitNodesMap.put(IP_ADDRESS, exitNodeList);
    when(exitNodesLoader.getExitNodes()).thenReturn(exitNodesMap);

    //when
    List<ExitNode> result = exitNodesRepository.getAll();

    //then
    verify(exitNodesLoader, times(1)).getExitNodes();
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  public void shouldThrowErrorWhenExitNodeNotFound() {
    //given
    Map<String, List<ExitNode>> exitNodesMap = new HashMap<>();
    List<ExitNode> exitNodeList = new ArrayList<>();
    exitNodesMap.put(IP_ADDRESS, exitNodeList);
    when(exitNodesLoader.getExitNodes()).thenReturn(exitNodesMap);

    //when
    List<ExitNode> result = exitNodesRepository.find(IP_ADDRESS);

    //then
    verify(exitNodesLoader, times(1)).getExitNodes();
    assertThat(result).isEqualTo(exitNodeList);

  }


}

