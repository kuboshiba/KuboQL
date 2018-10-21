import java.io.*;
public class delete{
	delete(String databases,String table,String target,String operator,String conditions){
		Define d=new Define();
		String path ="./databases/"+databases+"/"+table;
		File file = new File(path);//組み込み関数
    		if (!file.exists()) d.error("そのテーブルは存在しません show tables で確認してください");
    		else erase(path,target,operator,conditions);
	}
	
	public void erase(String path,String target,String operator,String conditions){
		Define d = new Define();
		try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {//pathについていろいろ。わからん！
      			int cnt = 0, n = 0, i=0, j=0,x=0,y=0,targetnum=0,z=0,longcheck=0,oldlongcheck=0,yi=0;  // 読み込み回数カウント
      			String line;  // 1行単位で読み込み
     			 String[] line_split;  // 読み込んだ1行を分割
		
      			while((line = in.readLine()) != null) {//読み込んだ1行がNULLになるまで(line=in.readLine()も行う)
      				if(n==0){
      					line_split = line.split(",");//line_splitにlineを,区切りにして格納
      					i=line_split.length;
      					n++;
      				}
				cnt++;//何行のファイルか確認
      			}
      			in.close();//先頭に戻すため一回閉じて
      			int[] remove=new int[cnt];
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
      			for(int u=0;u<i;u++){
      				if(data[1][u].equals(target)){
      					targetnum=u;
      					z=1;
      				}
      			}
      			if(z!=1){
      				d.error("そのデータはありません");
      				return;
      			}
			if(Num(conditions)){
				y=1;
				yi=Integer.parseInt(conditions);
			}
			if(y==0){//条件が文字だった場合
				if(operator.equals("=")){
					for(int q=2;q<cnt-1;q++){
						if(data[q][targetnum].equals(conditions)){
							remove[q]=1;
						}
					}
				}else{//条件が文字かつ演算子が＝でない場合
					d.error("演算子のエラー");
					return;
				}
			}else{
				switch(operator){
					case "<":
						for(int zaq=2;zaq<cnt-1;zaq++){
							if(Num(data[zaq][targetnum])){
								x=Integer.parseInt(data[zaq][targetnum]);
								if(x<yi){
									remove[zaq]=1;
								}
							}else{
								d.error("指定されたカラムが数値ではありません");
								return;
							}
						}
						break;
					case "<=":
						for(int zbq=2;zbq<cnt-1;zbq++){
							if(Num(data[zbq][targetnum])){
								x=Integer.parseInt(data[zbq][targetnum]);
								if(x<=yi){
									remove[zbq]=1;
								}
							}else{
								d.error("指定されたカラムが数値ではありません");
								return;
							}
						}
						break;
					case "=":
						for(int zcq=2;zcq<cnt-1;zcq++){
							if(Num(data[zcq][targetnum])){
								x=Integer.parseInt(data[zcq][targetnum]);
								if(x==yi){
									remove[zcq]=1;
								}
							}else{
								d.error("指定されたカラムが数値ではありません");
								return;
							}
						}
						break;
					case ">=":
						for(int zdq=2;zdq<cnt-1;zdq++){
							if(Num(data[zdq][targetnum])){
								x=Integer.parseInt(data[zdq][targetnum]);
								if(x>=yi){
									remove[zdq]=1;
								}
							}else{
								d.error("指定されたカラムが数値ではありません");
								return;
							}
						}
						break;
					case ">":
						for(int zeq=2;zeq<cnt-1;zeq++){
							if(Num(data[zeq][targetnum])){
								x=Integer.parseInt(data[zeq][targetnum]);
								if(x>yi){
									remove[zeq]=1;
								}
							}else{
								d.error("指定されたカラムが数値ではありません");
								return;
							}
						}
						break;
					default:
						d.error("演算子のエラー");
						return;
				}
			}
			inn.close();//閉じるわ
			int max=0;
			for(int df=2;df<cnt;df++){
				if(remove[df]==1){
					for(int fw=0;fw<i;fw++){
						longcheck=data[df][fw].length();
						longcheck=longcheck+2;
						oldlongcheck=Integer.parseInt(data[0][fw]);
						if(oldlongcheck==longcheck){
							max=0;
							for(int mf=2;mf<cnt-1;mf++){
								if(max<(data[mf][fw].length())&&(data[mf][fw].length())<(longcheck-2)){
									max=data[mf][fw].length();
								}
							}
							if(data[1][fw].length()>max){
								max=data[1][fw].length();
							}
							data[0][fw]=String.valueOf(max+2);
						}
					}
				}
			}
			int zw;
			FileWriter file = new FileWriter(path);
      			PrintWriter pw = new PrintWriter(new BufferedWriter(file));
      			for(int l=0;l<cnt-1;l++){
      				if(remove[l]==0){
      					for(zw=0;zw<i-1;zw++){
      						pw.print(data[l][zw]+",");
      					}
      					pw.println(data[l][zw]);
      				}
      			}
      			pw.println("@eof");
      			pw.close();
      			d.log(target+operator+conditions+"のdelete完了");
		}catch (FileNotFoundException e) {
      			e.printStackTrace();
      			System.exit(-1); // 0 以外は異常終了
    		} catch (IOException e) {
      			e.printStackTrace();
      			System.exit(-1);
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
}
