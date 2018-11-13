package rekrutacja;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rekrutacja.connector.TorProjectConnector;
import rekrutacja.logic.ExitNodesLoader;
import rekrutacja.logic.ExitNodesTransformer;

@RunWith(MockitoJUnitRunner.class)
public class ExitNodesLoaderTest {

  @InjectMocks
  private ExitNodesLoader exitNodesLoader;
  @Mock
  private TorProjectConnector torProjectConnector;
  @Mock
  private ExitNodesTransformer exitNodesTransformer;

  @Test
  public void shouldFetchExitNodesAndTransform() {
    //given
    String exitNodesString = "String";
    when(torProjectConnector.getExitNodesString()).thenReturn(exitNodesString);

    //when
    exitNodesLoader.getExitNodes();

    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
    verify(exitNodesTransformer, times(1)).toExitNodesMap(exitNodesString);
  }


}

