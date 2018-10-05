import java.io.*;

public class StartUp {
  Define d = new Define();
  StartUp() {
    d.log("program starting...");
    File file = new File("./databases");
    if (!file.exists()) {
      d.log("mkdir ./databases");
      file.mkdir();
    }
    d.message("コマンドの使い方を help か \\h で確認して下さい\n");
  }
}
