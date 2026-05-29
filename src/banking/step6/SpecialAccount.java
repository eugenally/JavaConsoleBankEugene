package banking.step6;

public class SpecialAccount  extends NormalAccount {
	
	// 💡 현재 입금 횟수를 저장할 멤버 변수 (계좌 개설 시 0으로 초기화)
    private int depositCount = 0;
	
    public SpecialAccount(String account, String name, int balance, double interast) {
    	super(account, name, balance, interast); 
    }
    
 // 💡 입금 횟수를 1 증가시키는 메서드 (입금 성공 시 호출)
    public void incrementDepositCount() {
        this.depositCount++;
    }

    // 💡 현재 몇 회차 입금인지 가져오는 Getter
    public int getDepositCount() {
        return this.depositCount;
    
    }
    @Override
    public void showAccInfo() {
    	super.showAccInfo();
    	// 💡 특판계좌만의 추가 정보 디스플레이
    	System.out.println("입금회차: " + this.depositCount + "회차");
    }

    
}