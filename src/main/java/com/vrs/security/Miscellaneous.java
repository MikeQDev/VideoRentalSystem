package com.vrs.security;

public class Miscellaneous {
	public static String sanitize(String s){
		return s.replaceAll("[<>()\'\"]", "");
	}
	private Miscellaneous(){}
}
