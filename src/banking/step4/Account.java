package banking.step4;





abstract  public class Account implements ICustomDefine {
		//
		private String account;
		private String name;
		private int balance;  // String → int
		private double interast;
		
		
		public Account(String account, String name, int balance, double interast) {
	        this.account = account;
	        this.name    = name;
	        this.balance = balance;
	        this.interast = interast;
	    }
		
		// equals()를 오버라이딩하면 hashCode()도 반드시 같이 오버라이딩
	    @Override
	    public int hashCode() {
	        return account.hashCode();  // 계좌번호 기반 해시코드
	    }
	    
	    
	    
	    
	    
		@Override
	    public abstract void showAccInfo();   // 하위 클래스가 반드시 구현
		
		// =======================================================
	    // [우회 방법] ICustomDefine의 관리자 기능들을 빈 상태로 오버라이딩합니다.
	    // 이렇게 하면 자식 클래스(HighCreditAccount 등)에서 에러가 발생하지 않습니다.
	    // =======================================================
	    @Override public void showMenu() {}
	    @Override public void makeAccount() {}
	    @Override public void depositMoney() {}
	    @Override public void withdrawMoney() {}
	    // =======================================================
	    /*
	    맴버변수 name이 private로 선언되어 외부에서는 접근을 허용하지 아
	    않으므로 ,getter를 통해 접근 할 수있도록 해야한다.
	    */
	    public String getAccount()          { return account; }
	    public String getName()             { return name; }
	    public int    getBalance()          { return balance; }
	    public void   setBalance(int bal)   { this.balance = bal; }
	    public double getInterast()         { return interast; }
	    public void  setInterast(double inte)  { this.interast = inte; }

		public double getTotalInterast() {
			
			return getInterast();//기본 이율 반환
		}
		
		
		
	
		

		
}		
