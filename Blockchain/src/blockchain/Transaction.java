package blockchain;

import java.security.Signature;
import java.util.Base64;

public class Transaction {

	private User from;
	private User to;
	private int amount;
	private int gas;
	private String signatureFrom;
	private String signatureTo;

	public Transaction(User from, User to, int amount, int gas) {
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.gas = gas;
	}

	public boolean verify() throws Exception {
		if ((signatureFrom == null) || (signatureTo == null)) {
			return false;
		}

		if (!verifyUser(("from=" + from + ", to=" + to + ", amount=" + amount + ", gas=" + gas), from, signatureFrom)) {
			return false;
		}
		if (!verifyUser(("from=" + from + ", to=" + to + ", amount=" + amount + ", gas=" + gas), to, signatureTo)) {
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
		return "from=" + from + ", to=" + to + ", amount=" + amount + ", gas=" + gas;
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
		this.signatureTo = signatureTo;
	}

	/**
	 * @return the gas
	 */
	public int getGas() {
		return gas;
	}

	/**
	 * @return the from
	 */
	public User getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public User getTo() {
		return to;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

}
