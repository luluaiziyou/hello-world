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

		// ���췢������Json
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

		CloseableHttpClient client = HttpClients.createDefault();

		// HttpPost
		// System.out.println(uri);

		// ʹ��POST��ʽ��������
		HttpPost post = new HttpPost(uri.toString());
		HttpResponse res = null;

		try {
			StringEntity entity = new StringEntity(jsonParam.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/json");
			post.setEntity(entity);
			System.out.println("��������Ϊ��" + post.getEntity());
			// post.getAllHeaders();
			// post.getURI();
			res = client.execute(post);
			Header[] headers = res.getAllHeaders();
			// HttpStatus.SC_OK��ʾ���ӳɹ�
			if (res.getStatusLine().getStatusCode() != 404) {
				// ȡ�÷��ص��ַ���
				if (res.getEntity() != null) {
					// ��ӡ��Ӧͷ
					for (Header head : headers) {
						System.out.println(head.toString());
					}
					// ��ӡ���ص�ʵ������
					System.out.println("���ص�ʵ������Ϊ��" + res.getEntity());

				}
			} else {
				System.out.println("fail the request");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("�ذ�����Ϊ" + res);
		}
	}

	//
	// HttpPost httpPost = new HttpPost(uri.toString());
	// // �����ַ�������
	// HttpEntity httpEntity = new UrlEncodedFormEntity(, "utf-8");
	// // ʹ��HttpPost���setEntity()���������������
	// httpPost.setEntity(httpEntity);
	// // ����httpPost
	// System.out.println(httpPost.getEntity());
	// // ȡ��HttpResponse����
	// HttpResponse httpResponse = httpClient.execute(httpPost);
	// // ȡ��httpResponse�е���Ӧͷ
	// Header[] headers = httpResponse.getAllHeaders();
	// // ȡ��httpResponse�е���Ӧʵ������
	// HttpEntity entity = httpResponse.getEntity();
	// // HttpStatus.SC_OK��ʾ���ӳɹ�
	// if (httpResponse.getStatusLine().getStatusCode() != 404) {
	// // ȡ�÷��ص��ַ���
	// if (httpResponse.getEntity() != null) {
	//
	// // ��ӡ��Ӧͷ
	// for (Header head : headers) {
	// System.out.println(head.toString());
	// }
	// // ��ӡ���ص�ʵ������
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
		// ����get���ʲ���
		// try {
		// uri1 = uri.setParameter("user", "bjkanghui")
		// .setParameter("password", "bjkhpassword")
		// .setParameter("userid", "bjkhapi")
		// .setParameter("action", "getProducts");
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// �������󣬷��ؽ��������
		HttpGet get = new HttpGet(uri.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			System.out.println("���лذ�����Ϊ" + response);
			System.out.println("�ذ�����getAllHeaders()Ϊ:  "
					+ response.getAllHeaders());
			System.out.println("�ذ�����getClass()Ϊ:  " + response.getClass());
			System.out.println("�ذ�����getEntity()Ϊ:  " + response.getEntity());
			System.out.println("�ذ�����getLocale()Ϊ��  " + response.getLocale());
			System.out.println("�ذ�����getParams()Ϊ:  "
					+ response.getProtocolVersion());
			System.out.println("�ذ�����getStatusLine()Ϊ��  "
					+ response.getStatusLine());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

	}
}
