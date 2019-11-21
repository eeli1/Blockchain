package blockchain;

import java.util.ArrayList;

public class Blockchain {

	private int dificulty;
	private ArrayList<Transaction> miningPool;
	private ArrayList<Block> blockcahin;
	private int blockReward;

	private User systemUser;

	public Blockchain() throws Exception {

		systemUser = new User(this);
		blockReward = 12;

		blockcahin = new ArrayList<Block>();
		miningPool = new ArrayList<Transaction>();
		Block genesis = new Block();
		blockcahin.add(genesis);
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
		Transaction[] out = new Transaction[miningPool.size() - 1];
		for (int i = 0; i < miningPool.size(); i++) {

		}
		return out;
	}

	public void addBlock(Block newBlock, User user) {
		if (newBlock.isValid(this.getLastBlock(), dificulty)) {
			Transaction t = new Transaction(systemUser, user, blockReward);
			try {
				this.addTransaction(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
