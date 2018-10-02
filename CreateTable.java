import java.io.*;
import java.util.*;
public class CreateTable {
  Scanner scanner = new Scanner(System.in);
  BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
  Define d = new Define();
  CreateTable(String database_name, String table_name) {
    String path = "./databases/" + database_name + "/" + table_name;
    File file = new File(path);
    try {
      if (file.createNewFile()){
        d.log("touch " + path);
        CreateCulum(path);
      } else {
        d.error("そのテーブルは既に存在しています");
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  void CreateCulum(String path) {
    int culum_num;
    String input = null;  // 入力
    try {
      FileWriter file = new FileWriter(path);
      PrintWriter pw = new PrintWriter(new BufferedWriter(file));

      d.cyan("カラムの個数を入力してください > ");
      culum_num = scanner.nextInt();
      String[] culum = new String[culum_num];

      for (int i=0; i<culum_num; i++) {
        culum_num[i] = scanner.next();
      }

      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
