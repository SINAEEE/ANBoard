<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@page import="userboard.*" %>
<%@page import="java.util.*" %>  
    
 <%
 	pagingDAO dao = new pagingDAO();
 	
 	String strPg = request.getParameter("pg"); //list.jsp?pg=?
 	
 	int rowSize = 10; //한페이지에 보여줄 글의 수
 	int pg = 1; //페이지 , list.jsp로 넘어온 경우, 초기값 =1
 	
 	if(strPg != null){ //list.jsp?pg=2
 		pg = Integer.parseInt(strPg); //저장
 	}

 	int from = (pg*rowSize)-(rowSize-1); //현재 페이지 게시물의 시작 행번호 
 	int to = (pg*rowSize); //현재 페이지 게시물의 끝 행번호
 	
 	//ArrayList<pagingDAO> list = dao.userBoard(from,to);
 	
 		
 	int total = dao.getTotal();
 	int allPage = (int) Math.ceil(total/(double)rowSize); //총페이지수
 	int block = 10;
 	
 	System.out.println("전체 페이지 수 : "+ allPage);
 	System.out.println("현재 페이지 수 : "+ strPg);
 	
 	int fromPage = ((pg-1)/block*block)+1;
 	int toPage = ((pg-1)/block*block)+block;
 	
 	if(toPage>allPage){
 		toPage = allPage;
 	}
 	
 %>
    
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
	</tr>

<%
    //for(int i=0; i<list.size(); i++){
        //userDTO dto = list.get(i);
        ArrayList<pagingDAO> list = dao.userBoard(from,to);
        for(pagingDTO dto : list){
%>
	<tr>
		<td><%=dto.getId() %></td>
		<td><%=dto.getUname() %></td>
		<td><%=dto.getPhone() %></td>
		<td><%=dto.getAddr() %></td>
	</tr>
<%
    }//for
%>
</table>

<table width="600">
<tr>
    <td align="center">
        <%
            if(pg>block){ //처음, 이전 링크
        %>
                [<a href="paginglist.jsp?pg=1">◀◀</a>]
                [<a href="paginglist.jsp?pg=<%=fromPage-1%>">◀</a>]    
        <%     
            }else{
        %>             
                [<span style="color:gray">◀◀</span>]   
                [<span style="color:gray">◀</span>]
        <%
            }
        %>
       
       
        <%
            for(int i=fromPage; i<= toPage; i++){
                if(i==pg){
        %>         
                    [<%=i%>]
       
        <%     
                }else{
        %>
                    [<a href="paginglist.jsp?pg=<%=i%>"><%=i%></a>]
        <%
                }
            }
       
        %>
       
       
        <%
            if(toPage<allPage){ //다음, 이후 링크
       
        %>
                [<a href="paginglist.jsp?pg=<%=toPage+1%>">▶</a>]
                [<a href="paginglist.jsp?pg=<%=allPage%>">▶▶</a>]
                       
        <%     
            }else{
        %>             
                   
                [<span style="color:gray">▶</span>]
                [<span style="color:gray">▶▶</span>]
        <%
            }
        %>
       
       
    </td>
</tr>
</table>


</body>
</html>



