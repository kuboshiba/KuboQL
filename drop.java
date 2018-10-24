import java.io.*;
public class drop{
	drop(String path){
		Define d =new Define();
		File file =new File(path);
		
		if(file.exists()){
			if(file.delete()){
				d.log("削除しました");
			}else{
				d.error("削除できませんでした");
			}
		}else{
			d.error("見つかりません");
		}
	}
}
