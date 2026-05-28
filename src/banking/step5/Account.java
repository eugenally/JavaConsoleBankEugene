package banking.step5;





abstract  public class Account  {
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
	    
	    public abstract void showAccInfo();
	    
	    @Override
	    public boolean equals(Object obj) {
	    	if (this == obj) return true;
	        if (!(obj instanceof Account)) return false;
	        Account other = (Account) obj;
	        return this.account.equals(other.account); 
	    }
	    
	    
		
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
