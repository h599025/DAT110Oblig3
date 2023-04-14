package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash { 
	
	
	public static BigInteger hashOf(String entity) {	
		
		BigInteger hashint = null;

		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		try {
			// we use MD5 with 128 bits digest
			MessageDigest md = MessageDigest.getInstance("MD5");

			// compute the hash of the input 'entity'
			byte[] hash = md.digest(entity.getBytes("UTF-8"));

			// convert the hash into hex format
			String hex = toHex(hash);

			// convert the hex into BigInteger
			hashint = new BigInteger(hex, 16);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		// compute the number of bits = bitSize()
		int numbits = bitSize();

		// compute the address size = 2 ^ number of bits
		BigInteger addressSize = BigInteger.valueOf(2).pow(numbits);

		// return the address size
		return addressSize;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			digestlen = md.getDigestLength();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
