package banking.step5;

public class HighCreditAccount extends Account {
	
    private String grade;  // "A", "B", "C" 문자로 저장
    private double gradeInterast;  // 등급별 추가이율
	
    public HighCreditAccount(String account, String name, int balance, double interast, String grade) {
    	super(account, name, balance, interast);  // 부모 Account 생성자 호출
    	this.grade = grade;
        
    	// 등급별 추가이율 설정
        if (grade.equals("A"))      this.gradeInterast = ICustomDefine.INTERAST_A;
        else if (grade.equals("B")) this.gradeInterast = ICustomDefine.INTERAST_B;
        else if (grade.equals("C")) this.gradeInterast = ICustomDefine.INTERAST_C;
        else                        this.gradeInterast = 0.0;

    }
    @Override
    public double getTotalInterast() {
        return getInterast() + gradeInterast;
    }
    @Override
    public void showAccInfo() {
    	System.out.println("계좌번호: "  + getAccount());
    	System.out.println("이름: "     + getName());
    	System.out.println("잔고: "     + getBalance());
    	System.out.println("신용등급: " + grade);   // A/B/C 문자로 출력 gradeToString()
    	System.out.println("이율: "     + (getInterast()  * 100) + "%");
    }
    
}