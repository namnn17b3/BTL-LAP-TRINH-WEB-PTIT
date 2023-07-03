package fruitshop.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class Sha256 {
	public static String sha256(String input) {
		MessageDigest digest;
		String encoded = "";
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
			encoded = Base64.getEncoder().encodeToString(hashBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoded;
	}
}
