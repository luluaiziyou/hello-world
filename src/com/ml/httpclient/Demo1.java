package com.ml.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		// request中json键值对
		Map<String, String> paramsCo = new HashMap<String, String>();
		paramsCo.put("user", "bjkanghui");
		paramsCo.put("password", "bjkhpassword");
		paramsCo.put("userid", "bjkhapi");
		paramsCo.put("bjkhapi", "getProducts");

		// 请求共用的httpclient对象，保持同一个会话
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// get请求
		// String getUrl = "http://www.baidu.com/";
		// httpGetMethod(httpClient, getUrl);
		// post请求
		// String postUrl =
		// "https://passport.csdn.net/account/login?from=http%3A%2F%2Fmy.csdn.net%2Fmy%2Fmycsdn";
		String postUrl = "http://testapi.51anlv.com/webservice/postbuy.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		for (Map.Entry<String, String> entry : paramsCo.entrySet()) {
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		System.out.println(params);
		// httpGetMethod(httpClient, getUrl);
		httpPostMethod(httpClient, postUrl, params);
	}

	/**
	 * post请求方式
	 * 
	 * @param httpClient
	 * @param url
	 * @param params
	 * @throws Exception
	 */
	public static void httpPostMethod(CloseableHttpClient httpClient,
			String url, List<NameValuePair> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		// 设置字符集编码
		HttpEntity httpEntity = new UrlEncodedFormEntity(params, "utf-8");
		// 使用HttpPost类的setEntity()方法设置请求参数
		httpPost.setEntity(httpEntity);
		// 输入httpPost
		System.out.println(httpPost.getEntity());
		// 取得HttpResponse对象
		HttpResponse httpResponse = httpClient.execute(httpPost);
		// 取得httpResponse中的响应头
		Header[] headers = httpResponse.getAllHeaders();
		// 取得httpResponse中的响应实体数据
		HttpEntity entity = httpResponse.getEntity();
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() != 404) {
			// 取得返回的字符串
			if (httpResponse.getEntity() != null) {

				// 打印响应头
				for (Header head : headers) {
					System.out.println(head.toString());
				}
				// 打印返回的实体数据
				System.out.println(EntityUtils.toString(entity));
			}
		} else {
			System.out.println("fail the request");
		}
	}

	/**
	 * Get请求方式
	 * 
	 * @param httpClient
	 * @param Url
	 * @throws IOException
	 * @throws Exception
	 */
	public static void httpGetMethod(CloseableHttpClient httpClient, String Url)
			throws Exception {
		// 创建一个 get 方法，类似于在浏览器地址栏中输入一个地址
		HttpGet httpGet = new HttpGet(Url);
		// httpGet.addHeader("Cookie", "JSESSIONID="+PublicData.COOKIE);

		// 取得HttpResponse对象
		HttpResponse httpResponse = httpClient.execute(httpGet);
		// 获取所有的响应头
		Header[] headers = httpResponse.getAllHeaders();
		// 取得响应结果httpResponse中的entity，并可以设置字符集编码
		HttpEntity entity = httpResponse.getEntity();
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() != 404) {
			// 取得返回的字符串
			if (httpResponse.getEntity() != null) {
				// 打印所有的响应头
				for (Header head : headers) {
					System.out.println(head.toString());
				}
				// 获取响应，并打印服务器返回的响应实体内容
				System.out.println(EntityUtils.toString(entity, "utf-8"));
			} else {
				System.out.println("fail the request");
			}
		}

	}
}
