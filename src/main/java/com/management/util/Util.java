package com.management.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date parseDate(String dateTine) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date parseDateTime = null;
		try {
			parseDateTime = simpleDateFormat.parse(dateTine);
		} catch (Exception e) {
			System.out.println("While date parsing " + e);
		}
		return parseDateTime;
	}
}
