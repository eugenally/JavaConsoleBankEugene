package banking.step2;

public class NormalAccount extends Account {
    public NormalAccount(String iMake, String iName, int iBalance) {
        super(iMake, iName, iBalance);
    }
    public void showAccInfo() {
        System.out.println("계좌번호: "  + getAccount());
        System.out.println("이름: "     + getName());
        System.out.println("잔고: "     + getBalance());
    }
}
