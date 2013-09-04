package com.joyplus.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.joyplus.service.SearchThread;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public void OnClickSearchBtn(View v){
		new SearchThread(EncodeKeyWord("²Ô¾®¿Õ")).start();
	}
	
	public String EncodeKeyWord(String keyword){
		 try {
			return URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
