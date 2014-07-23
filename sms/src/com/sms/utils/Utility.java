package com.sms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {
	public static String getHash(String inputString, String algorithm) {
		StringBuilder sb = new StringBuilder();
	    try {
			byte[] hash = MessageDigest.getInstance(algorithm).digest(inputString.getBytes());
			
			for (int i = 0; i < hash.length; ++i) {
		        String hex = Integer.toHexString(hash[i]);
		        if (hex.length() == 1) {
		            sb.append(0);
		            sb.append(hex.charAt(hex.length() - 1));
		        } else {
		            sb.append(hex.substring(hex.length() - 2));
		        }
		    }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    
	    return sb.toString();
	}
}