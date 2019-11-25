package blockchain;

import java.util.ArrayList;
import java.util.Base64;

public class Blockchain {

	private int dificulty;
	private ArrayList<Transaction> miningPool;
	private ArrayList<Block> blockcahin;
	private int blockReward;

	private User systemUser;

	public Blockchain() throws Exception {
		dificulty = 1;

		systemUser = new User(this);
		blockReward = 12;

		blockcahin = new ArrayList<Block>();
		miningPool = new ArrayList<Transaction>();
		Block genesis = new Block();
		blockcahin.add(genesis);

		Transaction start = new Transaction(this, systemUser, systemUser, 1);
		miningPool.add(start);
	}

	public boolean verifyPool() throws Exception {
		for (int i = 0; i < miningPool.size(); i++) {
			if (miningPool.get(i).verify() == false) {
				throw new Exception("miningPool " + i + "  was changed");
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String head = "Blockchain [dificulty=" + dificulty + ", blockReward=" + blockReward + "]";
		String blocks = "\n";
		for (int i = 1; i < blockcahin.size(); i++) {
			blocks += blockcahin.get(i).toString();
			blocks += "\n";
		}
		return head + blocks;
	}

	public boolean smallEnough(String hash) {
		byte[] decoded = Base64.getDecoder().decode(hash);
		for (int i = 0; i < decoded.length - (decoded.length - dificulty); i++) {
			if (decoded[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid() {
		for (int i = 1; i < blockcahin.size(); i++) {
			if (blockcahin.get(i).isValid(blockcahin.get(i - 1), dificulty) == false) {
				return false;
			}
		}
		return true;
	}

	public int getBalance(User user) {
		int balance = 0;
		for (int i = 0; i < blockcahin.size(); i++) {
			balance += blockcahin.get(i).getBalance(user);
		}
		return balance;
	}

	public void addTransaction(Transaction transaction) throws Exception {
		if (transaction.verify() == false) {
			return;
		}
		miningPool.add(transaction);
	}

	/**
	 * @return the blocks
	 */
	public ArrayList<Block> getBlocks() {
		return blockcahin;
	}

	public Block getLastBlock() {
		return blockcahin.get(blockcahin.size() - 1);
	}

	/**
	 * @return the dificulty
	 */
	public int getDificulty() {
		return dificulty;
	}

	/**
	 * @param dificulty the dificulty to set
	 */
	public void setDificulty(int dificulty) {
		this.dificulty = dificulty;
	}

	/**
	 * @return the miningPool
	 */
	public Transaction[] getMiningPool() {
		Transaction[] out = new Transaction[miningPool.size()];
		for (int i = 0; i < miningPool.size(); i++) {
			out[i] = miningPool.get(i);
		}
		return out;
	}

	public void addBlock(Block newBlock, User user) {
		if (blockcahin.size() == 1) {
			blockcahin.add(newBlock);
			Transaction t = new Transaction(this, systemUser, user, blockReward);
			try {
				this.addTransaction(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		if (newBlock.isValid(this.getLastBlock(), dificulty)) {
			blockcahin.add(newBlock);
			Transaction t = new Transaction(this, systemUser, user, blockReward);
			try {
				this.addTransaction(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
