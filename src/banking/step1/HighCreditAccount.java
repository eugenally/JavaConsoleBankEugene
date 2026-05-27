package banking.step1;

public class HighCreditAccount extends Account {
	
	
	private double interast;  // 이자율 필드 추가
	
    public HighCreditAccount(String account, String name, int balance, double interast) {
    	super(account, name, balance);  // 부모 Account 생성자 호출
        this.interast = interast;       // 이자율만 여기서 처리
    	

    }

    public void showAccInfo() {
    	System.out.println("계좌번호: "  + account);
    	System.out.println("이름: "     + name);
    	System.out.println("잔고: "     + balance);
    	System.out.println("이율: "     + (interast * 100) + "%");
    }
}