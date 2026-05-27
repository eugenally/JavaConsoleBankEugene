package banking.step1;

public class NormalAccount  {
	
	
	public string account;
	public string name;
	public int balance;
	
    public NormalAccount(String account, String name, int balance) {
    	this.account =account;
    	this.name =name;
    	this.balance =balance;
    	

    }
//    public void showAccInfo() {
//        System.out.println("계좌번호: "  + getAccount());
//        System.out.println("이름: "     + getName());
//        System.out.println("잔고: "     + getBalance());
//    }
    public void showAccInfo() {
    	System.out.println("계좌번호: "  + account);
    	System.out.println("이름: "     + name);
    	System.out.println("잔고: "     + balance);
    }
}
