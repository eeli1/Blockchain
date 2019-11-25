package blockchain;

public class Miner {

	Blockchain blockchain;
	User user;

	public Miner(Blockchain blockchain, User user) {
		this.blockchain = blockchain;
		this.user = user;
	}

	public void mine() throws Exception {
		if (blockchain.isValid() && blockchain.verifyPool()) {
			Block lastBlock = blockchain.getLastBlock();

			Transaction[] miningPool = blockchain.getMiningPool();

			Block block = new Block(miningPool, lastBlock.getHash(), blockchain);

			block.updataTime();
			block.calcHash();

			while (!blockchain.smallEnough(block.getHash())) {
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
