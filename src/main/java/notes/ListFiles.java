package notes;

import java.io.File;

public class ListFiles {
	public static void main(String[] args) {
		File rootFile = new File("E:\\新建文件夹 (2)");
		filelist(rootFile, 0);
	}

	static void filelist(File f, int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("-");
		}
		System.out.println(f.getName());
		if (f.isDirectory()) {
			
			File[] files = f.listFiles();
			for (int i=0;i<files.length;i++) {
				filelist(files[i], level + 1);
			}
		}
	}
}
