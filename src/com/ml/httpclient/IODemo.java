package com.ml.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author:Malu
 * @Date:2016/11/10
 * FilePath=> D:\���߿���\Э�鹤��\interfaceTest.txt
 */
public class IODemo {
	static String filePath = "D:\\���߿���\\Э�鹤��\\interfaceTest.txt";

	// static String filePath = "D:\\���߿���\\Э�鹤��";

	public static void main(String args[]) throws IOException {
		System.out.println(filePath);
		String encoding = "GBK";
		List<String> list = new ArrayList<String>();
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("·�������ڣ�����������filePath");
		} else {
			if (file.isDirectory()) {
				System.out.println("��·��Ϊ�ļ�·�����������ļ���·��");
			} else {
				System.out.println("��ض��ˣ�");
				try {
					InputStreamReader reader = new InputStreamReader(
							new FileInputStream(file), encoding);

					BufferedReader bufferedReader = new BufferedReader(reader);
					String lineTexString = null;
					while ((lineTexString = bufferedReader.readLine()) != null) {
						list.add(lineTexString);
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("��ȡ�ļ����ݳ���");
					e.printStackTrace();
				}
				// System.out.println(list.get(0));
				String config = list.get(0);
				Run.listRun(config);
			}
		}

	}
}
