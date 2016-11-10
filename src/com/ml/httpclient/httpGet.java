package com.ml.httpclient;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class httpGet {

	public static void testHttpGet(String uri) throws IOException {
		// TODO Auto-generated method stub
		int code;
		CloseableHttpClient client = HttpClients.createDefault();

		// �������󣬷��ؽ��������
		HttpGet get = new HttpGet(uri.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			// ��ӡ�ذ�״̬��
			code = response.getStatusLine().getStatusCode();
			System.out.println("�ذ�״̬�룺" + code);
			// ��ӡ�ذ�ͷ��Ϣ
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + "======="
						+ header.getValue());
			}
			// ��ӡ��������
			HttpEntity entity = response.getEntity();

			System.out.println("��������===="
					+ EntityUtils.toString(entity, "utf-8"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			response.close();
		}

	}
}
