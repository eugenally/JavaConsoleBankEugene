package banking.step4;



public interface ICustomDefine {
	// 메뉴 번호 상수
    int MAKE=1, DEPOSIT=2, WITHDRAW=3, INQUIRE=4, EXIT=5;

    // 신용등급 상수
    int GRADE_A = 1, GRADE_B = 2, GRADE_C = 3;

    // 등급별 이자율 상수 (double)
    double INTERAST_A = 0.07;  // 7%
    double INTERAST_B = 0.04;  // 4%
    double INTERAST_C = 0.02;  // 2%

    // 메서드 규칙 정의
    void showMenu();
    void makeAccount();
    void depositMoney();
    void withdrawMoney();
    void showAccInfo();
	

	
}
