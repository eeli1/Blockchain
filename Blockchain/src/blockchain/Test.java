package blockchain;

public class Test {

	public static void main(String[] args) throws Exception {
		Blockchain b = new Blockchain();
		User u1 = new User(b);
		System.out.println(u1);
	}

}
