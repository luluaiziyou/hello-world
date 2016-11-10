package com.ml.httpclient;

import java.io.IOException;

public class Run {
	public static void main(String[] args) {
		// 协议类型（http,tcp，udp）
		String schemeString = "http";
		// 协议方法(get,post)
		String methodString = "GET";
		// 协议域名
		String hostString = "www.baidu.com";
		// 协议path
		String pathString = "/";
		String uriString = "";

		// System.out.println("schemeString长度为：" + schemeString.length());
		// 设置默认协议及请求方式，提示参数必填
		if (schemeString == null || schemeString.length() == 0) {
			schemeString = "http";
		}
		if (methodString == null || methodString.length() == 0) {
			methodString = "post";
		}
		if (hostString == null || hostString.length() == 0) {
			System.out.println("请定义host");
		}
		if (pathString == null || pathString.length() == 0) {
			System.out.println("请定义path");
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

	// 准备调用协议
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
			System.out.println("暂不支持该请求方式");
		}
	}

}
