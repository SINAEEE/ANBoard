<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@page import="userboard.*" %>
<%@page import="java.util.ArrayList" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h2>회원 목록</h2>
<table border=1">
<tr>
<th>회원번호</th>
<th>회원명</th>
<th>전화번호</th>
<th>주소</th>

<%
userDAO dao = new userDAO();

ArrayList<userDTO> list = dao.userList();
for(userDTO dto : list){
%>

<tr>
<td><%=dto.getId() %></td>
<td><%=dto.getUname() %></td>
<td><%=dto.getPhone() %></td>
<td><%=dto.getAddr() %></td>
</tr>
<% 
}

%>
</table>

</body>
</html>