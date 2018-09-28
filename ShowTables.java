import java.io.*;
public class ShowTables {
  ShowTables(String path) {
    File file = new File(path);
    File files[] = file.listFiles();
    int i,j;
    int files_num = files.length;
    String temp1 = "Tables_in_" + file.getName();
    int file_len = temp1.length();

    for(i=0; i<files_num; i++) {
      if (file_len < files[i].getName().length()) file_len = files[i].getName().length();
    }

    System.out.print("+");
    for(i=0; i<file_len+2; i++) System.out.print("-"); // +2は両端の空白の分
    System.out.println("+");

    System.out.print("| " + temp1);
    for(i=0; i<file_len-temp1.length(); i++) System.out.print(" ");
    System.out.println(" |");

    System.out.print("+");
    for(i=0; i<file_len+2; i++) System.out.print("-"); // +2は両端の空白の分
    System.out.println("+");

    for(i=0; i<files_num; i++) {
      System.out.print("| " + files[i].getName());
      for(j=0; j<file_len-files[i].getName().length(); j++) System.out.print(" ");
      System.out.println(" |");
    }

    System.out.print("+");
    for(i=0; i<file_len+2; i++) System.out.print("-"); // +2は両端の空白の分
    System.out.println("+");

    System.out.println(" " + files.length + " rows in set" + "\n");
  }
}
