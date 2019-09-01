<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@page import="userboard.*" %>
<%@page import="java.util.*" %>  
    
 <%
 	pagingDAO dao = new pagingDAO();
 	
 	String strPg = request.getParameter("pg"); //list.jsp?pg=?
 	
 	int rowSize = 10; //���������� ������ ���� ��
 	int pg = 1; //������ , list.jsp�� �Ѿ�� ���, �ʱⰪ =1
 	
 	if(strPg != null){ //list.jsp?pg=2
 		pg = Integer.parseInt(strPg); //����
 	}

 	int from = (pg*rowSize)-(rowSize-1); //���� ������ �Խù��� ���� ���ȣ 
 	int to = (pg*rowSize); //���� ������ �Խù��� �� ���ȣ
 	
 	//ArrayList<pagingDAO> list = dao.userBoard(from,to);
 	
 		
 	int total = dao.getTotal();
 	int allPage = (int) Math.ceil(total/(double)rowSize); //����������
 	int block = 10;
 	
 	System.out.println("��ü ������ �� : "+ allPage);
 	System.out.println("���� ������ �� : "+ strPg);
 	
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

<h2>ȸ�� ���</h2>
<table border=1">
	<tr>
		<th>ȸ����ȣ</th>
		<th>ȸ����</th>
		<th>��ȭ��ȣ</th>
		<th>�ּ�</th>
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
            if(pg>block){ //ó��, ���� ��ũ
        %>
                [<a href="paginglist.jsp?pg=1">����</a>]
                [<a href="paginglist.jsp?pg=<%=fromPage-1%>">��</a>]    
        <%     
            }else{
        %>             
                [<span style="color:gray">����</span>]   
                [<span style="color:gray">��</span>]
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
            if(toPage<allPage){ //����, ���� ��ũ
       
        %>
                [<a href="paginglist.jsp?pg=<%=toPage+1%>">��</a>]
                [<a href="paginglist.jsp?pg=<%=allPage%>">����</a>]
                       
        <%     
            }else{
        %>             
                   
                [<span style="color:gray">��</span>]
                [<span style="color:gray">����</span>]
        <%
            }
        %>
       
       
    </td>
</tr>
</table>


</body>
</html>



