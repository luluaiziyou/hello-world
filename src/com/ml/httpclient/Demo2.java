package com.ml.httpclient;

/**
 * Created by malu on 07/11/2016.
 */
//package httpclient;

import java.io.IOException;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Demo2 {

	public static void main(String[] args) throws IOException {
		System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8888");
		// 设置访问uri
		URIBuilder uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
					// .setHost("testapi.51anlv.com")
					// .setPath("/webservice/postbuy.php");
					.setHost("testapi.51anlv.com")
					.setPath("/webservice/postbuy.php");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(uri);

		// 构造Json
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("user", "bjkanghui");
		jsonParam.put("password", "bjkhpassword");
		jsonParam.put("userid", "bjkhapi");
		jsonParam.put("action", "getProducts");
		System.out.println("json内容：" + jsonParam);
		// testHttpGet(uri);
		testHttpPost(uri, jsonParam);
	}

	private static void testHttpPost(URIBuilder uri, JSONObject jsonParam) {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient client = HttpClients.createDefault();

		// HttpPost
		// System.out.println(uri);

		// 使用POST方式发送数据
		HttpPost post = new HttpPost(uri.toString());
		HttpResponse res = null;
		String strResult = "";

		try {
			StringEntity entity = new StringEntity(jsonParam.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/json");
			post.setEntity(entity);

			res = client.execute(post);
			Header[] headers = res.getAllHeaders();
			// HttpStatus.SC_OK表示连接成功
			if (res.getStatusLine().getStatusCode() != 404) {
				System.out.println("协议返回状态码："
						+ res.getStatusLine().getStatusCode());
				// 取得返回的字符串
				HttpEntity httpResEntity = res.getEntity();
				if (httpResEntity != null) {
					// 打印响应头
					for (Header head : headers) {
						System.out.println(head.toString());
					}
					// 打印返回的实体数据
					strResult = EntityUtils.toString(httpResEntity);
					System.out.println("返回的实体数据为：" + strResult);
					// 解析回包Json 数据
					// // 输入流字节流
					// InputStream inputStream = httpResEntity.getContent();
					// // 输入流字符流
					// InputStreamReader inputStreamReader = new
					// InputStreamReader(
					// inputStream);
					// // 缓冲流
					// BufferedReader reader = new BufferedReader(
					// inputStreamReader);
					// String s;
					// // 结果读出到 StringBuffer
					// while ((s = reader.readLine()) != null) {
					// result.append(s);
					// }
					// System.out.println("读出的返回实体数据字符串" + s);

					// 把 Json字符串转换成Json格式 ,解读内容

					JSONObject jsonRes = JSONObject.fromObject(strResult);
					Iterator<String> sIterator = jsonRes.keys();
					while (sIterator.hasNext()) {
						// 获取key
						String keyString = sIterator.next();
						// 根据key获取value
						String value = jsonRes.getString(keyString);
						System.out.println("key--" + keyString + "===="
								+ "value--" + value);
					}
				}
			} else {
				System.out.println("fail the request");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			// reader.close();
			System.out.println("回包内容为" + res.getStatusLine());
		}
	}
}
