package com.joyplus.search;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private WebView webView;
	private ListView listView = null;
	private MyHandler myHandler = null;
	private final int MESSAGESHOWLISTVIEW = 101;
	List<String> list = new ArrayList<String>();
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_activity);
		webView = (WebView)findViewById(R.id.webView1);
		listView = (ListView)findViewById(R.id.listview);
//		webView.loadUrl("http://www.btscg.com/");
		webView.loadUrl("http://www.lesou.org/search.php?wd=%E8%8D%89");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new InJavascriptInterface(), "injsinterface");
		webView.setWebViewClient(new WebViewClient() {
			@SuppressLint("ShowToast")
			@Override
			public void onPageFinished(WebView view, String url) {
				Toast.makeText(WebViewActivity.this, "网页加载完成", 0).show();
				view.loadUrl("javascript:window.injsinterface.show(document.body.innerHTML);");
				super.onPageFinished(view, url);
			}
		});
		listView.setVisibility(View.INVISIBLE);
		myHandler = new MyHandler();
	}

	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler{
		
		public MyHandler(){
			
		}
		
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what)
			{
			case MESSAGESHOWLISTVIEW:
				if(list.size()>0)
				{
					listView.setVisibility(View.VISIBLE);
					listView.setAdapter(new ArrayAdapter<String>(WebViewActivity.this, R.layout.listview_item,list));
					listView.requestFocus();
					listView.setFocusable(true);
					listView.setFocusableInTouchMode(true);
				}
				break;
			default:
				break;
			}
		}
	}
	
	class InJavascriptInterface {
		@SuppressLint("ShowToast")
		public void show(String data) {
			Toast.makeText(WebViewActivity.this, "执行了handler.show方法", 0).show();
			Document doc = Jsoup.parse(data);
			String temp = doc.select("font").toString();
			list = ParseLaw.GetUrlFromHtml(data);//圣城影视
			myHandler.sendEmptyMessage(MESSAGESHOWLISTVIEW);
		}
	}
}