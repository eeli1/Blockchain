package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

public class Block {

	private String hash;
	private String previousHash;

	private Transaction[] transaction;

	private LocalDateTime time;

	public Block(Transaction[] transaction, String previousHash) {
		this.transaction = transaction;
		this.previousHash = previousHash;
	}

	public Block() { // for genesisBlock

	}

	public int getBalance(User user) {
		int balance = 0;
		for (int i = 0; i < transaction.length; i++) {
			balance += transaction[i].getBalance(user);
		}
		return balance;
	}

	public boolean isValid(Block previousBlock, int dificulty) {
		if (previousHash != previousBlock.getHash()) {
			return false;
		}

		if (greaterThan(this.hash, dificulty)) {
			return false;
		}

		for (int i = 0; i < transaction.length; i++) {
			try {
				if (transaction[i].verify() == false) {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private boolean greaterThan(String hash, int dificulty) {

	}

	@Override
	public String toString() {
		return "Block [hash=" + hash + ", previousHash=" + previousHash + ", transaction="
				+ Arrays.toString(transaction) + ", time=" + time + ", nonce=" + nonce + "]";
	}

	/**
	 * @return the transaction
	 */
	public Transaction[] getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction[] transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void updataTime() {
		time = LocalDateTime.now();
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	private long nonce;

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 * @throws NoSuchAlgorithmException
	 */
	public void calcHash() throws NoSuchAlgorithmException {
		this.hash = getSHA(this.toString());
	}

	private static String getSHA(String text) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
		return encoded;
	}

	/**
	 * @return the previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * @param previousHash the previousHash to set
	 */
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	/**
	 * @return the nonce
	 */
	public long getNonce() {
		return nonce;
	}

	/**
	 * @param nonc the nonce to set
	 */
	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

}
