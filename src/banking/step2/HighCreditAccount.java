package banking.step2;

public class HighCreditAccount extends Account {
	
	private double interest;
	
	public HighCreditAccount(String iMake, String iName, int iBalance, double interast) {
		super(iMake, iName, iBalance);
		this.interest = interest;
	}

	public void showAccInfo() {
        System.out.println("계좌번호: " + getAccount());
        System.out.println("이름: " + getName());
        System.out.println("잔액: " + getBalance());
        System.out.println("이자율: " + interest);
    }

}
