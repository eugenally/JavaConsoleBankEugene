package banking.step3;

public class HighCreditAccount extends Account implements ICustomDefine{
	
//    private double interast;  // 최종 이율 (기본이율 + 등급별 추가이율)
//    private int grade;        // 신용등급 (1=A, 2=B, 3=C)
    
    private String grade;  // "A", "B", "C" 문자로 저장
    private double gradeInterast;  // 등급별 추가이율
	
    public HighCreditAccount(String account, String name, int balance, double interast, String grade) {
    	super(account, name, balance, interast);  // 부모 Account 생성자 호출
    	this.grade = grade;
        
    	// 등급에 따라 이율 자동 설정
//        if (grade == ICustomDefine.GRADE_A) {
//            this.interast = ICustomDefine.INTERAST_A;
//        } else if (grade == ICustomDefine.GRADE_B) {
//            this.interast = ICustomDefine.INTERAST_B;
//        } else if (grade == ICustomDefine.GRADE_C) {
//            this.interast = ICustomDefine.INTERAST_C;
//        }
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

    public void showAccInfo() {
    	System.out.println("계좌번호: "  + getAccount());
    	System.out.println("이름: "     + getName());
    	System.out.println("잔고: "     + getBalance());
    	System.out.println("신용등급: " + grade);   // A/B/C 문자로 출력 gradeToString()
    	System.out.println("이율: "     + (getInterast()  * 100) + "%");
    }
//    private String gradeToString() {
//        if (grade == ICustomDefine.GRADE_A) return "A";
//        if (grade == ICustomDefine.GRADE_B) return "B";
//        if (grade == ICustomDefine.GRADE_C) return "C";
//        return "알 수 없음";
//    }
//    private static double gradeToInterast(int grade) {
//        if (grade == ICustomDefine.GRADE_A) return ICustomDefine.INTERAST_A;
//        if (grade == ICustomDefine.GRADE_B) return ICustomDefine.INTERAST_B;
//        if (grade == ICustomDefine.GRADE_C) return ICustomDefine.INTERAST_C;
//        return 0.0;
    	
    
}