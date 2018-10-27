import java.io.*;
public class sort{
	sort(String databases,String table,String number){
		Define d = new Define();
		String path ="./databases/"+databases+"/"+table;
		if(Num(number)){
			File file = new File(path);//組み込み関数
    			if (!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください");
    			else sorting(path,number);
		}else{
			d.error("sortコマンドの使い方が間違っています helpか\\ｈで参照してください");
		}
	}
	
	public static boolean Num(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public void sorting(String path,String number){
		int a=Integer.parseInt(number);
		Define d = new Define();
		try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {//pathについていろいろ。わからん！
      			int cnt = 0, n = 0, i=0, j,z;  // 読み込み回数カウント
      			String line;  // 1行単位で読み込み
     			 String[] line_split;  // 読み込んだ1行を分割
     			 int[] culum_len = new int[50];  // カラム数は最大50個まで
		
      			while((line = in.readLine()) != null) {//読み込んだ1行がNULLになるまで(line=in.readLine()も行う)
      				if(n==0){
      					line_split = line.split(",");//line_splitにlineを,区切りにして格納
      					i=line_split.length;
      					n++;
      				}
				cnt++;//何行のファイルか確認
      			}
      			in.close();//先頭に戻すため一回閉じて
      			int que=Integer.parseInt(number);//数字化
      			if(que>i){
      				d.error("ソート対象のセルが見つかりません desc [テーブル名]でソート対象を確認してください");
      				return;
      			}
      			BufferedReader inn = new BufferedReader(new FileReader(new File(path)));//また開く
      			String[][] data=new String[cnt][i];
      			for(j=0;j<cnt;j++){
      				line=inn.readLine();
      				line_split = line.split(",");//line_splitにlineを,区切りにして格納
      				if(line_split[0].equals("@eof")){
      					data[j][0]="@eof";
      				}else{
      					for(int k=0;k<i;k++){
      						data[j][k]=line_split[k];
      					}
      				}
      			}
      			String[] swap=new String[i];//swapはソート時の退避
      			for(int w=2;w<cnt-1;w++){
      				for(j=w+1;j<cnt-1;j++){
      					if(data[w][que].compareToIgnoreCase(data[j][que])>0){
      						for(int y=0;y<i;y++){
      							swap[y]=data[w][y];
      							data[w][y]=data[j][y];
      							data[j][y]=swap[y];
      						}
      					}	
      				}
      			}
      			inn.close();//閉じる
			FileWriter file = new FileWriter(path);
      			PrintWriter pw = new PrintWriter(new BufferedWriter(file));
      			for(int l=0;l<cnt-1;l++){
      				for(z=0;z<i-1;z++){
      					pw.print(data[l][z]+",");
      				}
      				pw.println(data[l][z]);
      			}
      			d.log(path+"の"+que+"番カラムのデータを基準にソート完了");
			System.out.println();
			pw.print("@eof");
      			pw.close();
    		 
    		}catch (FileNotFoundException e) {
      			e.printStackTrace();
      			System.exit(-1); // 0 以外は異常終了
    		} catch (IOException e) {
      			e.printStackTrace();
      			System.exit(-1);
    		}
	}
	
	
	
}


