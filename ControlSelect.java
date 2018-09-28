import java.io.*;
public class ControlSelect {
  Define d = new Define();
  ControlSelect(String database, String[] cmd) {
    if(cmd.length == 3 && cmd[1].equals("*from")) SelectAll("./databases/" + database + "/" + cmd[2]);
    else if(cmd.length == 4 && cmd[1].equals("*") && cmd[2].equals("from")) SelectAll("./databases/" + database + "/" + cmd[3]);
    else if(cmd.length == 3 && cmd[1].equals("from")) d.error("select コマンドの使い方が間違っています help か \\h で参照してください");
  }

  void SelectAll(String path) {
    File file = new File(path);
    if (!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください");
    else PrintSelectAll(path);
  }

  void PrintSelectAll(String path) {
    try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
      String line;
      while((line = in.readLine()) != null) {
        String[] a = line.split(",");
        for(int i=0; i<a.length; i++) {
          System.out.println(a[i]);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1); // 0 以外は異常終了
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
