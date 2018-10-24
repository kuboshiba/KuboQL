import java.io.*;
public class fdrop{
	fdrop(String path){
		Define d=new Define();
		File file=new File(path);
		if(!file.exists()){
			d.error("存在しません");
			return;
		}
		if(file.isFile()){
			d.error("dropコマンドの使い方が間違っています helpか\\ｈで参照してください");
			return;
		}
		File[] f=file.listFiles();
		for(int i=0;i<f.length;i++){
			new drop(f[i].getPath());
		}
		file.delete();
		d.log(path+"を削除しました");
	}
}
