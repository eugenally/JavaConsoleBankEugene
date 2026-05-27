package banking.step1;

public class NormalAccount extends Account {
	
    public NormalAccount(String account, String name, int balance) {
    	
    	super(account, name, balance); 

    }
    public void showAccInfo() {
        System.out.println("계좌번호: "  + getAccount());
        System.out.println("이름: "     + getName());
        System.out.println("잔고: "     + getBalance());
    }

    
}
