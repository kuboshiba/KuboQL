import java.io.*;
public class drop{
	drop(String databases,String table){
		String path="./databases/"+databases+"/"+table;
		Define d =new Define();
		File file =new File(path);
		
		if(file.exists()){
			if(file.delete()){
				d.log(table+"を削除しました");
			}else{
				d.error(table+"を削除できませんでした");
			}
		}else{
			d.error(table+"が見つかりません");
		}
	}
}
