package banking.step2;

public enum CreditGrade {
    A, B, C;

    // 등급별 이자율 반환
    public double getInterest() {
        switch (this) {
            case A: return 0.07;  // 7%
            case B: return 0.04;  // 4%
            case C: return 0.02;  // 2%
            default: return 0.0;
        }
    }
}