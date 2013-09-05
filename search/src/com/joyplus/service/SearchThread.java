package com.joyplus.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.joyplus.data.Const;

public class SearchThread extends Thread{
	private String data = null;
	private String keyWord = null;
	public SearchThread(String keyWord){
		this.keyWord = keyWord;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(keyWord == null)
			{
				return;
			}
			String temp = Const.REQUESTURL+keyWord;
			Document doc = Jsoup.connect(Const.REQUESTURL+keyWord).get();
			Elements test = doc.select("div.main_content");
			org.jsoup.nodes.Element element = test.get(0);
			Elements test3 = element.getAllElements();
			data = element.toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Const.data = data;
		}
	}
}