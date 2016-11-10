package com.ml.httpclient;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class httpPost {
	// 初始化 变量
	static StringBuffer result = new StringBuffer();
	static CloseableHttpClient client = HttpClients.createDefault();

	static HttpResponse res = null;
	static String strResult = "";

	public static void testHttpPost(String uriString, String paramString)
			throws UnsupportedEncodingException {
		HttpPost post = null;
		// jsonParam.put("user", "bjkanghui");
		// 无参数的post协议
		if (paramString == null || paramString.length() == 0) {
			post = new HttpPost(uriString.toString());
		} else {
			// 暂时只支持post参数json格式，将keyvalue格式，如key=value&key1=value1格式的处理方式注释

			// String[] paramStrings = paramString.split("&");
			// for (int i = 0; i < paramStrings.length - 1; i++) {
			// String keyValue = paramStrings[i].toString();
			// String[] mapStrings = keyValue.split("=");
			// // System.out.println("key:" + mapStrings[0].toString() +
			// // "======"
			// // + "value：" + mapStrings[1].toString());
			// jsonParam.put(mapStrings[0].toString(),
			// mapStrings[1].toString());
			//
			// }
			JSONObject jsonParam = JSONObject.fromObject(paramString);
			// System.out.println("获取json格式user的值 ================"
			// + jsonParam.get("user"));
			post = new HttpPost(uriString.toString());
			StringEntity entity = new StringEntity(jsonParam.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/json");
			post.setEntity(entity);
		}

		// 使用POST方式发送数据
		try {

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
				} else {
					System.out.println("fail the request");
				}
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
