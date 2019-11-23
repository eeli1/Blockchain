package blockchain;

import java.security.*;
import java.util.Base64;

public class User {

	private KeyPair keyPair;
	private Blockchain blockchain;
	private boolean systemUser;

	public User(Blockchain blockchain) throws Exception {

		if (blockchain.getBlocks() == null) {
			systemUser = true;
		} else {
			systemUser = false;
		}

		keyPair = generateKeyPair();
		this.blockchain = blockchain;
	}

	/**
	 * @return the systemUser
	 */
	public boolean isSystemUser() {
		return systemUser;
	}

	public User(Blockchain blockchain, KeyPair keyPair) {
		this.keyPair = keyPair;
		this.blockchain = blockchain;
	}

	public void createTransaction(User to, int amount) throws Exception {
		Transaction transaction = new Transaction(this, to, amount);
		transaction.signFrom(this.sign((transaction.toString()), keyPair));

		blockchain.addTransaction(transaction);
	}

	@Override
	public String toString() {
		return "User PublicKey = " + keyPair.getPublic();
	}

	public PublicKey getPublicKey() {
		return keyPair.getPublic();
	}

	private KeyPair generateKeyPair() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048, new SecureRandom());
		KeyPair pair = generator.generateKeyPair();

		return pair;
	}

	private String sign(String plainText, KeyPair keyPair) throws Exception {
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(keyPair.getPrivate());
		privateSignature.update(plainText.getBytes("UTF_8"));

		byte[] signature = privateSignature.sign();

		return Base64.getEncoder().encodeToString(signature);
	}

//	public static KeyPair getKeyPairFromKeyStore() throws Exception {
//	InputStream ins = RsaExample.class.getResourceAsStream("/keystore.jks");
//
//	KeyStore keyStore = KeyStore.getInstance("JCEKS");
//	keyStore.load(ins, "s3cr3t".toCharArray()); // Keystore password
//	KeyStore.PasswordProtection keyPassword = // Key password
//			new KeyStore.PasswordProtection("s3cr3t".toCharArray());
//
//	KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("mykey", keyPassword);
//
//	java.security.cert.Certificate cert = keyStore.getCertificate("mykey");
//	PublicKey publicKey = cert.getPublicKey();
//	PrivateKey privateKey = privateKeyEntry.getPrivateKey();
//
//	return new KeyPair(publicKey, privateKey);
//}

}
