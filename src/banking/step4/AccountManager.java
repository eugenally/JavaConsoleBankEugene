package banking.step4;

import java.util.ArrayList;
import java.util.Scanner;





public class AccountManager implements ICustomDefine {

//	private Account[] myAccounts;
//	private int numOfAccounts;
	private ArrayList<Account> myAccounts;
	
	
	public AccountManager(int num) {
//        myAccounts    = new Account[num];
//        numOfAccounts = 0;
		myAccounts    = new ArrayList<>();
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
	        myAccounts.add(
	        		new NormalAccount(iAcc, iName, iBalance, iInterast));
	        System.out.println("일반계좌 개설 완료");
 		
		}
		else if (choice == 2) {
			System.out.print("신용신뢰계좌: ");
			System.out.print("신용등급(A,B,C등급): ");
            String grade = BankingSystemMain.scan.nextLine().toUpperCase();  // 문자 입력


            
            
            if (!grade.equals("A") && !grade.equals("B") && !grade.equals("C")) {
                System.out.println("잘못된 등급입니다. (A/B/C만 입력 가능)");
                return;
            }

		    myAccounts.add(
		    		new HighCreditAccount(iAcc, iName, iBalance, iInterast, grade));
		    System.out.println("신용계좌 개설 완료");
		} 
		else {	
			System.out.println("잘못된 선택입니다.");
	    
		}
		
	}
	@Override
	public void depositMoney() {
	    System.out.print("입금할 계좌번호: ");
	    String iAcc = BankingSystemMain.scan.nextLine();

//	    for (int i = 0; i < numOfAccounts; i++) {
//	        if (myAccounts[i].getAccount().equals(accNum)) {
	    for (Account acc : myAccounts) {
	        if (acc.getAccount().equals(iAcc)) {
	        	try {
		            System.out.print("입금액: ");
		            int amount = BankingSystemMain.scan.nextInt();//
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
		            int newBalance = acc.getBalance() + amount;
		            double totalInterast = acc.getTotalInterast();  // 기본+등급이율
		            newBalance = (int)(newBalance * (1 + totalInterast));
		            acc.setBalance(newBalance); // 반드시 저장
		            System.out.println("입금 완료. 잔고: " + acc.getBalance());
		            
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
	    
	    for (Account acc : myAccounts) {
	        if (acc.getAccount().equals(accNum)) {
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
	                if (acc.getBalance() < amount) {
	                    System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
	                    System.out.print("YES / NO : ");
	                    String answer = BankingSystemMain.scan.nextLine().toUpperCase();

	                    if (answer.equals("YES")) {
	                        // 전액 출금
	                        int fullAmount = acc.getBalance();
	                        acc.setBalance(0);
	                        System.out.println(fullAmount + "원 전액 출금되었습니다.");
	                    } else {
	                        // 출금 취소
	                        System.out.println("출금요청이 취소되었습니다.");
	                    }
	                    return;
	                }
		            
		            
		            acc.setBalance(acc.getBalance() - amount);
		            System.out.println("출금 완료. 잔고: " + acc.getBalance());
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
		// 리스트가 비어있는지 확인 (선택사항이지만 권장)
	    if (myAccounts.isEmpty()) {
	        System.out.println("등록된 계좌가 없습니다.");
	        return;
	    }

	    // 향상된 for문으로 모든 계좌 출력
	    for (Account acc : myAccounts) {
	        acc.showAccInfo();
	        System.out.println("--------------------");
	    }
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}	

	

}
