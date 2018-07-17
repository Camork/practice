package jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by camork on 06/03/2018.
 */
public class JsonReques {
	public static void main(String[] args) throws IOException {
		HttpGet get = new HttpGet("https://api.douban.com/v2/book/isbn/978-7-111-42190-5");

		CloseableHttpResponse response = HttpClients.createDefault().execute(get);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println(content);
		response.close();
	}
}
