package maybeweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Check {
	
	private Connection conn() throws Exception{
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		Class.forName("oracle.jdbc.driver.OracleDriver"); //데이터베이스 드라이버 로딩
		return DriverManager.getConnection(url,"scott","tiger");
		
		
	}
	private void connClose(ResultSet rs,PreparedStatement stmt,Connection con) {
		try {if(rs!=null) stmt.close();   } catch (SQLException e) {}
		try {if(con!=null) stmt.close();  } catch (SQLException e) {}
		try {if(stmt!=null) stmt.close(); } catch (SQLException e) {}	
	}
	
	public int login(String id, String pw) {
		// DB에 접속후 id,pw 가지고 확인해서
		// 1~3 까지의 값을 return 주면 된다.
		
//		int flag = 0;//초기화
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con=conn();
			stmt=con.prepareStatement("select * from tbl_user where id=? and pw=?");
			stmt.setString(1, id);
			stmt.setString(2, pw);
			
			rs= stmt.executeQuery(); //sql 문 넣어서 작업
			
			if(rs.next()) { //한행이 있다면(Id가 있다면)				
				String dbPw=rs.getString("pw");
				if(pw.equals(dbPw))
					return 3;
				else
					return 2;
			}else
				return 1;
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생");
			e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(rs,stmt,con);
		}
		

		return 0; //초기화
				
	}
	
	List<Daily> list(){
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection con = null;
		List<Daily> list=new ArrayList<>();
		try {
			con=conn();
			stmt=con.prepareStatement("select DAILY,TITLE,NO,CONTENT from DAILY_TABLE order by 1");
			rs= stmt.executeQuery(); 
			
			while(rs.next()) {
				Daily u=new Daily();
				u.daily=rs.getDate(1);
				u.title=rs.getNString(2);
				u.no=rs.getInt(3);
				u.content=rs.getNString(4);
				list.add(u);
			}
		} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("예외발생");
		e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(rs,stmt,con);
		}
		return list;
		
	}
	
	public void write(String date, String title, int no,String content) {
		
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con=conn();
//			System.out.println("DB 접속 성공");
			stmt=con.prepareStatement("Insert into DAILY_TABLE values(?,?,?,?)");
//			stmt.executeUpdate(sql); //sql 문 넣어서 작업  //삽입,삭제,갱신시에는 .executeUpdate()			
			stmt.setString(1, date);
			stmt.setString(2, title);
			stmt.setInt(3, no);
			stmt.setString(4, content);
			Thread.sleep(500);
			stmt.executeUpdate();
			
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생");
			e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(null,stmt,con);
		}
		
		return ;
	}
	
	public int count(String num) {
		// DB에 접속후 id,pw 가지고 확인해서
		// 1~3 까지의 값을 return 주면 된다.
		
//		int flag = 0;//초기화
		int x = 0;
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con=conn();
			stmt=con.prepareStatement("select count(?) from DAILY_TABLE ");
			stmt.setString(1, num);
			rs= stmt.executeQuery(); //sql 문 넣어서 작업
			
			if(rs.next()) { //한행이 있다면(Id가 있다면)				
			 x=rs.getInt(1);
			}else {
				
			}
				
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생");
			e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(rs,stmt,con);
		}
		

		return x; //초기화
				
	}
	
	public String Search(String title) {
		String dbtt = "";
		
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con=conn();
//			System.out.println("DB 접속 성공");
			stmt=con.prepareStatement("Select title from DAILY_TABLE where title=?");
//			stmt.executeUpdate(sql); //sql 문 넣어서 작업  //삽입,삭제,갱신시에는 .executeUpdate()					
			stmt.setString(1, title);
			stmt.executeUpdate();
			
			rs= stmt.executeQuery();
						
			if(rs.next()) { //한행이 있다면(Id가 있다면)				
				dbtt=rs.getString("title");				
			}else {
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생");
			e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(rs,stmt,con);
		}
		
		return dbtt;
	}
	
	public String Search_content(String title) {
		String dbct = "";
		
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con=conn();
//			System.out.println("DB 접속 성공");
			stmt=con.prepareStatement("Select content from DAILY_TABLE where title=?");
//			stmt.executeUpdate(sql); //sql 문 넣어서 작업  //삽입,삭제,갱신시에는 .executeUpdate()					
			stmt.setString(1, title);
			stmt.executeUpdate();
			
			rs= stmt.executeQuery();
						
			if(rs.next()) { //한행이 있다면(Id가 있다면)				
				dbct=rs.getString("CONTENT");				
			}else {
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외발생");
			e.printStackTrace();
		} // 데이터베이스 드라이버 로딩 
		finally {
			connClose(rs,stmt,con);
		}
		
		return dbct;
	}

}
