package banking.step3;

import java.util.Scanner;





public class AccountManager implements ICustomDefine {
	
	private Account[] myAccounts;
	private int numOfAccounts;
	
	public AccountManager(int num) {
        myAccounts    = new Account[num];
        numOfAccounts = 0;
	}
	
	
	@Override
	public void showMenu() {
		  System.out.println("-----Menu------");
		  System.out.println(MAKE     + ".계좌개설");
		  System.out.println(DEPOSIT  + ".입\t금");
		  System.out.println(WITHDRAW + ".출\t금");
		  System.out.println(INQUIRE  + ".계좌정보출력");
		  System.out.println(EXIT     + ".프로그램종료");
		  System.out.print("선택:");
		
	}

	@Override
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
	@Override
	public void depositMoney() {
	    System.out.print("입금할 계좌번호: ");
	    String accNum = BankingSystemMain.scan.nextLine();

	    for (int i = 0; i < numOfAccounts; i++) {
	        if (myAccounts[i].getAccount().equals(accNum)) {
	        	try {
		            System.out.print("입금액: ");
		            int amount = BankingSystemMain.scan.nextInt();
		            BankingSystemMain.scan.nextLine();
		            
		            if(amount < 0) {
		            	System.out.println("[입금오류] 음수는 입금할 수 없습니다.");
	                    return;
		            }
		            if (amount % 500 != 0) {
	                    System.out.println("[입금오류] 입금액은 500원 단위만 가능합니다.");
	                 
	                    return;
	                }
		            //합산, 잔고
		            int newBalance = myAccounts[i].getBalance() + amount;
		            double totalInterast = myAccounts[i].getTotalInterast();  // 기본+등급이율
		            newBalance = (int)(newBalance * (1 + totalInterast));
		            myAccounts[i].setBalance(newBalance); // 반드시 저장
		            System.out.println("입금 완료. 잔고: " + myAccounts[i].getBalance());
		            
		        }
		        catch (java.util.InputMismatchException e) {
	                // 문자 입력 시
	                System.out.println("[입력오류] 숫자만 입력 가능합니다.");
	                BankingSystemMain.scan.nextLine();  // 버퍼 비우기
		        }
	        	return;
		    }
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	
	@Override
	public void withdrawMoney() {
		System.out.print("출금할 계좌번호: ");
	    String accNum = BankingSystemMain.scan.nextLine();
	    
	    for (int i = 0; i < numOfAccounts; i++) {
	        if (myAccounts[i].getAccount().equals(accNum)) {
	        	try {
		            System.out.print("출금액: ");
		            int amount = BankingSystemMain.scan.nextInt();
		            BankingSystemMain.scan.nextLine();
		            
		            // 음수 체크 (if문)
	                if (amount < 0) {
	                    System.out.println("[출금오류] 음수는 출금할 수 없습니다.");
	                    return;
	                }

	                // 1000원 단위 체크 (if문)
	                if (amount % 1000 != 0) {
	                    System.out.println("[출금오류] 출금액은 1000원 단위만 가능합니다.");
	                    System.out.println("예) 1000, 2000원 가능 / 1100원 불가");
	                    return;
	                }

	                // 잔고 부족 처리
	                if (myAccounts[i].getBalance() < amount) {
	                    System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
	                    System.out.print("YES / NO : ");
	                    String answer = BankingSystemMain.scan.nextLine().toUpperCase();

	                    if (answer.equals("YES")) {
	                        // 전액 출금
	                        int fullAmount = myAccounts[i].getBalance();
	                        myAccounts[i].setBalance(0);
	                        System.out.println(fullAmount + "원 전액 출금되었습니다.");
	                    } else {
	                        // 출금 취소
	                        System.out.println("출금요청이 취소되었습니다.");
	                    }
	                    return;
	                }
		            
		            
		            myAccounts[i].setBalance(myAccounts[i].getBalance() - amount);
		            System.out.println("출금 완료. 잔고: " + myAccounts[i].getBalance());
		            return;
	        	}
	        	catch (java.util.InputMismatchException e) {
	                System.out.println("[입력오류] 숫자만 입력 가능합니다.");
	                BankingSystemMain.scan.nextLine();  // 버퍼 비우기
	            }
	        }
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	    
	@Override
	public void showAccInfo() {
		for (int i = 0; i < numOfAccounts; i++) {
			System.out.println("-------------");
	        myAccounts[i].showAccInfo();//각계좌 정보 출력
	        System.out.println("-------------");
			
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}	

	

}
