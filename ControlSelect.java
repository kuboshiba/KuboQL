import java.io.*;
public class ControlSelect {
  Define d = new Define();
  ControlSelect(String database, String[] cmd) {
    if(cmd.length == 3 && cmd[1].equals("*from")) SelectAll("./databases/" + database + "/" + cmd[2]);
    else if(cmd.length == 4 && cmd[1].equals("*") && cmd[2].equals("from")) SelectAll("./databases/" + database + "/" + cmd[3]);
  	else if(cmd.length == 4 && cmd[2].equals("from")) new SelectColumn("./databases/" + database + "/" + cmd[3], cmd[1]);			//追加
    else if(cmd.length == 3 && cmd[1].equals("from")) d.error("select コマンドの使い方が間違っています help か \\h で参照してください");
  }

  void SelectAll(String path) {
    File file = new File(path);
    if (!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください");
    else PrintSelectAll(path);
  }

  void PrintSelectAll(String path) {
    try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
      int cnt = 0, n = 0, i, j;  // 読み込み回数カウント
      String line;  // 1行単位で読み込み
      String[] line_split;  // 読み込んだ1行を分割
      int[] culum_len = new int[50];  // カラム数は最大50個まで

      while((line = in.readLine()) != null) {  //  1行ずつ読み込み
        line_split = line.split(",");  //  ","区切りで格納
        if(line_split[0].equals("@eof")) {
          System.out.print("+");
          for(i=0; i<n; i++) {
            for(j=0; j<culum_len[i]; j++) System.out.print("-");
            System.out.print("+");
          }
          System.out.println();
          System.out.println(" " + String.valueOf(cnt-2) + " rows in set" + "\n");
          break;
        }
        if (cnt == 0 ) {
          n = line_split.length;  //  文字数格納
          for(i=0; i<line_split.length; i++) culum_len[i] = Integer.parseInt(line_split[i]);
          System.out.print("+");
          for(i=0; i<line_split.length; i++) {
            for(j=0; j<culum_len[i]; j++) System.out.print("-");
            System.out.print("+");
          }
          System.out.println(); //  ここまで最初の行(線)
        }  else {
          for(i=0; i<line_split.length; i++) {
            System.out.print("| " + line_split[i]);
            int temp = culum_len[i] - line_split[i].length() - 1;
            for(j=0; j<temp; j++) System.out.print(" ");  //  左詰め
          }
          System.out.println("|");  //  ここまで1行出力 
          if(cnt == 1) {
            System.out.print("+");
            for(i=0; i<line_split.length; i++) {
              for(j=0; j<culum_len[i]; j++) System.out.print("-");
              System.out.print("+");
            }
            System.out.println();  //  ここまで区切り線
          }
        }
        cnt++;
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
