package banking.step6;

public class HighCreditAccount extends Account {
	
    private String grade;  // "A", "B", "C" 문자로 저장
    private double gradeInterast;  // 등급별 추가이율
	
    public HighCreditAccount(String account, String name, int balance, double interast, String grade) {
    	super(account, name, balance, interast);  // 부모 Account 생성자 호출
    	// 문자열 앞뒤 공백을 제거(.trim())하여 등급 비교가 확실하게 작동하도록 안전장치를 추가
    	this.grade = grade.trim();
        
    	// 등급별 추가이율 설정
        if (grade.equals("A"))      this.gradeInterast = ICustomDefine.INTERAST_A;
        else if (grade.equals("B")) this.gradeInterast = ICustomDefine.INTERAST_B;
        else if (grade.equals("C")) this.gradeInterast = ICustomDefine.INTERAST_C;
        else                        this.gradeInterast = 0.0;

    }
    @Override
    public double getTotalInterast() {
    	// 기본 이율 + 신용등급 추가 이율 반환
        return getInterast() + gradeInterast;
    }
    @Override
    public void showAccInfo() {
    	System.out.println("계좌번호: "  + getAccount());
    	System.out.println("이름: "     + getName());
    	System.out.println("잔고: "     + getBalance());
    	System.out.println("신용등급: " + grade);   // A/B/C 문자로 출력 gradeToString()
    	// 💡 수정 포인트: getInterast() 대신 최종 합산 이율인 getTotalInterast()를 호출
    	System.out.println("이율: "     + (getTotalInterast()  * 100) + "%");
    }
    
}