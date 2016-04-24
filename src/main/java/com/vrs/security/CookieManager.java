package com.vrs.security;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieManager {
	private static List<String> cookies = new ArrayList<String>(0);

	/**
	 * Check if cookies are valid
	 * @param req
	 * @return true if cookies are valid, false if cookkies are not valid
	 */
	public static boolean hasValidLoginCookie(HttpServletRequest req) {
		if (req.getCookies() == null)
			return false;
		if (req.getCookies()[0].getName().equals("login")
				&& cookies.contains(req.getCookies()[0].getValue()))
			return true;
		return false;
	}
	
	/**
	 *
	 * @return Cookie of a randomly-generated value
	 */
	public static String generateCookie() {
		String cookieValue = new BigInteger(130, new SecureRandom())
				.toString(32);
		cookies.add(cookieValue);
		return cookieValue;
	}
	
	/**
	 * 
	 * @return HTML->JS code for redirecting user to login page
	 */
	public static String reauth() {
		return "<script>alert('Invalid session! Try logging in again.');window.location.href=\"/login.html\";</script>";
	}
	private CookieManager() {

	}
}
