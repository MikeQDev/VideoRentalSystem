package com.vrs.security;

public class Miscellaneous {
	/**
	 * Sanitize string requests to prevent from SQL and XSS attacks
	 * @param s
	 * @return Sanitized request
	 */
	public static String sanitize(String s){
		return s.replaceAll("[<>()\'\"]", "");
	}
	private Miscellaneous(){}
}
