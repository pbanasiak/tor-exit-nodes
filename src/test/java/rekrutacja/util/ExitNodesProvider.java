package rekrutacja.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

public class ExitNodesProvider {

  private static final String EXIT_ADDRESSES_DOCUMENT_FILE_NAME = "classpath:exit-addresses";

  public static String givenExitNodesString() throws Exception {
    File file = ResourceUtils.getFile(EXIT_ADDRESSES_DOCUMENT_FILE_NAME);
    return FileUtils.readFileToString(file, "UTF-8");
  }
}
