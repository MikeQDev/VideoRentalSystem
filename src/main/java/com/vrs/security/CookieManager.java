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

	public static boolean hasValidLoginCookie(HttpServletRequest req) {
		if (req.getCookies() == null)
			return false;
		if (req.getCookies()[0].getName().equals("login")
				&& cookies.contains(req.getCookies()[0].getValue()))
			return true;
		return false;
	}

	public static String generateCookie() {
		String cookieValue = new BigInteger(130, new SecureRandom())
				.toString(32);
		cookies.add(cookieValue);
		return cookieValue;
	}

	public static String reauth() {
		return "<script>alert('Invalid session! Try logging in again.');window.location.href=\"/login.html\";</script>";
	}

	/*
		public static boolean isValidCookie(HttpServletRequest req) {
			List<Cookie> cookies = new ArrayList<Cookie>(Arrays.asList(req
					.getCookies()));
			for (Cookie c : cookies) {
				if (c.getName().equals("authentication")) {
					if (c.getValue() == "a")
						return true;
					else
						return false;
				}
			}
			return false;
		}
	*/
	private CookieManager() {

	}
}
