package userboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userDAO {
	
	public Connection dbConn() {
		
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
	
public ArrayList<userDTO> userList() {
	
	ArrayList<userDTO> ulist = new ArrayList<userDTO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = dbConn();
		//String sql = "select * from basic_user";
		String sql = "select R1.* FROM (" +
				     "SELECT * FROM basic_user " + 
			         "order by phone asc" +
			         ") R1" +
			         " WHERE id >=0 AND id <=10"; 
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			userDTO dto = new userDTO();
			dto.setId(rs.getInt("id"));
			dto.setUname(rs.getString("uname"));
			dto.setPhone(rs.getString("phone"));
			dto.setAddr(rs.getString("address"));
			
			ulist.add(dto);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		try{
            if(rs!=null){rs.close();}
            
        }catch(Exception e2){
            e2.printStackTrace();
        }
        
        try{
            if(pstmt!=null){pstmt.close();}
            
        }catch(Exception e2){
            e2.printStackTrace();
        }
        
        try{
            if(conn!=null){conn.close();}
            
        }catch(Exception e2){
            e2.printStackTrace();
        }

		
	}return ulist;
		
		
}	
	

}
