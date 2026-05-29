package banking.step6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	}

	@Override
	public void makeAccount() {
		System.out.println("1. 일반계좌  2. 신용신뢰계좌  3. 특판계좌");
		int choice = BankingSystemMain.scan.nextInt();  // 
		BankingSystemMain.scan.nextLine(); // 버퍼 청소
	    		
		System.out.print("계좌번호:"); 
		String iAcc = BankingSystemMain.scan.nextLine();
		System.out.print("고객이름:"); 
		String iName = BankingSystemMain.scan.nextLine();
		System.out.print("잔고:"); 
		int iBalance = BankingSystemMain.scan.nextInt();
		BankingSystemMain.scan.nextLine(); // 💡 [필수] 잔고 입력 후 버퍼 청소!
		
		double iInterast = 0.0;
		
	    // 새 계좌 인스턴스 생성
	    Account newAcc = null;
		if(choice == 1) {
			System.out.println("일반계좌 개설:");
			
			System.out.print("기본이자%(정수형태로입력):");
			iInterast = BankingSystemMain.scan.nextInt()/100.0;
			BankingSystemMain.scan.nextLine();// 💡 [필수] 이율 입력 후 버퍼 청소!
			
			
			
//	        myAccounts.add(
//	        		new NormalAccount(iAcc, iName, iBalance, iInterast));
			newAcc =
					new NormalAccount(iAcc, iName, iBalance, iInterast);
	        System.out.println("일반계좌 개설 완료");
 		
		}
		if (choice == 2) {
			System.out.print("신용신뢰계좌: ");
			System.out.print("신용등급(A,B,C등급): ");
            String grade = BankingSystemMain.scan.nextLine().toUpperCase();  // 문자 입력


            
            
            if (!grade.equals("A") && !grade.equals("B") && !grade.equals("C")) {
                System.out.println("잘못된 등급입니다. (A/B/C만 입력 가능)");
                return;
            }

		    newAcc =
		    		new HighCreditAccount(iAcc, iName, iBalance, iInterast, grade);
		    System.out.println("신용계좌 개설 완료");
		} 
		else if(choice == 3) {
			System.out.println("특판계좌 개설:");
		    System.out.print("기본이자%(정수형태로입력):");
		    iInterast = BankingSystemMain.scan.nextInt() / 100.0;
		    BankingSystemMain.scan.nextLine(); 
		    
		    newAcc = new SpecialAccount(iAcc, iName, iBalance, iInterast);
		    System.out.println("특판계좌 개설 완료");
		}
		else {
			System.out.println("잘못된 선택입니다.");
			return;
		}
			// 중복 체크 — 계좌번호 동일 여부 직접 확인 (ArrayList이므로)
//		Account existing = findByAccount(iAcc);  // 아래 헬퍼 메서드 사용
		// 중복 체크 로직 (Iterator 방식 유지)
				Account existing = null;
				Iterator<Account> checkItr = myAccounts.iterator();
				while(checkItr.hasNext()) {
					Account acc = checkItr.next();
					if(acc.getAccount().equals(iAcc)) {
						existing = acc;
						break;
					}
				}

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
	    	// STEP 3 — 중복 없으면 바로 추가
	        myAccounts.add(newAcc);  
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
	 // 1. 컬렉션 요소를 순회하기 위한 Iterator 인터페이스 구현체 생성	
	    Iterator<Account> itr = myAccounts.iterator();
	 // 검색된 계좌 객체를 담을 참조변수 (초기값은 null)
	    Account targetAcc = null;
	    
	    
	    
	    
	    while (itr.hasNext()) {
	        Account acc = itr.next();	// 컬렉션에서 다음 계좌 꺼내기
	     // 입력한 계좌번호와 일치하는 객체를 찾았다면
		    if (acc.getAccount().equals(iAcc)) {
		    	targetAcc = acc;   // 찾은 계좌 저장
	            break;        // 찾았으면 즉시 탈출
		    }
		 }
		  
		 // 3. [핵심 안전장치] while문이 끝났는데도 targetAcc가 여전히 null이라면 계좌가 없는 것임
		if (targetAcc == null) {
				System.out.println("오류: 입력하신 계좌번호(" + iAcc + ")를 찾을 수 없습니다.");
				return; // 메서드 종료 (아래 입금 로직을 실행하지 않고 안전하게 빠져나감)
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
	        // 1. 공통 이자 계산 (Normal, HighCredit, Special 모두 기본적으로 수행)
	        int interest = (int)(targetAcc.getBalance() * targetAcc.getTotalInterast());
	        targetAcc.setBalance(targetAcc.getBalance() + interest + amount);
	        System.out.println("입금 완료. 잔고: " + targetAcc.getBalance());
	        
	      // B. '최종 입금 대상 금액'을 담을 변수 선언 (기본값 = 이자 + 사용자가 입력한 입금액)
	     			int totalDeposit = interest + amount;
	        
	        // 2. 💡 찾은 계좌가 '특판계좌(SpecialAccount)'인지 확인
	        if (targetAcc instanceof SpecialAccount) {
	            SpecialAccount specAcc = (SpecialAccount) targetAcc; // 형변환
	            
	            // 입금 회차 1 증가 (이제 이번 입금이 몇 회차인지 반영됨)
	            specAcc.incrementDepositCount(); 
	            
	            // 짝수 번째 입금인지 판별 (2, 4, 6...)
	            if (specAcc.getDepositCount() % 2 == 0) {
	                totalDeposit += 500; // 💡 축하금 500원 추가 수당 지급!
	                System.out.println("🎉 [특판축하금] 짝수번째 입금 기념 500원이 추가 지급되었습니다!");
	            }
	        }
	        // 3. 최종 계산된 금액을 잔고에 반영
	        targetAcc.setBalance(targetAcc.getBalance() + totalDeposit);
	        System.out.println("입금 완료되었습니다.");

	    }
    	catch (java.util.InputMismatchException e) {
            // 문자 입력 시
            System.out.println("[입력오류] 숫자만 입력 가능합니다.");
            BankingSystemMain.scan.nextLine();  // 버퍼 비우기
        
	    }
	    System.out.println("계좌를 찾을 수 없습니다.");
	}
	
	@Override
	public void withdrawMoney() {
		System.out.print("출금할 계좌번호: ");
	    String iAcc = BankingSystemMain.scan.nextLine();
	    
	 // 1. Iterator를 통해 목록에서 일치하는 계좌 검색
	    Iterator<Account> itr = myAccounts.iterator();
	    Account targetAcc = null;
	    
	    while (itr.hasNext()) {
	    	Account acc = itr.next();
	        if (acc.getAccount().equals(iAcc)) {
	        	targetAcc = acc;
	            break;
	        }
	    }
	 // 2. 💡 [안전장치 1] 계좌를 찾지 못했다면 여기서 즉시 차단하고 메서드 종료
	    if (targetAcc == null) {
	    	System.out.println("오류: 입력하신 계좌번호를 찾을 수 없습니다.");
	    	return;
	    }    	
	        	
	 // 3. 계좌가 확실히 존재할 때만 출금 금액 입력 및 연산 진행  	
	    try {	
            System.out.print("출금액: ");
            int amount = BankingSystemMain.scan.nextInt();
            BankingSystemMain.scan.nextLine();// 💡 [필수] nextInt() 직후 버퍼 청소!
            
            // 음수 체크 (if문)
            if (amount < 0) {
                System.out.println("[출금오류] 음수는 출금할 수 없습니다.");
                return;
            }

            // 1000원 단위 체크 (if문)
            if (amount % 1000 != 0) {
                System.out.println("[출금오류] 출금액은 1000원 단위만 가능합니다.");
                return;
            }

            // 잔고 부족 처리
            if (targetAcc.getBalance() < amount) {
                System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
                System.out.print("YES / NO : ");
                String answer = BankingSystemMain.scan.nextLine().toUpperCase();

                if (answer.equals("YES")) {
                    // 전액 출금
                    int fullAmount = targetAcc.getBalance();
                    targetAcc.setBalance(0);
                    System.out.println(fullAmount + "원 전액 출금되었습니다.");
                } 
                else {
                    // 출금 취소
                    System.out.println("출금요청이 취소되었습니다.");
                }
                
                    return;
	            }
            if (targetAcc.getAccount().equals(iAcc)) {
            	targetAcc.setBalance(targetAcc.getBalance() - amount);
                System.out.println("출금 완료. 잔고: " + targetAcc.getBalance());
            } 
	    
        }  
    	catch (java.util.InputMismatchException e) {
            System.out.println("[입력오류] 숫자만 입력 가능합니다.");
            BankingSystemMain.scan.nextLine();  // 버퍼 비우기
            return;
    	}
	   
    }
        	
