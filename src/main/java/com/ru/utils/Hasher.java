package com.ru.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Hasher {
	
	public static String getHash(String matricula) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(matricula.getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}
}
