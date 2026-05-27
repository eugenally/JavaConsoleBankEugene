package banking.step3;





abstract public class Account implements ICustomDefine {
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
		@Override
	    public abstract void showAccInfo();   // 하위 클래스가 반드시 구현
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
