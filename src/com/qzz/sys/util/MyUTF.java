package com.qzz.sys.util;

import java.io.UnsupportedEncodingException;

public class MyUTF {
	//封装成工具类
	   public static String getNewString(String str) throws UnsupportedEncodingException
	    {
	       return new String(str.getBytes("ISO-8859-1"),"UTF-8");
	    }

}
