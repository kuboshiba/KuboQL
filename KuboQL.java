public class KuboQL {
  public static void main(String[] args) {
    Define d = new Define();
    d.log("Program starting...");
    d.message("コマンドの使い方を help か \\h で確認して下さい\n");
    new StartUp();
    new MainActivity();
  }
}
