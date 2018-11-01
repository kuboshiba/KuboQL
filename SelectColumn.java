import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SelectColumn {
	Define d = new Define();  // エラー出力用
	
	SelectColumn(String path, String cmd) {
		File file = new File(path);
		if(!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください");
		else PrintSelectColumn(path, cmd);
	}
	
	void PrintSelectColumn(String path, String cmd){
		try(BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {
			int cnt = 0, n = 0, i, j, nocolumn = 0, output = 0;  // 読み込み回数カウント
			String line;  // 1行単位で格納
			String[] line_split;  // 読み込んだ1行を分割して格納
			String[] line_column = new String[50];  // 1行目(カラム)を格納
			int[] column_num = new int[50];
			int[] column_len = new int[50];  // カラム数は最大50個まで
			String cmd_split[];
			
			cmd_split = cmd.split(",");
//		for(i=0;i<2;i++)System.out.println(cmd_split[i]);
			
			all_loop:
			while((line = in.readLine()) != null) {  // 1行ずつ読み込み
				line_split = line.split(",");  // ","区切りで格納
				
				if(line_split[0].equals("@eof")) {  // 行の最初の単語が@eofのとき(最後の行)
					System.out.print("+");
					for(i=0; i<output; i++)	{
						for(j=0; j<column_len[column_num[i]]; j++) System.out.print("-");
						System.out.print("+");
					}
					System.out.println();
					System.out.println(" " + String.valueOf(cnt-2) + " rows in set" + "\n");
					break;
				}
				if(cnt == 0) {  // 最初の行は文字数が書いてある
					n = line_split.length;  // 文字数(単語数)格納
					for(i=0; i<line_split.length; i++) column_len[i] = Integer.parseInt(line_split[i]);  // それぞれのカラムの最大文字数+2(表の横の長さ)
				} else {
					if(cnt == 1){
						for(i=0; i<n; i++){
							line_column[i] = line_split[i];  // カラム名を保存
						}
						if(!Arrays.asList(line_column).contains(cmd_split))
							nocolumn = 1;
						for(i=0; i<cmd_split.length; i++){
							for(j=0; j<n; j++) {
								if(line_column[j].equals(cmd_split[i])) {
									column_num[i] = j;
									output++;
									nocolumn = 0;
								}
							}
						}
						if(nocolumn == 1) {
							d.error("そのカラムは存在しません");
							break all_loop;
						}
						else {
							for(i=0; i<output; i++) {
								System.out.print("+");  // 最初の線
								for(j=0; j<column_len[column_num[i]]; j++) System.out.print("-");
							}
							System.out.print("+");
							System.out.println(); // ここまで最初の線
						
							for(i=0; i<output; i++) {
								System.out.print("| " + line_column[column_num[i]]);  // カラム名出力
								int temp =  column_len[column_num[i]] - line_split[column_num[i]].length() - 1;
								for(j=0; j<temp; j++) System.out.print(" ");  // 左詰め
							}
							System.out.println("|");
						
							for(i=0; i<output; i++) {
								System.out.print("+");  // 区切り線
								for(j=0; j<column_len[column_num[i]]; j++) System.out.print("-");
							}
							System.out.print("+");
							System.out.println();  // ここまで区切り線
						}
					}
					else if(cnt > 1) {
						for(i=0; i<output; i++) {
							System.out.print("| " + line_split[column_num[i]]);
							int temp =  column_len[column_num[i]] - line_split[column_num[i]].length() - 1;
							for(j=0; j<temp; j++) System.out.print(" ");  // 左詰め
						}
						System.out.println("|");
					}
				}
				cnt++;
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);  // 0以外は異常終了
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
