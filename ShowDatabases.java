import java.io.*;
public class ShowDatabases {
  ShowDatabases() {
    File file = new File("./databases");
    File files[] = file.listFiles();
    int len = 0, n;

    for (int i=0; i<files.length; i++) {
      if (len < files[i].getName().length()) len = files[i].getName().length();
    }

    len = " Database".length() + Math.abs(" Database".length()-len) + 2;

    System.out.print("+");
    for (int i=0; i<len; i++) System.out.print("-");
    System.out.println("+");

    System.out.print("| Database");
    for(int i=0; i<len-"| Database".length() + 1; i++) System.out.print(" ");
    System.out.println("|");

    System.out.print("+");
    for (int i=0; i<len; i++) System.out.print("-");
    System.out.println("+");

    for (int i=0; i<files.length; i++) {
      System.out.print("| " + files[i].getName());
        n = len - files[i].getName().length();
      for(int j=0; j<n-1; j++) System.out.print(" ");
      System.out.println("|");
    }

    System.out.print("+");
    for (int i=0; i<len; i++) System.out.print("-");
    System.out.println("+");

    System.out.println(files.length + " rows in set");
  }
}
