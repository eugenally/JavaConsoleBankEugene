package banking.step2;

import java.util.Scanner;

public class BankingSystemMain {

	public static Scanner scan =new Scanner(System.in);
	
	public static void menuShow(){
		System.out.println("########인터넷 뱅킹(ver02)#########");
		System.out.print("1.계좌계설 ");
		System.out.println("2.입금");
		System.out.print("3.출금 ");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램 종료 ");
		System.out.println("메뉴선택>>>> ");
		
	}
	
	//github연동
	//연동 완료후 커밋 & 푸시
	public static void main(String[] args) {
		
		AccountManager mng = new AccountManager(100);
		
		while(true) {
			menuShow();
			try{
				int choice = scan.nextInt();
				scan.nextLine();
				
				switch (choice) {
				case 1: 
					mng.makeAccount();
					break;
				case 2:
					mng.depositMoney();
					break;
				case 3:
					mng.withdrawMoney();
					break;
				case 4:
					mng.showAccInfo();
					break;		
				case 5:
					System.out.println("프로그램 종료");
					return;
					}
			}	
			catch (Exception e) {
				
			}
		}
	}
}
