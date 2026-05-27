package banking.step3;

import java.util.Scanner;

public class BankingSystemMain {

	public static Scanner scan =new Scanner(System.in);
	
	public static void menuShow(){
		System.out.println("########인터넷 뱅킹(ver03)#########");
		System.out.print("1.계좌계설 ");
		System.out.println("2.입금");
		System.out.print("3.출금 ");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램 종료 ");
		System.out.println("메뉴선택>>>> ");
		
	}
	
	
	public static void main(String[] args) {
		
		AccountManager mng = new AccountManager(50);
		
		while(true) {
			mng.showMenu(); // menuShow() 대신 인터페이스 메서드 사용
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
}
