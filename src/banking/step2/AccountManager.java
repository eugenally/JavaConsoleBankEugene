package banking.step2;

import java.util.Scanner;



//AccountManager.java — ICustomDefine을 여기서 구현

public class AccountManager implements ICustomDefine{
	
	private Account[] myAccounts;
	private int numOfAccounts;
	 // makeAccount, depositMoney, withdrawMoney, showAccInfo, showMenu 구현


	public AccountManager(int num) {
	    myAccounts = new Account[num];
	    numOfAccounts = 0;
	}
	
	@Override
	public void showMenu() {
	}
	
	public void makeAccount() {
		System.out.println("1. 일반계좌  2. 신용계좌");
	    int choice = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();
	    
		String iMake,iName;
		int iBalance;
		System.out.println("계좌번호:");iMake = BankingSystemMain.scan.nextLine();
		System.out.println("고객이름:");iName = BankingSystemMain.scan.nextLine();
		System.out.printf("잔고:");iBalance = BankingSystemMain.scan.nextInt();
		
		if(choice==1) {
			
			NormalAccount na = new NormalAccount(iMake, iName, iBalance);
			//High인스턴스를 list에추가
			myAccounts[numOfAccounts++] = na; 
 		}
		else if (choice == 2) {
			System.out.println("신용등급 선택 (1.A등급 2.B등급 3.C등급): ");
		    int gradeChoice = BankingSystemMain.scan.nextInt();
//		    BankingSystemMain.scan.nextLine();
//	        System.out.print("이자율: ");
//	        double interast = BankingSystemMain.scan.nextDouble();
//	        BankingSystemMain.scan.nextLine();
		    
		    System.out.println("신용등급 선택");
		    System.out.println(GRADE_A + ".A등급(" + (INTEREST_A*100) + "%)");
		    System.out.println(GRADE_B + ".B등급(" + (INTEREST_B*100) + "%)");
		    System.out.println(GRADE_C + ".C등급(" + (INTEREST_C*100) + "%)");

		    int gradeChoice = BankingSystemMain.scan.nextInt();
		    BankingSystemMain.scan.nextLine();

		    double interast;  // 선택된 이자율 담을 변수

		    if (gradeChoice == GRADE_A) {
		        interast = INTEREST_A;
		    } else if (gradeChoice == GRADE_B) {
		        interast = INTEREST_B;
		    } else if (gradeChoice == GRADE_C) {
		        interast = INTEREST_C;
		    } else {
		        System.out.println("잘못된 등급 선택");
		        return;
		    }

		    myAccounts[numOfAccounts++] =
		        new HighCreditAccount(iMake, iName, iBalance, interast);
		    System.out.println("신용계좌 개설 완료 / 이자율: " + (interast*100) + "%");
		    }
		    
		    
		    
	        // 신용계좌 배열에 저장
	        myAccounts[numOfAccounts++] = new HighCreditAccount(iMake, iName, iBalance, interast);
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
