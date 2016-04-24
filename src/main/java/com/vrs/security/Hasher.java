package com.vrs.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	/**
	 * 
	 * @param password
	 * @return hashed password
	 */
	public static String hashPass(String password) {
		try {
			password = new BigInteger(1, MessageDigest.getInstance("MD5")
					.digest(("whySo" + password + "salty").getBytes()))
					.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

	private Hasher() {

	}
}
