package banking.step4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;





public class AccountManager implements ICustomDefine {

//	private Account[] myAccounts;
//	private int numOfAccounts;
	private ArrayList<Account> myAccounts;
//	private HashSet<Account> myAccounts;
	
	// 인자(num)를 받지 않는 기본 생성자로 수정
	public AccountManager() {
//        myAccounts    = new Account[num];
//        numOfAccounts = 0;
		myAccounts    = new ArrayList<>();
//		myAccounts    = new HashSet<Account>();
	}
	// 계좌번호로 계좌 찾는 헬퍼 메서드
	private Account findByAccount(String accNum) {
        for (Account acc : myAccounts) {
            if (acc.getAccount().equals(accNum)) return acc;
        }
        return null;
	}
	@Override
	public void showMenu() {
		  System.out.println("-----Menu------");
		  System.out.println(MAKE     + ".계좌개설");
		  System.out.println(DEPOSIT  + ".입\t금");
		  System.out.println(WITHDRAW + ".출\t금");
		  System.out.println(INQUIRE  + ".계좌정보출력");
		  System.out.println(DELETE  + ".계좌삭제");
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
		
	    // 새 계좌 인스턴스 생성
	    Account newAcc = null;
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
			return;
		}
			// 중복 체크 — 계좌번호 동일 여부 직접 확인 (ArrayList이므로)
		Account existing = findByAccount(iAcc);  // 아래 헬퍼 메서드 사용

	    if (existing != null) {
	        // 중복 계좌 발견
	        System.out.println("중복계좌발견됨. 덮어쓸까요? (y or n)");
	        String answer = BankingSystemMain.scan.nextLine().toLowerCase();

	        if (answer.equals("y")) {
	            myAccounts.remove(existing);  // 기존 계좌 삭제
	            myAccounts.add(newAcc);     // 새 계좌 추가
	            System.out.println("기존 계좌를 덮어썼습니다.");
	        } else {
	            System.out.println("기존 계좌 정보를 유지합니다.");
	        }
	    }
	    else {
	        System.out.println("계좌계설이 완료되었습니다.");
	   	    
		}
		
	}
	@Override
	public void depositMoney() {
	    System.out.print("입금할 계좌번호: ");
	    String iAcc = BankingSystemMain.scan.nextLine();

//	    for (int i = 0; i < numOfAccounts; i++) {
//	        if (myAccounts[i].getAccount().equals(accNum)) {
//	    for (Account acc : myAccounts) {
	    	
	    Iterator<Account> itr = myAccounts.iterator();
	    Account acc = null;
	    
	    while (itr.hasNext()) {
	        Account temp = itr.next();	
		    if (acc.getAccount().equals(iAcc)) {
		    	acc = temp;   // 찾은 계좌 저장
	            break;        // 찾았으면 즉시 탈출
		    }
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
//		            int newBalance = acc.getBalance() + amount;
//		            double totalInterast = acc.getTotalInterast();  // 기본+등급이율
//		            newBalance = (int)(newBalance * (1 + totalInterast));
//		            acc.setBalance(newBalance); // 반드시 저장
//		            System.out.println("입금 완료. 잔고: " + acc.getBalance());
        
            int interest = (int)(acc.getBalance() * acc.getTotalInterast());
            acc.setBalance(acc.getBalance() + interest + amount);
            System.out.println("입금 완료. 잔고: " + acc.getBalance());
            return;
    
		    }
	    	catch (java.util.InputMismatchException e) {
                // 문자 입력 시
                System.out.println("[입력오류] 숫자만 입력 가능합니다.");
                BankingSystemMain.scan.nextLine();  // 버퍼 비우기
	        }
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	
	@Override
	public void withdrawMoney() {
		System.out.print("출금할 계좌번호: ");
	    String iAcc = BankingSystemMain.scan.nextLine();
	    
//	    for (Account acc : myAccounts) {
	    Iterator<Account> itr = myAccounts.iterator();
	    while (itr.hasNext()) {
	        Account acc = itr.next();
	        if (acc.getAccount().equals(iAcc)) {
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
	                if (acc.getAccount().equals(iAcc)) {
	                    
	                    acc.setBalance(acc.getBalance() - amount);
	                    System.out.println("출금 완료. 잔고: " + acc.getBalance());
	                    return;
		                }
		            
		            
		          
	        	}
	        	catch (java.util.InputMismatchException e) {
	                System.out.println("[입력오류] 숫자만 입력 가능합니다.");
	                BankingSystemMain.scan.nextLine();  // 버퍼 비우기
	                return;
	            }
//	        	acc.setBalance(acc.getBalance() - amount);
//		        System.out.println("출금 완료. 잔고: " + acc.getBalance());
//		        return;
		        
               
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

	    
//	    for (Account acc : myAccounts) {
	    Iterator<Account> itr = myAccounts.iterator();
	    while (itr.hasNext()) {
	    	itr.next().showAccInfo();
	        System.out.println("--------------------");
	    }
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}	
// 삭제 추가
	public void deleteAcc() {
		//System.out.println("##deleteInfo 호출됨");
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요");
		String deleteAccount = scan.nextLine();
		//삭제할 인스턴스 확인용 변수
		int deleteIndex = -1;
		//확장 for문으로 갯수만큼 반복
				
//		for(Account acc : myAccounts) {
			//삭제한 이름이 있는지 검색
		Iterator<Account> itr = myAccounts.iterator();
	    while (itr.hasNext()) {
	        Account acc = itr.next();
			if(deleteAccount.equals(acc.getName())) {

				itr.remove(); // 💡 Iterator의 remove()를 사용해야 안전하게 지워집니다.
				//참조값을통해 삭제
//				myAccounts.remove(acc);
				
				//샂제되었다면 확인 용으로 변수를 변경
				deleteIndex = 0;
				//삭제 되면 즉시 탈출
				break;
			}
		}
	}
}
