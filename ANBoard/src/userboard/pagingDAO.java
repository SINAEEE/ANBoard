package userboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pagingDAO {

//mysql db 연결 매소드, 연결객체 conn 반환
public Connection dbConns() {
		
		Connection conn = null; //db접속 객체
		
		try {
			//mysql jdbc driver 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			//db연결 문자열 
			String url = "jdbc:mysql://localhost:3306/anboard";
			//String url = "jdbc:mysql://localhost:3306/anboard?characterEncoding=EUC-KR&serverTimezone=UTC&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pwd = "dd910103";
			
			//db접속
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("db 접속 성공");
			
		}catch(Exception e) {
			
			//db관련 작업 반드시 익셉션 처리
			System.out.println("db접속 실패");
			e.printStackTrace();
		}
		return conn;
	}

//게시글 리스트
public ArrayList<pagingDTO> userBoard (int begin, int end){

	
	ArrayList<pagingDTO> list = new ArrayList<pagingDTO>();
	
	
	//System.out.println(begin);
	//System.out.println(end);
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	String sql = "select R1.* FROM (" +
		     "SELECT * FROM basic_user " + 
	         "order by phone asc" +
	         ") R1" +
	         " WHERE id >=0 AND id <=10"; 
	
	System.out.println(sql);
	
	try {
		conn = dbConns();
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, begin);
		pstm.setInt(2, end);
		
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			pagingDTO dto = new pagingDTO(); //DTO객체 선언
			
			dto.setId(rs.getInt("id"));
			dto.setUname(rs.getString("uname"));
			dto.setPhone(rs.getString("phone"));
			dto.setAddr(rs.getString("address"));
			
			list.add(dto);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


public int getTotal(){
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	int cnt = 0;
	
	String sql = "select count(*) from basic_user;";
	
	try {
		conn = dbConns();
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt");
			System.out.println("총게시물수 : " + cnt );
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		dbClose(conn,pstm,rs);
	}
	
	return cnt;

	
}


//db 닫아주기 - 1
private void dbClose(Connection conn, PreparedStatement pstm) {
	
	//pstm 닫기
	if(pstm != null)
		try {
			pstm.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	if(conn != null)
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
}

//db 닫아주기 - 2
private void dbClose(Connection conn, PreparedStatement pstm, ResultSet rs) {
	
	if(rs != null) {
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	if(pstm != null) {
		try {
			pstm.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	if(conn != null) {
		try {
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

}














