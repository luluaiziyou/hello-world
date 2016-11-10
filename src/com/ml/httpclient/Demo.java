package com.ml.httpclient;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Demo {

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

		// 构造发包体重Json
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

		CloseableHttpClient client = HttpClients.createDefault();

		// HttpPost
		// System.out.println(uri);

		// 使用POST方式发送数据
		HttpPost post = new HttpPost(uri.toString());
		HttpResponse res = null;

		try {
			StringEntity entity = new StringEntity(jsonParam.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/json");
			post.setEntity(entity);
			System.out.println("发包内容为：" + post.getEntity());
			// post.getAllHeaders();
			// post.getURI();
			res = client.execute(post);
			Header[] headers = res.getAllHeaders();
			// HttpStatus.SC_OK表示连接成功
			if (res.getStatusLine().getStatusCode() != 404) {
				// 取得返回的字符串
				if (res.getEntity() != null) {
					// 打印响应头
					for (Header head : headers) {
						System.out.println(head.toString());
					}
					// 打印返回的实体数据
					System.out.println("返回的实体数据为：" + res.getEntity());

				}
			} else {
				System.out.println("fail the request");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("回包内容为" + res);
		}
	}

	//
	// HttpPost httpPost = new HttpPost(uri.toString());
	// // 设置字符集编码
	// HttpEntity httpEntity = new UrlEncodedFormEntity(, "utf-8");
	// // 使用HttpPost类的setEntity()方法设置请求参数
	// httpPost.setEntity(httpEntity);
	// // 输入httpPost
	// System.out.println(httpPost.getEntity());
	// // 取得HttpResponse对象
	// HttpResponse httpResponse = httpClient.execute(httpPost);
	// // 取得httpResponse中的响应头
	// Header[] headers = httpResponse.getAllHeaders();
	// // 取得httpResponse中的响应实体数据
	// HttpEntity entity = httpResponse.getEntity();
	// // HttpStatus.SC_OK表示连接成功
	// if (httpResponse.getStatusLine().getStatusCode() != 404) {
	// // 取得返回的字符串
	// if (httpResponse.getEntity() != null) {
	//
	// // 打印响应头
	// for (Header head : headers) {
	// System.out.println(head.toString());
	// }
	// // 打印返回的实体数据
	// System.out.println(EntityUtils.toString(entity));
	// }
	// } else {
	// System.out.println("fail the request");
	// }
	// }
	// }

	private static void testHttpGet(URIBuilder uri) throws IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client = HttpClients.createDefault();
		URIBuilder uri1 = null;
		// 设置get访问参数
		// try {
		// uri1 = uri.setParameter("user", "bjkanghui")
		// .setParameter("password", "bjkhpassword")
		// .setParameter("userid", "bjkhapi")
		// .setParameter("action", "getProducts");
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// 发送请求，返回结果并分析
		HttpGet get = new HttpGet(uri.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			System.out.println("所有回包内容为" + response);
			System.out.println("回包内容getAllHeaders()为:  "
					+ response.getAllHeaders());
			System.out.println("回包内容getClass()为:  " + response.getClass());
			System.out.println("回包内容getEntity()为:  " + response.getEntity());
			System.out.println("回包内容getLocale()为：  " + response.getLocale());
			System.out.println("回包内容getParams()为:  "
					+ response.getProtocolVersion());
			System.out.println("回包内容getStatusLine()为：  "
					+ response.getStatusLine());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

	}
}
