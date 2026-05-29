package banking.step6;

public class NormalAccount extends Account {
	
    public NormalAccount(String account, String name, int balance, double interast) {
    	
    	super(account, name, balance, interast); 

    }
    @Override
    public void showAccInfo() {
        System.out.println("계좌번호: "  + getAccount());
        System.out.println("이름: "     + getName());
        System.out.println("잔고: "     + getBalance());
        System.out.println("이율: "     + (getInterast() * 100) + "%");
    }

    
}
