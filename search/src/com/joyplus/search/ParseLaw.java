package com.joyplus.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.*;

public class ParseLaw {
	// private static String yzys =
	// "[ed2k]+://[^\"]*|[azAz]+://[^\"]*";//宇宙影视的规则,找ed2k开头,"""结尾;http开头,"结尾
	// private static String scys =
	// "[magnet]+:\\?[^\"]*";//圣城影视的规则,找magnet开头,"""结尾
	private static String PARSERULES = "[ed2k]+://[^\"]*|[magnet]+:\\?[^\"]*";

	public static List<String> GetUrlFromHtml(String data) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = null;
		if (data == null) {
			return null;
		}
		pattern = Pattern.compile(PARSERULES);
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}
}