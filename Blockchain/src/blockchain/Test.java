package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Test {

	public static void main(String[] args) throws Exception {
//		MessageDigest digest = MessageDigest.getInstance("SHA-256");
//
//		String text1 = "1";
//		byte[] hash1 = digest.digest(text1.getBytes(StandardCharsets.UTF_8));
//		String encoded1 = Base64.getEncoder().encodeToString(hash1);
//
//		String text2 = "0";
//		byte[] hash2 = digest.digest(text2.getBytes(StandardCharsets.UTF_8));
//		String encoded2 = Base64.getEncoder().encodeToString(hash2);
//
//		encoded1 = "0000000000000000001432e88926cae62bfc7bde6ea987885c4a8139d568358d";
//
//		encoded2 = "000000000000000005d4224dc74ec40a1f13bb1c19f757eedb867f70ccbde300";
//		System.out.println(encoded1);
//		System.out.println(encoded2);

		String encoded = "0000000000000000001432e88926cae62bfc7bde6ea987885c4a8139d568358d";
		byte[] decoded = Base64.getDecoder().decode(encoded);
		for (byte b : decoded) {
			System.out.println(b);
		}
		// System.out.println(smallEnough(encoded, 1));
	}

	private static boolean smallEnough(String hash, int dificulty) {
		byte[] decoded = Base64.getDecoder().decode(hash);
		for (int i = decoded.length - 1; i < decoded.length - dificulty; i--) {
			if (decoded[i] != 0) {
				return false;
			}
		}
	}

}
