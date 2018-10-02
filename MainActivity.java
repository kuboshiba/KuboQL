import java.io.*;
import java.util.*;

public class MainActivity {
  Scanner scanner = new Scanner(System.in);
  Define d = new Define();

  String target = null;  //選択しているデータベース
  String input = null;   //入力文字列を格納
  String[] cmd = null;   //入力文字列を空白区切りで格納

  boolean judge;

  MainActivity() {
    while(true) MainLoop();
  }

  //メインループ
  void MainLoop() {
    InputLine();
    SearchCommand();
  }

  //コマンド検索 & 実行
  void SearchCommand() {
    switch (this.cmd[0]) {

      case "show":
        if (this.cmd.length == 2) {

          switch (this.cmd[1]) {
            case "databases":
              new ShowDatabases();
              break;
            case "tables":
              if (this.target != null) {
                new ShowTables("./databases/" + this.target);
              }
              else d.error("use [データベース名] でデータベースを選択してください");
              break;
            default:
              d.error("show コマンドの使い方が間違っています help か \\h で参照してください");
          }
        } else d.error("show コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      case "select":
        if (this.target == null) {
          d.error("use [データベース名] でデータベースを選択してください");
          break;
        }
        if (this.cmd.length >= 3) {
          new ControlSelect(this.target, this.cmd);
        }
        else d.error("select コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      case "use":
        if (this.cmd.length == 2) {
          CheckExistDatabases checkExistDatabases = new CheckExistDatabases();
          if(checkExistDatabases.exist(this.cmd[1]) == true ) {
            this.target = this.cmd[1];
            d.log("Database changed.");
          }
          else d.error("そのデータベースは存在しません show databases で確認してください");
        }
        break;

      case "create":
        if (this.cmd.length == 3) {
          switch (this.cmd[1]) {
            case "database":
              File file = new File("./databases/" + this.cmd[2]);
              if (!file.exists()) {
                file.mkdir();
                d.log("mkdir ./databases/" + this.cmd[2]);
              } else d.error("そのデータベースは既に存在しています");
              break;
            case "table":
              if (this.target != null) {
                new CreateTable(this.target, this.cmd[2]);
              } else d.error("use [データベース名] でデータベースを選択してください");
              break;
            default:
              d.error("create コマンドの使い方が間違っています help か \\h で参照してください");
          }
        } else {
          d.error("create コマンドの使い方が間違っています help か \\h で参照してください");
        }
        break;

      case "help":
      case "\\h":
        if (this.cmd.length == 1) new PrintTxtFile("./Help.kubota");
        else d.error("help コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      case "exit":
        if (this.cmd.length == 1) {
          d.white("Bye.\n\n");
          System.exit(0);
        } else d.error("exit コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      default:
        d.error("そのコマンドは存在しません  help か \\h で参照してください");
    }
  }

  //コマンド入力関数
  void InputLine() {
    d.bold();
    d.magenta("KuboQL [");
    d.bold();
    if(this.target == null) d.yellow(this.target);
    else d.green(this.target);
    d.bold();
    d.magenta("] > ");
    this.input = scanner.nextLine();
    this.cmd = this.input.split("[\\s]+");  // "[\\s]+" <<< 問答無用で空白で区切る
  }
}
