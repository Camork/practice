package temp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public int count1;
    public int count2 = 0;

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}

public class ClassLoad {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        URL url = new URL("jar:file:/D:/git/quick-completion-plugin/build/idea-sandbox/plugins/quick-completion-plugin/lib/asm-7.0.jar!/");


        File file = FileUtils.toFile(url);

    }
}