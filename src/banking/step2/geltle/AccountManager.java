package banking.step2.geltle;

import java.util.Scanner;





public class AccountManager implements ICustomDefine {
	
	private Account[] myAccounts;
	private int numOfAccounts;
	
	public AccountManager(int num) {
        myAccounts    = new Account[num];
        numOfAccounts = 0;
	}
	
	
	@Override
	public void showMenu() {}

	
	public void makeAccount() {
		System.out.println("1. 일반계좌  2. 신용신뢰계좌");
		int choice = BankingSystemMain.scan.nextInt();  // 
	    BankingSystemMain.scan.nextLine();
	    		
		System.out.print("계좌번호:"); 
		String iAcc = BankingSystemMain.scan.nextLine();
		System.out.print("고객이름:"); 
		String iName = BankingSystemMain.scan.nextLine();
		System.out.print("잔고:"); 
		int iBalance = BankingSystemMain.scan.nextInt();
		BankingSystemMain.scan.nextLine();
		System.out.print("기본이자%(정수형태로입력):");
		double iInterast = BankingSystemMain.scan.nextInt()/100.0;
		BankingSystemMain.scan.nextLine();
		
		if(choice == 1) {
			System.out.println("일반계좌 개설:");
//			NormalAccount na = new NormalAccount(iAcc, iName, iBalance, iInterast);
			//High인스턴스를 list에추가
//			myAccounts[numOfAccounts++] = na; 
			 // 신용계좌 배열에 저장
	        myAccounts[numOfAccounts++] = new NormalAccount(iAcc, iName, iBalance, iInterast);
	        System.out.println("일반계좌 개설 완료");
 		
		}
		else if (choice == 2) {
			System.out.print("신용신뢰계좌: ");
			System.out.print("신용등급(A,B,C등급): ");
            String grade = BankingSystemMain.scan.nextLine().toUpperCase();  // 문자 입력


//		    // 등급 선택 추가
//		    System.out.println("신용등급 선택");
//		    System.out.println(ICustomDefine.GRADE_A + ". A등급 (" + (ICustomDefine.INTERAST_A * 100) + "%)");
//		    System.out.println(ICustomDefine.GRADE_B + ". B등급 (" + (ICustomDefine.INTERAST_B * 100) + "%)");
//		    System.out.println(ICustomDefine.GRADE_C + ". C등급 (" + (ICustomDefine.INTERAST_C * 100) + "%)");
//
//		    int grade = BankingSystemMain.scan.nextInt();
//		    BankingSystemMain.scan.nextLine();
            
            
            if (!grade.equals("A") && !grade.equals("B") && !grade.equals("C")) {
                System.out.println("잘못된 등급입니다. (A/B/C만 입력 가능)");
                return;
            }

		    // double interast 파라미터 → int grade 파라미터로 변경
		    myAccounts[numOfAccounts++] =
		    		new HighCreditAccount(iAcc, iName, iBalance, iInterast, grade);
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
	            
	            //합산, 잔고
	            int newBalance = myAccounts[i].getBalance() + amount;
	            double totalInterast = myAccounts[i].getTotalInterast();  // 기본+등급이율
	            newBalance = (int)(newBalance * (1 + totalInterast));
	            myAccounts[i].setBalance(newBalance); // 반드시 저장
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
			System.out.println("-------------");
	        myAccounts[i].showAccInfo();//각계좌 정보 출력
	        System.out.println("-------------");
			
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}	

	

}
