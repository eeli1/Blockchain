package blockchain;

import java.security.Signature;
import java.time.LocalDateTime;
import java.util.Base64;

public class Transaction {

	private User userFrom;
	private User userTo;
	private int amount;
	private String signature;
	private Blockchain blockchain;
	private int randomNum;
	private LocalDateTime time;

	public Transaction(Blockchain blockchain, User userFrom, User userTo, int amount) {
		this.userFrom = userFrom;
		this.userTo = userTo;
		if (amount > 0) {
			this.amount = amount;
		} else {
			this.amount = 0;
		}
		this.blockchain = blockchain;
		randomNum = (int) (Math.random() * Math.pow(10, 9));
		time = LocalDateTime.now();

	}

	public void sign(String signature) throws Exception {
		this.signature = signature;
	}

	public int getRandomNum() {
		return randomNum;
	}

	public int getBalance(User user) {
		if (user == userFrom) {
			return -amount;
		}
		if (user == userTo) {
			return amount;
		}
		return 0;
	}

	public boolean verify() throws Exception {
		if (userFrom.isSystemUser()) {
			return true;
		}
		if (signature == null) {
			return false;
		}
		if (!verifyUser((this.toString()), userTo, signature)) {
			return false;
		}
		return true;
	}

	private static boolean verifyUser(String transaction, User user, String signature) throws Exception {
		Signature publicSignature = Signature.getInstance("SHA256withRSA");
		publicSignature.initVerify(user.getPublicKey());
		publicSignature.update(transaction.getBytes("UTF_8"));

		byte[] signatureBytes = Base64.getDecoder().decode(signature);

		return publicSignature.verify(signatureBytes);
	}

	@Override
	public String toString() {
		return "userFrom=" + userFrom.toString() + ", userTo=" + userTo.toString() + ", amount=" + amount + "randomNum="
				+ randomNum;
	}

	/**
	 * @return the from
	 */
	public User getUserFrom() {
		return userFrom;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @return the to
	 */
	public User getUserTo() {
		return userTo;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	public LocalDateTime getTime() {
		return time;
	}

}
