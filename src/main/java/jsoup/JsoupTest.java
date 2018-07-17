package jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Camork on 2017-03-22.
 */
public class JsoupTest {

    private static boolean exeFlag = true; // 执行标识
    int pages = 0;

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://movie.douban.com/");
        get.setHeader("Content-Type", "application/x-www-form-urlencoded");
        get.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(get);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");

        response.close();

        Document document = Jsoup.parse(content);

        Element e = document.getElementById("screening");
        Elements elements = e.select(".ui-slide-content .title");

//        for (Element element : elements) {
//            System.out.println(element.text());
//        }


    }

    @Test
    public void ExecutorTest() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while (exeFlag) {
            if (pages < 100) {
                executorService.execute(() -> {
                    synchronized (this) {
                        System.out.println("爬取了第" + pages + "网页...");
                        pages++;
                    }
                });
            } else {
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) { // 活动线程个数是0
                    executorService.shutdown(); // 结束所有线程
                    exeFlag = false;
                    System.out.println("爬虫任务已经完成");
                }
            }
        }


    }
}
