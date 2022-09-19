<%@page import="com.zerobase.yellowzero.db.HistoryData"%>
<%@page import="java.util.List"%>
<%@page import="com.zerobase.yellowzero.controller.HistoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>위치 히스토리 목록</title>
	<style>
	
		#menu {
			margin-bottom: 20px;
			text-align: center;
		}
		
		#menu a {
			margin-right: 5px;
		}
	
        #table {
            border-collapse: collapse;
            width: 100%;
        }

        #table td, #table th {
            border: 1px solid gray;
            padding: 8px;
        }

        #table tr:nth-child(odd){background-color: #FAFABE;}

        #table th {
            padding-top: 10px;
            padding-bottom: 10px;
            text-align: left;
            background-color: #FFF56E;
            color: #8B6331;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">와이파이 정보 구하기</h1>
<div id="menu">
	<a href="home.jsp">홈</a>
	<a href="history.jsp">위치 히스토리 목록</a>
	<a href="load_info.jsp"> Open API 와이파이 정보 가져오기</a>
</div>

<br/>
<%
    HistoryService history = new HistoryService();
    List<HistoryData> list = history.loadHistory();
    String id = request.getParameter("id");
    if(id != null) {
    	history.deleteHistory(id);
    }
%>

<table id="table">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
        <%
            if (list.size() != 0) {
                for (HistoryData data : list) { %>
				    <tr>
				        <td><%=data.getID()%></td>
				        <td><%=data.getLAT()%></td>
				        <td><%=data.getLNT()%></td>
				        <td><%=data.getHISTORY_DATE()%></td>
				        <td align="center">
				        	<a href="history.jsp?id=<%=data.getID()%>">
				        		<button type="button">삭제</button>
				        	</a>
				        </td>
				    </tr>
        
         <%      }
             } else {  %>
	          <tr>
	          	<td colspan="5" style="text-align: center;">위치 조회한 히스토리가 없습니다.</td>
	          </tr>
         <%
             }
         %>
</table>
</body>
</html>