<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
    
    //1. request (클라이언트 입력한 값을 받을 수 있다)
    //input > text , password, radio, textarea. select
    //넘어오는 값이 : 단일값, 다중값
    //input name="userid" 속성을 가지고 있어야 한다(?userid=kkkk);
    
    String userid = request.getParameter("userid");
    String userpwd = request.getParameter("pwd");
    
    //2. 다중값(request 객체의 함수)
    //input > checkbox (name 이 동일하게) , select (multipe)
    String[] hobbys = request.getParameterValues("hobby");
    
    Enumeration<String> e = request.getParameterNames();
    String name="";
    while(e.hasMoreElements()){
    	name += "/" + e.nextElement();
    }
    
    //Map{key , value}
    Map<String, String[]> m = request.getParameterMap();
    //key : <input type="text" name = "user" value="hong">> user
    //value : hong
    
    //user=hong&hobby=a&hobby=b
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
출력1> <%= userid %><br>
출력2> <%= userpwd %><br>
출력3> <%= hobbys %><br>
request.getParmeterNames : <%= name %><br>
</body>
</html>
 	