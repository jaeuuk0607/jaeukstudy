<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
  요청에 대한 흐름제어 (forward 를 통해서)
 
 include
 forward
 
  공통점 : request 객체를 공유한다
  차이점 : include 제어권을 가지고 있다
	  forward 제어권을 넘겨준다
	  (요청 주소는 동일한데 : 다른 page 의 내용을 서비스)
	  why? 처음 요청했던 주소(page)의 buffer 를 그대로 사용
	  localhost:8090/webJSP/login.jsp....
 전달내용이 : A.jsp
 		 B.jsp
 		 C.jsp
*/
String code = request.getParameter("code");
String viewURI = null;

if(code.equals("A")){
	viewURI = "/forward/A.jsp";
	
}else if(code.equals("B")){
	viewURI = "/forward/B.jsp";
}else if(code.equals("C")){
	viewURI = "/forward/C.jsp";
}

%>
<jsp:forward page="<%=viewURI %>"></jsp:forward>
<!-- 
http://localhost:8090/wepJSP/Ex15_forward_main.jsp?code=A
http://localhost:8090/wepJSP/Ex15_forward_main.jsp?code=B
http://localhost:8090/wepJSP/Ex15_forward_main.jsp?code=C
 -->