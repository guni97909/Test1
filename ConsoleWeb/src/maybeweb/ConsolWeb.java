package maybeweb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class ConsolWeb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Check check=new Check();
		
		LocalDate now = LocalDate.now();   
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");   
		String formatedNow = now.format(formatter);
		
		LogoTitle1 lgt1=new LogoTitle1();	
		LogoTitle2 lgt2=new LogoTitle2();
		TitlePrint tp=new TitlePrint();
       
		
	
		String id,pw;
		

			System.out.println("나의 일기장 1.입장  0.나가기");
			int temp= sc.nextInt();
			sc.nextLine();
			
			if(temp==1) {
				System.out.println("일기장에 입장합니다.");
				System.out.print("ID:");
				id= sc.nextLine();
				System.out.print("PassWord:");
				pw= sc.nextLine();
				
				int flag=check.login(id,pw);
				if(flag==3) {
					System.out.println("로그인 성공하였습니다.");
					
					
					
					for(;;) {
						List<Daily> list=check.list();
						tp.print(lgt1);					
							for(Daily temp2:list) {
								System.out.print(temp2.daily+" | ");
								System.out.println(temp2.title);											
							}   
							System.out.println("---------------------");
							System.out.println("1.오늘의 일기쓰기 2. 내용보기 3.종료");
							int temp2= sc.nextInt();
							sc.nextLine();

							if(temp2==1) {
						
//								System.out.println("오늘의 날짜를 입력하세요. 형태(yyyy-MM-dd)");
//								String date=sc.nextLine();						
								
								System.out.println("제목: ");
								String tt=sc.nextLine();
								System.out.println("내용: ");
								String cott=sc.nextLine();
								
								int count=check.count("NO");
								System.out.println("저장하시겠습니까? Yes/No 입력");
								String yesno=sc.nextLine();
								if(yesno.equals("Yes")) {
									check.write(formatedNow, tt,count+1, cott);
									System.out.println("저장하였습니다.");
									System.out.println("---------------------");	
								}else if(yesno.equals("No")) {
									System.out.println("취소하였습니다..");
								}else {
									System.out.println("잘못입력하였습니다..");
								}
								
								

							}else if(temp2==2) {
								System.out.println("어떤 글을 보시겠습니까?");
								String search_title=sc.nextLine();	
								if(search_title.equals(check.Search(search_title))) {
									tp.print(lgt2);
									System.out.print(check.Search(search_title)+" | ");
									System.out.println(check.Search_content(search_title));
									System.out.println("*************************************************************************************************");
									System.out.println("뒤로가기:1     프로그램종료: 2 ");
									String yesno2=sc.nextLine();
									if(yesno2.equals("1")) {
										
									}else if(yesno2.equals("2")) {
										System.out.println("종료합니다.");
										break;
									}else {
										System.out.println("잘못입력하였습니다..");
									}
								}else {
									System.out.println("그런 제목은 없습니다.");
									
								}
												
							}
							else {
								System.out.println("일기를 종료합니다.");
								break;
							}
					}
		
					
				
				}else if(flag==2) {
					System.out.println("비밀번호가 틀렸습니다.");
				}else if(flag==1) {
					System.out.println("아이디틀렸습니다.");
				}
				
				
			}else if(temp==0) {
				System.out.println("'나가기'를 입력하였습니다. 콘솔을 종료합니다.");
				
			
			}else {
				System.out.println("잘못 입력하였습니다.");
			}
		

	}

}
