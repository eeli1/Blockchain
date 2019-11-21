package blockchain;

public class Miner {

	Blockchain blockchain;
	User user;

	public Miner(Blockchain blockchain, User user) {
		this.blockchain = blockchain;
		this.user = user;
	}

	public void mine() throws Exception {
		if (blockchain.isValid()) {
			Block lastBlock = blockchain.getLastBlock();

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
			blockchain.addBlock(block, user);
		} else {
			throw new IllegalArgumentException("mining on in valid chain");
		}
	}

	

}
