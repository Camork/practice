package notes;

import java.io.*;

public class FileTest {
	public static void main(String[] args) {
		File file = new File(".");
		String[] nameList = file.list(new MyFilenameFilter());
		for (String temp : nameList) {
			System.out.println(temp);
		}
		System.out.println("s\ts");
	}
}

// 实现自己的FilenameFilter实现类
class MyFilenameFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		// 如果文件名以.java结尾，或者文件对应一个路径，返回true
		return (name.endsWith("txt") || new File(name).isDirectory());
	}
}