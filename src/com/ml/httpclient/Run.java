package com.ml.httpclient;

import java.io.IOException;

public class Run {
	public static void main(String[] args) {
		// Э�����ͣ�http,tcp��udp��
		String schemeString = "http";
		// Э�鷽��(get,post)
		String methodString = "GET";
		// Э������
		String hostString = "www.baidu.com";
		// Э��path
		String pathString = "/";
		String uriString = "";

		// System.out.println("schemeString����Ϊ��" + schemeString.length());
		// ����Ĭ��Э�鼰����ʽ����ʾ��������
		if (schemeString == null || schemeString.length() == 0) {
			schemeString = "http";
		}
		if (methodString == null || methodString.length() == 0) {
			methodString = "post";
		}
		if (hostString == null || hostString.length() == 0) {
			System.out.println("�붨��host");
		}
		if (pathString == null || pathString.length() == 0) {
			System.out.println("�붨��path");
		}
		if ((hostString != null && hostString.length() != 0)
				&& (pathString != null && pathString.length() != 0)) {
			uriString = schemeString + "://" + hostString + pathString;
			System.out.println("uriString=====" + uriString);
			beginHttp(methodString, uriString);
		}

		// System.out.println("shemeString==" + schemeString + "\n"
		// + "methodString==" + methodString + "\n" + "hostString=="
		// + hostString + "\n" + "pathString==" + pathString);
	}

	// ׼������Э��
	private static void beginHttp(String methodString, String uriString) {
		// TODO Auto-generated method stub
		System.out.println("beginHttp");
		if (methodString.toLowerCase().equals("get")) {
			System.out.println("get");
			try {
				httpGet.testHttpGet(uriString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (methodString.toLowerCase().equals("post")) {
			System.out.println("post");

		} else {
			System.out.println("�ݲ�֧�ָ�����ʽ");
		}
	}

}
