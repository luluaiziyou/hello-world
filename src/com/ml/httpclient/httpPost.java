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
	// ��ʼ�� ����
	static StringBuffer result = new StringBuffer();
	static CloseableHttpClient client = HttpClients.createDefault();

	static HttpResponse res = null;
	static String strResult = "";

	public static void testHttpPost(String uriString, String paramString)
			throws UnsupportedEncodingException {
		HttpPost post = null;
		// jsonParam.put("user", "bjkanghui");
		// �޲�����postЭ��
		if (paramString == null || paramString.length() == 0) {
			post = new HttpPost(uriString.toString());
		} else {
			// ��ʱֻ֧��post����json��ʽ����keyvalue��ʽ����key=value&key1=value1��ʽ�Ĵ���ʽע��

			// String[] paramStrings = paramString.split("&");
			// for (int i = 0; i < paramStrings.length - 1; i++) {
			// String keyValue = paramStrings[i].toString();
			// String[] mapStrings = keyValue.split("=");
			// // System.out.println("key:" + mapStrings[0].toString() +
			// // "======"
			// // + "value��" + mapStrings[1].toString());
			// jsonParam.put(mapStrings[0].toString(),
			// mapStrings[1].toString());
			//
			// }
			JSONObject jsonParam = JSONObject.fromObject(paramString);
			// System.out.println("��ȡjson��ʽuser��ֵ ================"
			// + jsonParam.get("user"));
			post = new HttpPost(uriString.toString());
			StringEntity entity = new StringEntity(jsonParam.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/json");
			post.setEntity(entity);
		}

		// ʹ��POST��ʽ��������
		try {

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
				} else {
					System.out.println("fail the request");
				}
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
