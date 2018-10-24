import java.io.*;
import java.util.*;//scannerのためのインポート

public class MainActivity {
  Scanner scanner = new Scanner(System.in);//system.inはコマンドプロントへの入力。scannerは組み込み関数
  Define d = new Define();//Defineを参照

  String target = null;  //選択しているデータベース
  String input = null;   //入力文字列を格納
  String[] cmd = null;   //入力文字列を空白区切りで格納

  boolean judge;

  MainActivity() {//ファイル名と関数名が同じなのでこれが実行される(?)
    while(true) MainLoop();
  }

  //メインループ
  void MainLoop() {
    InputLine();//コマンドを取り込んで単語ごとに分割
    SearchCommand();
  }

  //コマンド検索 & 実行
  void SearchCommand() {
    switch (this.cmd[0]) {//入力されたコマンドの1単語目が・・・

      case "show"://showならば[1単語目]
        if (this.cmd.length == 2) {//コマンドに入力されたのが2単語か？

          switch (this.cmd[1]) {//入力されたコマンドの2単語目が..
            case "databases"://databasesなら[2単語目]
              new ShowDatabases();//ShowDatabasesのファイルを参照
              break;//case文の各分岐の最後にはbreak
            case "tables"://tableなら[2]
              if (this.target != null) {//targetがnullでなければ
                new ShowTables("./databases/" + this.target);//ShowTablesを参照。引数はtargetの場所
              }
              else d.error("use [データベース名] でデータベースを選択してください");//Defineの中のerror関数
              break;
            default://[2単語目]がいままでの選択肢に当てはまらなかった場合
              d.error("show コマンドの使い方が間違っています help か \\h で参照してください");//Defineの中のerror関数
          }//入力されたコマンド2単語目についてのswitch文が終了
        } else d.error("show コマンドの使い方が間違っています help か \\h で参照してください");//コマンドに入力されたのが2単語ではなかった場合Defineの中のError関数
        break;

      case "select"://selectならば[1]
        if (this.target == null) {
          d.error("use [データベース名] でデータベースを選択してください");//targetがNULLであればDefine中のerror関数
          break;//この先に進まないようにするためのbreak
        }
        if (this.cmd.length >= 3) {//入力されたコマンドが3単語以上なら
          new ControlSelect(this.target, this.cmd);//ｃontrolselect参照.引数は（指定されているデータベースの名前,入力されたコマンドを区切ったやつ）
        }
        else d.error("select コマンドの使い方が間違っています help か \\h で参照してください");//4以上の単語が入力されていた場合はエラー
        break;//switch-caseの選択肢の動作終了

      case "use"://useのとき「1」
        if (this.cmd.length == 2) {//入力されたのが2単語か？
          CheckExistDatabases checkExistDatabases = new CheckExistDatabases();//checkexistdatabasesを参照しながら実行
          if(checkExistDatabases.exist(this.cmd[1]) == true ) {
            this.target = this.cmd[1];//targetを入力された2単語目に変更
            d.log("Database changed.");//define中の関数
          }
          else d.error("そのデータベースは存在しません show databases で確認してください");//ファイルが存在しなかった時のエラー
        }
        break;//switch-caseの選択肢動作終了

      case "create"://createの場合[1]
        if (this.cmd.length == 3) {//入力されたのが3単語の場合
          switch (this.cmd[1]) {//入力された2単語目について分岐
            case "database"://databaseの場合[2]
              File file = new File("./databases/" + this.cmd[2]);//ファイル入出力に関する組み込み関数を使用しながら実行
              if (!file.exists()) {//存在しなければ
                file.mkdir();//作れ
                d.log("mkdir ./databases/" + this.cmd[2]);//コマンドプロント上で表示
              } else d.error("そのデータベースは既に存在しています");//もともと存在していた場合はエラー
              break;//選択肢終了
            case "table"://tableの場合[2]
              if (this.target != null) {
                new CreateTable(this.target, this.cmd[2]);//targetがNULLでない場合はcreatetableを実行
              } else d.error("use [データベース名] でデータベースを選択してください");
              break;//選択肢終了
            default://switchに選択肢が存在しなかった場合
              d.error("create コマンドの使い方が間違っています help か \\h で参照してください");
          }//2単語目におけるswitch終了
        } else {
          d.error("create コマンドの使い方が間違っています help か \\h で参照してください");//入力された単語が3でなかった場合のエラー
        }
        break;//選択肢終了

      case "help"://helpの場合[1](breakがないので垂れ流し)
      case "\\h"://「\\h」が入力された場合とhelpの場合[1]
        if (this.cmd.length == 1) new PrintTxtFile("./Help.kubota");//入力が1単語だった場合はPrintTxtFileの実行
        else d.error("help コマンドの使い方が間違っています help か \\h で参照してください");//1単語以外だった場合のエラー
        break;//選択肢終了

      case "exit"://exitの場合[1]
        if (this.cmd.length == 1) {
          d.white("Bye.\n\n");//入力が1単語の場合Byeを出力後
          System.exit(0);//プログラムの終了
        } else d.error("exit コマンドの使い方が間違っています help か \\h で参照してください");//入力が1単語ではなかった場合のエラー
        break;//選択肢終了
        
       case "sort"://sortの場合[1]
       	if(this.cmd.length==3){//入力された単語が3か？
       		if(this.target!=null){//targetが指定されているか？
       			new sort(this.target,this.cmd[1],this.cmd[2]);
       		}else{
       			d.error("use [データベース名] でデータベースを選択してください");
       		}
       	}else{
       		d.error("sort コマンドの使い方が間違っています help か \\h で参照してください");
       	}
       	break;
       case "delete":
       	if(this.cmd.length==7 && this.cmd[1].equals("from") && this.cmd[3].equals("where")){
       		if(this.target!=null){
       			new delete(this.target,this.cmd[2],this.cmd[4],this.cmd[5],this.cmd[6]);
       		}else{
       			d.error("use[データベース名]でデータベースを選択してください");
       		}
       	}else{
       		d.error("deleteコマンドの使い方が間違っています helpか\\ｈで参照してください");
       	}
       	break;
       	
       case "drop":
       		if(this.cmd.length==3 && this.cmd[1].equals("table")){
       			if(this.target!=null){
       				String droppath="./databases/"+this.target+"/"+this.cmd[2];
       				new drop(droppath);
       			}else{
       				d.error("use [データベース名]でデータベースを選択してください");
       			}
       		}else if(this.cmd.length==3 && this.cmd[1].equals("database")){
       			String fdroppath="./databases/"+this.cmd[2];
       			if(this.cmd[2].equals(this.target)){
       				this.target=null;
       			}
       			new fdrop(fdroppath);
       		}else{
       			d.error("dropコマンドの使い方が間違っています help　か \\h で参照してください");
       		}
       		break;	

      default://入力された1単語目がどの分岐にも当てはまらなかった場合
        d.error("そのコマンドは存在しません  help か \\h で参照してください");
    }
  }

  //コマンド入力関数
  void InputLine() {
    d.bold();//define関数の中のやつ
    d.magenta("KuboQL [");//色指定＋文字出力
    d.bold();
    if(this.target == null) d.red(this.target);//targetがNULLであれば赤色でtarget(NULL)を出力
    else d.green(this.target);//そうでなければ緑でtarget出力
    d.bold();
    d.magenta("] > ");//出力
    this.input = scanner.nextLine();//inputの中にコマンドプロントで入力された1行を取り込む
    this.cmd = this.input.split("[\\s]+");  // "[\\s]+" <<< 問答無用で空白で区切る
  }
}
