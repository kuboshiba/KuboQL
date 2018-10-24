import java.io.*;
import java.util.*;
public class insert{
	insert(String databases,String table){
		Define d = new Define();  //Define(Kubo)の関数を用いてプログラミングを行う
		String path = "./databases/"+databases+"/"+table;		
		File file = new File(path);//pathには読み込みたいファイルの場所
    		if (!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください(insert)");
    			else inserting(path);
    			//上３行でpathで示した場所にファイルが存在するか確認
    			
    			
	}

 public void inserting(String path){
	Define d = new Define();
	try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {//pathにはファイルの場所。ファイルを読み込む下準備
      			String line;  // lineを定義
     			 String[] line_split;  // line_splitを定義
		int flag,kosu,gyosu;
			flag = 0;
			gyosu = 0;
			kosu=0;
			while((line = in.readLine())!=null){ 			//lineにpathの１行を格納する部分を回す
				if(flag == 0){
					line_split = line.split(",");
					kosu = line_split.length;
					flag = 1;
							}
			gyosu++;
}
		in.close();//17行目の終了
		System.out.println("kosu:"+kosu+"gyosu:"+gyosu);
	BufferedReader in2 = new BufferedReader(new FileReader(new File(path))); //pathにはファイルの場所。ファイルを読み込む下準備
			gyosu++;
		String[][] data = new String[gyosu][kosu];
		for(int p=0; p<gyosu-2; p++){
      			line = in2.readLine();
				line_split=line.split(",");
					for(int k=0; k<kosu; k++){
						data[p][k] = line_split[k];
					}
//10/18はここまでしました					
				}
		Scanner scanner = new Scanner(System.in);//system.inはコマンドプロントへの入力。scannerは組み込み関数
		
		//ここからデータの追加(各種変数の調節（denshiの1行目にあるやつ）も同時にやっちゃうで)
		//古い変数を格納する変数0で定義して
		//新しい変数を格納する変数0で定義して
		for(int s=0; s<kosu; s++){
			d.yellow(data[1][s]+"の追加するデータを入力:");
			data[gyosu-2][s]=scanner.nextLine();
			int g = Integer.parseInt(data[0][s]);
			int o = data[gyosu-2][s].length();
			if(g-2 < o){
				data[0][s]=String.valueOf(o+2);
			}
		}
		in2.close();
		FileWriter file = new FileWriter(path);//書き込むための準備
      			PrintWriter pw = new PrintWriter(new BufferedWriter(file));//書き込むための準備
      			for(int kaki=0; kaki<gyosu-1; kaki++){
      				for(int yomi=0; yomi<kosu-1; yomi++){
      					pw.print(data[kaki][yomi]+",");
      				}
      				pw.println(data[kaki][kosu-1]);
      			}
      			pw.println("@eof");
      			pw.close();
		//for(int u=0;u<gyosu-1;u++){
			//for(int j=0;j<kosu;j++){
				//System.out.print(data[u][j]);
			//}
			System.out.println();
      			}catch (FileNotFoundException e) {//tryの中身がバグった場合
      			e.printStackTrace();
      			System.exit(-1); // 0 以外は異常終了
    		} catch (IOException e) {//tryの中身がバグった場合
      			e.printStackTrace();
      			System.exit(-1);
    		}
}
}
