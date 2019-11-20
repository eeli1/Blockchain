package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Block {

	private String hash;
	private String previousHash;

	private Transaction[] transaction;

	private LocalDateTime time;

	public Block(Transaction[] transaction, String previousHash) {
		this.transaction = transaction;
		this.previousHash = previousHash;
	}

	public Block(Transaction[] transaction) {
		this.transaction = transaction;
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
		this.hash = new String(getSHA(this.toString()));
	}

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
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
