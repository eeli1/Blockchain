package blockchain;

import java.security.Signature;
import java.util.Base64;

public class Transaction {

	private User userFrom;
	private User userTo;
	private int amount;
	private String signatureFrom;
	private String signatureTo;

	public Transaction(User userFrom, User userTo, int amount) {
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.amount = amount;
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
		if ((signatureFrom == null) || (signatureTo == null)) {
			return false;
		}

		if (!verifyUser((this.toString()), userFrom, signatureFrom)) {
			return false;
		}
		if (!verifyUser((this.toString()), userTo, signatureTo)) {
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
		return "userFrom=" + userFrom + ", userTo=" + userTo + ", amount=" + amount;
	}

	/**
	 * @return the signatureFrom
	 */
	public String getSignatureFrom() {
		return signatureFrom;
	}

	/**
	 * @param signatureFrom the signatureFrom to set
	 */
	public void signFrom(String signatureFrom) {
		if (this.signatureFrom != null) {
			throw new IllegalAccessError("signatureFrom has alrady been defind");
		}
		this.signatureFrom = signatureFrom;
	}

	/**
	 * @return the signatureTo
	 */
	public String getSignatureTo() {
		return signatureTo;
	}

	/**
	 * @param signatureTo the signatureTo to set
	 */
	public void signTo(String signatureTo) {
		if (this.signatureTo != null) {
			throw new IllegalAccessError("signatureTo has alrady been defind");
		}
		this.signatureTo = signatureTo;
	}

	/**
	 * @return the from
	 */
	public User getUserFrom() {
		return userFrom;
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

}
