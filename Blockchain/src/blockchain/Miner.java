package blockchain;

public class Miner {

	Blockchain blockchain;
	User user;

	public Miner(Blockchain blockchain, User user) {
		this.blockchain = blockchain;
		this.user = user;
	}

	public Block mine() throws Exception {
		Block lastBlock = blockchain.getLastBlocks();

		Transaction[] miningPool = blockchain.getMiningPool();
		for (int i = 0; i < miningPool.length; i++) {
			if (miningPool[i].verify() == false) {
				throw new IllegalAccessError("miningPool " + i + "  was changed");
			}
		}

		Block block = new Block(miningPool, lastBlock.getHash());

		block.updataTime();
		block.calcHash();

		while (greaterThan(blockchain.getDificulty(), block.getHash())) {
			block.setNonce(block.getNonce() + 1);
			block.updataTime();
			block.calcHash();
		}
		return block;
	}

	private boolean greaterThan(String a, String b) {
		if (a.length() != b.length()) {
			throw new IllegalArgumentException("the tarays have to be the same size");
		}
		for (int i = 0; i < b.length(); i++) {

		}
		return false;
	}

}
