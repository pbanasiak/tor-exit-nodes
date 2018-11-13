package rekrutacja;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rekrutacja.connector.TorProjectConnector;
import rekrutacja.model.ExitNode;
import rekrutacja.util.TestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
public class ExitNodesRestTest {

  private static final String URL = "/{ipAddress}";
  private static final String STATUS_URL = "/status";
  private static final String EXIT_NODE_ADDRESS = "195.176.3.20";
  private static final String FAKE_NODE_ADDRESS = "666.19.111.147";
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;
  @Autowired
  private TorProjectConnector torProjectConnector;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void shouldReturn200ForMetaInfoRequestWhenExitNodeExists() throws Exception {
    //given

    //when
    MvcResult mvcResult = mockMvc.perform(head(URL, EXIT_NODE_ADDRESS))
        .andExpect(status().isOk())
        .andReturn();

    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
    MockHttpServletResponse result = mvcResult.getResponse();
    assertThat(result.getContentAsString()).isEmpty();
  }

  @Test
  public void shouldReturn404ForMetaInfoRequestWhenExitNodeDoesNotExist() throws Exception {
    //given
    //when
    MvcResult mvcResult = mockMvc.perform(head(URL, FAKE_NODE_ADDRESS))
        .andExpect(status().isNotFound())
        .andReturn();

    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
    MockHttpServletResponse result = mvcResult.getResponse();
    assertThat(result.getContentAsString()).isEmpty();
  }

  @Test
  public void shouldReturn200ForDetailsRequestWhenExitNodeExists() throws Exception {
    //given
    //when
    MvcResult mvcResult = mockMvc.perform(get(URL, EXIT_NODE_ADDRESS))
        .andExpect(status().isOk())
        .andReturn();

    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
    ExitNode[] exitNodes = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ExitNode[].class);
    assertThat(exitNodes.length).isEqualTo(2);
    assertThat(exitNodes[0].getIpAddress()).isEqualTo(EXIT_NODE_ADDRESS);
    assertThat(exitNodes[1].getIpAddress()).isEqualTo(EXIT_NODE_ADDRESS);
  }

  @Test
  public void shouldReturn404ForDetailsRequestWhenExitNodeDoesNotExist() throws Exception {
    //given
    //when
    MvcResult mvcResult = mockMvc.perform(get(URL, FAKE_NODE_ADDRESS))
        .andExpect(status().isNotFound())
        .andReturn();

    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
    MockHttpServletResponse result = mvcResult.getResponse();
    assertThat(result.getContentAsString()).isEmpty();
  }

  @Test
  public void shouldCacheExitNodesResponse() throws Exception {
    //given

    //when
    mockMvc.perform(head(URL, EXIT_NODE_ADDRESS))
        .andExpect(status().isOk())
        .andReturn();
    mockMvc.perform(head(URL, EXIT_NODE_ADDRESS))
        .andExpect(status().isOk())
        .andReturn();
    //then
    verify(torProjectConnector, times(1)).getExitNodesString();
  }

  @Test
  public void shouldReturnStatusMetaInfo() throws Exception {
    //given

    //when
    MvcResult mvcResult = mockMvc.perform(head(STATUS_URL))
        .andExpect(status().isOk())
        .andReturn();

    //then
    MockHttpServletResponse result = mvcResult.getResponse();
    assertThat(result.getContentAsString()).isEmpty();
  }

  @Test
  public void shouldReturnStatusDetails() throws Exception {
    //given

    //when
    mockMvc.perform(get(STATUS_URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.tor_exit_nodes_count").value(920));

    //then
  }
}
