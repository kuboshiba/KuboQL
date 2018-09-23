import java.io.*;
public class CheckExistDatabases {
  boolean exist(String path) {
    boolean judge = true;
    File file = new File("./databases/" + path);
    if (!file.exists()) judge = false;
    return judge;
  }
}
