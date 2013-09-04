package com.joyplus.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

import com.joyplus.adkey.RequestException;
import com.joyplus.adkey.Util;
import com.joyplus.data.Const;

import android.content.Intent;

public class SearchThread extends Thread{
	private String data = null;
	private String keyWord = null;
	public SearchThread(String keyWord){
		this.keyWord = keyWord;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection connection = null;
		InputStream inputstream = null;
		try {
			if(keyWord == null)
			{
				return;
			}
			URL url = new URL(Const.REQUESTURL+keyWord);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(60*1000);
			connection.setRequestMethod("GET");
			inputstream = connection.getInputStream();
			// 将要下载的文件写到保存在保存路径下的文件
			byte[] buffer = new byte[1024];
			while ((inputstream.read(buffer)) != -1) {
				data += buffer;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Const.data = data;			
		}
		
		/*
		 * httpClient获取网页源码
		 */

		String url;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setSoTimeout(client.getParams(),
				Const.SOCKET_TIMEOUT);
		HttpConnectionParams.setConnectionTimeout(client.getParams(),
				Const.CONNECTION_TIMEOUT);
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				Const.data = (response.getEntity().getContent());
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			
		}
	
		
	}
	
}