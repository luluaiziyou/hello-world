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
		// request��json��ֵ��
		Map<String, String> paramsCo = new HashMap<String, String>();
		paramsCo.put("user", "bjkanghui");
		paramsCo.put("password", "bjkhpassword");
		paramsCo.put("userid", "bjkhapi");
		paramsCo.put("bjkhapi", "getProducts");

		// �����õ�httpclient���󣬱���ͬһ���Ự
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// get����
		// String getUrl = "http://www.baidu.com/";
		// httpGetMethod(httpClient, getUrl);
		// post����
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
	 * post����ʽ
	 * 
	 * @param httpClient
	 * @param url
	 * @param params
	 * @throws Exception
	 */
	public static void httpPostMethod(CloseableHttpClient httpClient,
			String url, List<NameValuePair> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		// �����ַ�������
		HttpEntity httpEntity = new UrlEncodedFormEntity(params, "utf-8");
		// ʹ��HttpPost���setEntity()���������������
		httpPost.setEntity(httpEntity);
		// ����httpPost
		System.out.println(httpPost.getEntity());
		// ȡ��HttpResponse����
		HttpResponse httpResponse = httpClient.execute(httpPost);
		// ȡ��httpResponse�е���Ӧͷ
		Header[] headers = httpResponse.getAllHeaders();
		// ȡ��httpResponse�е���Ӧʵ������
		HttpEntity entity = httpResponse.getEntity();
		// HttpStatus.SC_OK��ʾ���ӳɹ�
		if (httpResponse.getStatusLine().getStatusCode() != 404) {
			// ȡ�÷��ص��ַ���
			if (httpResponse.getEntity() != null) {

				// ��ӡ��Ӧͷ
				for (Header head : headers) {
					System.out.println(head.toString());
				}
				// ��ӡ���ص�ʵ������
				System.out.println(EntityUtils.toString(entity));
			}
		} else {
			System.out.println("fail the request");
		}
	}

	/**
	 * Get����ʽ
	 * 
	 * @param httpClient
	 * @param Url
	 * @throws IOException
	 * @throws Exception
	 */
	public static void httpGetMethod(CloseableHttpClient httpClient, String Url)
			throws Exception {
		// ����һ�� get ���������������������ַ��������һ����ַ
		HttpGet httpGet = new HttpGet(Url);
		// httpGet.addHeader("Cookie", "JSESSIONID="+PublicData.COOKIE);

		// ȡ��HttpResponse����
		HttpResponse httpResponse = httpClient.execute(httpGet);
		// ��ȡ���е���Ӧͷ
		Header[] headers = httpResponse.getAllHeaders();
		// ȡ����Ӧ���httpResponse�е�entity�������������ַ�������
		HttpEntity entity = httpResponse.getEntity();
		// HttpStatus.SC_OK��ʾ���ӳɹ�
		if (httpResponse.getStatusLine().getStatusCode() != 404) {
			// ȡ�÷��ص��ַ���
			if (httpResponse.getEntity() != null) {
				// ��ӡ���е���Ӧͷ
				for (Header head : headers) {
					System.out.println(head.toString());
				}
				// ��ȡ��Ӧ������ӡ���������ص���Ӧʵ������
				System.out.println(EntityUtils.toString(entity, "utf-8"));
			} else {
				System.out.println("fail the request");
			}
		}

	}
}
