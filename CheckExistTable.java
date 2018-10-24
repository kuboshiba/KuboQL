import java.io.*;
public class CheckExistTable {
    Define d = new Define();

    boolean exist(String path) {
        File file = new File(path);
        return file.exists();
    }

    boolean exist_column(String path, String[] column_list) {
        boolean flag = false;
        File file = new File(path);
        int cnt = 1;
        try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            String[] line_split;
            while((line = in.readLine()) != null) {
                if (cnt == 2) {
                    line_split = line.split(",");
                    for(int i=0; i<column_list.length; i++) {
                        flag = false;
                        for(int j=0; j<line_split.length; j++) {
                            if (line_split[j].equals(column_list[i])) {
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            d.error("Not found " + column_list[i]);
                            break;
                        }
                    }
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
        return flag;
    }
}