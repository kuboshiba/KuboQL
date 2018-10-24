import java.io.*;
public class DescTable {
    Define d = new Define();
    DescTable (String path) {
        int cnt = 1;
        try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            String[] line_split;
            while((line = in.readLine()) != null) {
                if (cnt == 2) {
                    line_split = line.split(",");
                    DescPrint(path, line_split);
                    break;
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

    void DescPrint (String path, String[] column) {
        String[] temp = path.split("/");
        temp[3] = "Columns_in_" + temp[3];
        int max_len = temp[3].length();
        for (int i=0; i<column.length; i++) {
            if (max_len < column[i].length()) max_len = column[i].length();
        }
        System.out.print("+");
        for(int i=0; i<max_len+2; i++) System.out.print("-");
        System.out.println("+");

        System.out.print("| ");
        System.out.print(temp[3]);
        for (int j=0; j<max_len-temp[3].length(); j++) System.out.print(" ");
        System.out.println(" |");

        System.out.print("+");
        for(int i=0; i<max_len+2; i++) System.out.print("-");
        System.out.println("+");

        for (int i=0; i<column.length; i++) {
            System.out.print("| ");
            System.out.print(column[i]);
            for (int j=0; j<max_len-column[i].length(); j++) System.out.print(" ");
            System.out.println(" |");
        }

        System.out.print("+");
        for(int i=0; i<max_len+2; i++) System.out.print("-");
        System.out.println("+");

        System.out.println(" " + column.length + " rows in set" + "\n");
    }
}