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
		// ���÷���uri
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

		// ����Json
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("user", "bjkanghui");
		jsonParam.put("password", "bjkhpassword");
		jsonParam.put("userid", "bjkhapi");
		jsonParam.put("action", "getProducts");
		System.out.println("json���ݣ�" + jsonParam);
		// testHttpGet(uri);
		testHttpPost(uri, jsonParam);
	}

	private static void testHttpPost(URIBuilder uri, JSONObject jsonParam) {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient client = HttpClients.createDefault();

		// HttpPost
		// System.out.println(uri);

		// ʹ��POST��ʽ��������
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
			// HttpStatus.SC_OK��ʾ���ӳɹ�
			if (res.getStatusLine().getStatusCode() != 404) {
				System.out.println("Э�鷵��״̬�룺"
						+ res.getStatusLine().getStatusCode());
				// ȡ�÷��ص��ַ���
				HttpEntity httpResEntity = res.getEntity();
				if (httpResEntity != null) {
					// ��ӡ��Ӧͷ
					for (Header head : headers) {
						System.out.println(head.toString());
					}
					// ��ӡ���ص�ʵ������
					strResult = EntityUtils.toString(httpResEntity);
					System.out.println("���ص�ʵ������Ϊ��" + strResult);
					// �����ذ�Json ����
					// // �������ֽ���
					// InputStream inputStream = httpResEntity.getContent();
					// // �������ַ���
					// InputStreamReader inputStreamReader = new
					// InputStreamReader(
					// inputStream);
					// // ������
					// BufferedReader reader = new BufferedReader(
					// inputStreamReader);
					// String s;
					// // ��������� StringBuffer
					// while ((s = reader.readLine()) != null) {
					// result.append(s);
					// }
					// System.out.println("�����ķ���ʵ�������ַ���" + s);

					// �� Json�ַ���ת����Json��ʽ ,�������

					JSONObject jsonRes = JSONObject.fromObject(strResult);
					Iterator<String> sIterator = jsonRes.keys();
					while (sIterator.hasNext()) {
						// ��ȡkey
						String keyString = sIterator.next();
						// ����key��ȡvalue
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
			// �ر�������
			// reader.close();
			System.out.println("�ذ�����Ϊ" + res.getStatusLine());
		}
	}
}
