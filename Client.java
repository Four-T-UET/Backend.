public class Client{
	private Wallet wallet;
	private Item[] inventory;
	public void deposit(double amount){
		this.wallet.setBalance(this.wallet.getBalance()+amount);
	}