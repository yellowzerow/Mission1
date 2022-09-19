
<%@page import="com.zerobase.yellowzero.db.WifiData"%>
<%@page import="java.util.List"%>
<%@page import="com.zerobase.yellowzero.controller.HttpService"%>
<%@page import="com.zerobase.yellowzero.controller.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>공공 와이파이 정보 가져오기</title>
</head>
<body>
	<%
        WifiService wifi = new WifiService();
        HttpService http = new HttpService();
        List<WifiData> datas = null;

        try {
        	datas = http.loadAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (datas != null) {
            for (WifiData data : datas) {
                wifi.saveInfo(data);
            }
        }

    %>

    <h1 style="text-align: center;"><%=datas != null ? datas.size() : 0%>개의 WIFI 정보를 정상적으로 저장하였습니다</h1>
    <div style="text-align: center;">
        <a href="home.jsp">홈으로 가기</a>
    </div>
</body>
</html>