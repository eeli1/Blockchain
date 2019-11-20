package blockchain;

import java.util.ArrayList;

public class Blockchain {

	private String dificulty;
	private ArrayList<Transaction> miningPool;
	private ArrayList<Block> blocks;

	public Blockchain() {
		blocks = new ArrayList<Block>();
		miningPool = new ArrayList<Transaction>();
		Transaction[] data = {};
		Block genesis = new Block(data);
		blocks.add(genesis);
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
		return blocks;
	}

	public Block getLastBlocks() {
		return blocks.get(blocks.size() - 1);
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void addBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the dificulty
	 */
	public String getDificulty() {
		return dificulty;
	}

	/**
	 * @param dificulty the dificulty to set
	 */
	public void setDificulty(String dificulty) {
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

}
