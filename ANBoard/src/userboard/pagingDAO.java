package userboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pagingDAO {

//mysql db ���� �żҵ�, ���ᰴü conn ��ȯ
public Connection dbConns() {
		
		Connection conn = null; //db���� ��ü
		
		try {
			//mysql jdbc driver �ε�
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			//db���� ���ڿ� 
			String url = "jdbc:mysql://localhost:3306/anboard";
			//String url = "jdbc:mysql://localhost:3306/anboard?characterEncoding=EUC-KR&serverTimezone=UTC&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pwd = "dd910103";
			
			//db����
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("db ���� ����");
			
		}catch(Exception e) {
			
			//db���� �۾� �ݵ�� �ͼ��� ó��
			System.out.println("db���� ����");
			e.printStackTrace();
		}
		return conn;
	}

//�Խñ� ����Ʈ
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
			pagingDTO dto = new pagingDTO(); //DTO��ü ����
			
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
			System.out.println("�ѰԽù��� : " + cnt );
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		dbClose(conn,pstm,rs);
	}
	
	return cnt;

	
}


//db �ݾ��ֱ� - 1
private void dbClose(Connection conn, PreparedStatement pstm) {
	
	//pstm �ݱ�
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

//db �ݾ��ֱ� - 2
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














