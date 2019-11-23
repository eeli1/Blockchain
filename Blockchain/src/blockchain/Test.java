package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Test {

	public static void main(String[] args) throws Exception {
		Blockchain blockchain = new Blockchain();
		User u1 = new User(blockchain);
		Miner m1 = new Miner(blockchain, u1);

		m1.mine();
		m1.mine();

		User u2 = new User(blockchain);

		Transaction t = new Transaction(u1, u2, 1);
		blockchain.addTransaction(t);

		m1.mine();
		System.out.println(blockchain);
		System.out.println(blockchain.getBlocks().size());
		System.out.println("end");
	}

	private static void testHash() {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			int i = 0;
			String text = "tests, nonce = ";
			byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			String temp;

			while (!smallEnough(encoded, 1)) {
				temp = text + i;
				System.out.println("text: " + temp + "  hash: " + encoded);
				i++;
				hash = digest.digest(temp.getBytes(StandardCharsets.UTF_8));
				encoded = Base64.getEncoder().encodeToString(hash);
			}

			temp = text + i;
			System.out.println("text: " + temp + "  hash: " + encoded);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static boolean smallEnough(String hash, int dificulty) {
		byte[] decoded = Base64.getDecoder().decode(hash);
		for (int i = 0; i < decoded.length - (decoded.length - dificulty); i++) {
			if (decoded[i] != 0) {
				return false;
			}
		}
		return true;
	}

}
