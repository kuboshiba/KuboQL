import java.io.*;
public class PrintTxtFile {
  PrintTxtFile(String path) {
    try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
      String line;
      while((line = in.readLine()) != null) System.out.println(line);
      System.out.println();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1); // 0 以外は異常終了
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