//	        	acc.setBalance(acc.getBalance() - amount);
//		        System.out.println("출금 완료. 잔고: " + acc.getBalance());
//		        return;
		        
               
	        
	    
	    
	
	    
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
		
		
		System.out.print("검색할 이름을 입력하세요");
		String deleteAccount = BankingSystemMain.scan.nextLine();
		boolean isDeleted = false;
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
	    if(!isDeleted) {
	    	System.out.println("입력하신 이름의 계좌를 찾을 수 없습니다.");
	    }
	}

	public void saveAccInfo() {
		try {
			//3직렬화를 위한 인스턴스 생성- 스트림 생성 file 로 생성후 object로 감쌈
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(
							"src/banking/step6/myAccount_info.obj"));
//			//1리스트에 저장된  정보를 For문으로 반복
//			for(Account acc : myAccounts) {
//				//2연락처 정보를 저장-데이터 쓰기
//				out.writeObject(acc);
			// ✅ 리스트 통째로 저장
	        out.writeObject(myAccounts);
	        out.close();
	        System.out.println("계좌 정보가 파일에 저장되었습니다.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void readAccInfo() {
		try {
			//3직렬화를 위한 인스턴스 생성- 스트림 생성 file 로 생성후 object로 감쌈
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(
							"src/banking/step6/myAccount_info.obj"));
			myAccounts = (ArrayList<Account>) in.readObject();
			//몇개의 정보인지 알 수 없으므로 무한루프 로 구성
			while(true) {
//				//Object 타입으로 저장된 정보를 인출 한 후 다운캐스팅
//				Account acc = (Account)in.readObject();
//				myAccounts.add(acc);
				try {
	                Account acc = (Account) in.readObject();
	                myAccounts.add(acc);
	            } 
				catch (EOFException e) {
	                // 파일의 끝에 도달하면 반복문 탈출
	                break;
				}
		
			}
			System.out.println("기존 계좌 정보를 파일에서 성공적으로 불러왔습니다.");
		}
		catch (IOException | ClassNotFoundException e) {
			System.out.println("파일을 읽어오는 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
	
}
