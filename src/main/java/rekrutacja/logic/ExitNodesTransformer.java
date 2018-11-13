package rekrutacja.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import rekrutacja.model.ExitNode;

@Component
public class ExitNodesTransformer {

  private static final String DATE_TIME_PATTERN = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
  private static final String FIRST_LINE_PATTERN = "ExitNode\\s(?<id>\\w+)\\s+";
  private static final String SECOND_LINE_PATTERN = "Published\\s(?<published>" + DATE_TIME_PATTERN + ")\\s+";
  private static final String THIRD_LINE_PATTERN = "LastStatus\\s(?<lastStatus>" + DATE_TIME_PATTERN + ")\\s+";
  private static final String FOURTH_LINE_PATTERN =
      "ExitAddress\\s(?<ipAddress>.+)\\s(?<date>" + DATE_TIME_PATTERN + ")";
  private static final Pattern DOCUMENT_PATTERN = Pattern.compile(
      FIRST_LINE_PATTERN + SECOND_LINE_PATTERN + THIRD_LINE_PATTERN + FOURTH_LINE_PATTERN
  );

  public Map<String, List<ExitNode>> toExitNodesMap(String exitNodesString) {
    Map<String, List<ExitNode>> exitNodesMap = new HashMap<>();
    for (Matcher matcher = DOCUMENT_PATTERN.matcher(exitNodesString); matcher.find(); ) {
      String ipAddress = matcher.group("ipAddress");
      ExitNode exitNode = buildExitNode(matcher, ipAddress);
      if (exitNodesMap.containsKey(ipAddress)) {
        exitNodesMap.get(ipAddress).add(exitNode);
      } else {
        List<ExitNode> exitNodes = new ArrayList<>();
        exitNodes.add(exitNode);
        exitNodesMap.put(ipAddress, exitNodes);
      }
    }
    return exitNodesMap;
  }

  private ExitNode buildExitNode(Matcher matcher, String ipAddress) {
    return ExitNode.builder()
        .id(matcher.group("id"))
        .published(matcher.group("published"))
        .lastStatus(matcher.group("lastStatus"))
        .ipAddress(ipAddress)
        .date(matcher.group("date"))
        .build();
  }
}
