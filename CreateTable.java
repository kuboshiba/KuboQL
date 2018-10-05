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
    int culum_num, data_num;
    String input = null;  // 入力
    try {
      FileWriter file = new FileWriter(path);
      PrintWriter pw = new PrintWriter(new BufferedWriter(file));

      d.bold();
      d.yellow("\nカラムの個数を入力してください > ");
      culum_num = scanner.nextInt();

      String[] culum = new String[culum_num];
      int[] max_len = new int[culum_num];

      for (int i=0; i<culum_num; i++) {
        d.bold();
        d.white("カラム " + String.valueOf(i+1) + " > ");
        culum[i] = scanner.next();
        max_len[i] = culum[i].length() + 2;
      }

      d.bold();
      d.yellow("データの個数を入力してください > ");
      data_num = scanner.nextInt();

      String[][] data = new String[data_num + 3][culum_num];

      for (int i=0; i<culum_num; i++) data[1][i] = culum[i];

      for (int i=0; i<data_num; i++) {
        d.bold();
        d.yellow("\n【 データ " + String.valueOf(i+1) + " 】\n");
        for (int j=0; j<culum_num; j++) {
          d.bold();
          d.white(culum[j] + " > ");
          data[i+2][j] = scanner.next();
          if (data[i+2][j].length() + 2 > max_len[j]) max_len[j] = data[i+2][j].length() + 2;
        }
      }

      for (int i=0; i<culum_num; i++) data[0][i] = String.valueOf(max_len[i]);
      data[data_num+2][0] = "@eof";

      for (int i=0; i<data_num+3; i++) {
        for (int j=0; j<culum_num-1; j++) {
          pw.print(data[i][j]+",");
        } pw.println(data[i][culum_num-1]);
      }
      System.out.println();
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
