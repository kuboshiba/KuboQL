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
              break;
            default:
              d.error("コマンドの使い方が間違っています help か \\h で参照してください");
          }
        } else d.error("コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      case "select":
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

      case "help":
      case "\\h":
        if (this.cmd.length == 1) new PrintTxtFile("./Help.kubota");
        else d.error("コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      case "exit":
        if (this.cmd.length == 1) {
          d.white("Bye.\n\n");
          System.exit(0);
        } else d.error("コマンドの使い方が間違っています help か \\h で参照してください");
        break;

      default:
        d.error("そのコマンドは存在しません  help か \\h で参照してください");
    }
  }

  //コマンド入力関数
  void InputLine() {
    d.bold();
    d.magenta("KuboQL [" + this.target + "] > ");
    this.input = scanner.nextLine();
    this.cmd = this.input.split("[\\s]+");  // "[\\s]+" <<< 問答無用で空白で区切る
  }
}
