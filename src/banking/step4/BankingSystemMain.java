package banking.step4;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
///깃허브 클론작업 성공
public class BankingSystemMain implements ICustomDefine {

	public static Scanner scan =new Scanner(System.in);
	
	@Override
	public void showMenu(){
		System.out.println("########인터넷 뱅킹(ver04)#########");
		System.out.print(MAKE        +"1.계좌계설 ");
		System.out.println(DEPOSIT   +"2.입금");
		System.out.print(WITHDRAW    +"3.출금 ");
		System.out.println(INQUIRE   +"4.전체계좌정보출력");
		System.out.println(DELETE    +"5.계좌정보삭제");//추가
		System.out.println(EXIT      +"6.프로그램 종료 ");
		System.out.println("메뉴선택>>>> ");
		
	}
	 
	
	public static void main(String[] args) {
		
		AccountManager mng = new AccountManager();
		// 1. BankingSystemMain 인스턴스를 생성합니다.
	    BankingSystemMain mainSystem = new BankingSystemMain();
	    
		while(true) {
//			mng.showMenu(); // menuShow() 대신 인터페이스 메서드 사용
			
			// 2. mng.showMenu() 대신 mainSystem의 showMenu()를 호출합니다.
	        mainSystem.showMenu();
			
			try{
				int choice = scan.nextInt();
				scan.nextLine();
				
				switch (choice) {
				case 1: 
					System.out.println("***신규계좌개설***");
					mng.makeAccount();
					break;
				case 2:
					System.out.println("***입   금***");
					mng.depositMoney();
					break;
				case 3:
					System.out.println("***출   금***");
					mng.withdrawMoney();
					break;
				case 4:
					System.out.println("***계좌정보출력***");
					mng.showAccInfo();
					break;	
				case 5:
					System.out.println("***계좌정보삭제***");//추가
					mng.deleteAcc();
					break;
				case 6:
					System.out.println("프로그램 종료");
					return;
					}
				if (choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
				    throw new MenuSelectException(choice + "는 잘못된 메뉴입니다.");
				}
			}	
			catch (MenuSelectException e) {
				    System.out.println("[메뉴오류] " + e.getMessage());
			}
			catch (java.util.InputMismatchException e) {
		            // 문자 입력 시
		            System.out.println("[입력오류] 숫자만 입력 가능합니다.");
		            scan.nextLine();  // 버퍼 비우기
			}
		}	
	}
	@Override public void makeAccount() {}
    @Override public void depositMoney() {}
    @Override public void withdrawMoney() {}
    @Override public void showAccInfo() {}
    @Override public void deleteAcc() {}
}
