package banking.step1;

import java.util.Scanner;





public class AccountManager {
	// 1. 객체 배열 선언
	private HighCreditAccount[] highCreditAccounts;
	private int HighCreditAccount;
	private NormalAccount[] normalAccounts;
	private int NormalAccount;
	
	
	// 2. 객체 배열 생성 (생성자에서)
	public AccountManager(int num) {
		highCreditAccounts = new HighCreditAccount[num];
	    numOfhighCreditAccounts = 0;
	    normalAccounts = new NormalAccount[num];
	    numOfnormalAccounts = 0;
	}
	
	@Override
	public void showMenu() {
	}
	
	public void makeAccount(int choice) {
		System.out.println("1. 일반계좌  2. 신용계좌");
	    int choice = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();
	    
	    Scanner scan = new Scanner(System.in);
		String iAcc,iName;
		int iBalance;
		System.out.println("계좌번호:");iAcc = BankingSystemMain.scan.nextLine();
		System.out.println("고객이름:");iName = BankingSystemMain.scan.nextLine();
		System.out.printf("잔고:");iBalance = BankingSystemMain.scan.nextInt();
		
		if(choice==1) {
			System.out.println("일반계좌 개설:");
			NormalAccount na = new NormalAccount(iAcc, iName, iBalance);
			//High인스턴스를 list에추가
			myAccounts[numOfAccounts++] = na; 
			 // 신용계좌 배열에 저장
	        myAccounts[numOfAccounts++] = new NormalAccount(iAcc, iName, iBalance);
	        
 		
		}
		else if (choice == 2) {
			System.out.print("이자율: ");
			double interast = BankingSystemMain.scan.nextDouble();
			BankingSystemMain.scan.nextLine();
			myAccounts[numOfAccounts++] = new HighCreditAccount(iAcc, iName, iBalance, interast);
			System.out.println("신용계좌 개설 완료");
		} 
		else {
	
		 System.out.println("잘못된 선택입니다.");
	    
		}
	}

	public void depositMoney() {
	    System.out.print("입금할 계좌번호: ");
	    String accNum = BankingSystemMain.scan.nextLine();

	    for (int i = 0; i < numOfAccounts; i++) {
	        if (myAccounts[i].getAccount().equals(accNum)) {
	            System.out.print("입금액: ");
	            int amount = BankingSystemMain.scan.nextInt();
	            BankingSystemMain.scan.nextLine();
	            myAccounts[i].setBalance(myAccounts[i].getBalance() + amount);
	            System.out.println("입금 완료. 잔고: " + myAccounts[i].getBalance());
	            return;
	        }
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	
	public void withdrawMoney() {
		System.out.print("출금할 계좌번호: ");
	    String accNum = BankingSystemMain.scan.nextLine();
	    
	    for (int i = 0; i < numOfAccounts; i++) {
	        if (myAccounts[i].getAccount().equals(accNum)) {
	            System.out.print("출금액: ");
	            int amount = BankingSystemMain.scan.nextInt();
	            BankingSystemMain.scan.nextLine();
	            if (myAccounts[i].getBalance() < amount) {
                    System.out.println("잔고 부족!");
                    return;
                }
	            myAccounts[i].setBalance(myAccounts[i].getBalance() - amount);
	            System.out.println("출금 완료. 잔고: " + myAccounts[i].getBalance());
	            return;
	        }
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	    

	public void showAccInfo() {
		for (int i = 0; i < numOfAccounts; i++) {
		     myAccounts[i].showAccInfo();  // 각 계좌의 정보 출력
		     System.out.println("---");
			
		}
	}	

	

}
