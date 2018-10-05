public class Define {
  String black   = "\u001b[30m";
  String red     = "\u001b[31m";
  String green   = "\u001b[32m";
  String yellow  = "\u001b[33m";
  String blue    = "\u001b[34m";
  String magenta = "\u001b[35m";
  String cyan    = "\u001b[36m";
  String white   = "\u001b[37m";

  String bold   = "\u001b[1m";
  String reset   = "\u001b[0m";

  void bold() { System.out.print(bold); }
  void reset() { System.out.print(reset); }

  void log(String msg) { System.out.println(bold + cyan + "Log: " + msg + reset); }
  void error(String msg) { System.out.println(bold + red + "Error: " + msg + reset); }
  void message(String msg) { System.out.println(bold + blue + "Message: " + msg + reset); }

  void black(String msg) { System.out.print(black + msg + reset); }
  void red(String msg) { System.out.print(red + msg + reset); }
  void green(String msg) { System.out.print(green + msg + reset); }
  void yellow(String msg) { System.out.print(yellow + msg + reset); }
  void blue(String msg) { System.out.print(blue + msg + reset); }
  void magenta(String msg) { System.out.print(magenta + msg + reset); }
  void cyan(String msg) { System.out.print(cyan + msg + reset); }
  void white(String msg) { System.out.print(white + msg + reset); }

  void ln() { System.out.println(); }
  void ln(int cnt) { for(int i=0; i<cnt; i++) System.out.println(); }
}
