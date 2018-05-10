package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.dao.memodao;


@WebServlet("/MemoServlet")
public class Memoservlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
   
    public Memoservlet() {
        super();
       
    }

 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doProcess(request, response);
 }

 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doProcess(request, response);
 }
 private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //1.한글처리
  //2.데이터 받기
  //3.DB연결
  //4.Insert 실행
  //5.응답구성
  request.setCharacterEncoding("UTF-8");
    
  String id = request.getParameter("id");
  String email = request.getParameter("email");
  String memo = request.getParameter("memo");
  //out.print(id + "," + email + "," + memo);
  
  
  response.setContentType("text/html;charset=UTF-8");
  PrintWriter out = response.getWriter();
  try{
	  	memodao dao = new memodao();  //point
	  	int n = dao.insertMemo(id, email, memo);
	    if(n>0){
	    		out.print("<script>");
	    			out.print("alert('등록성공..');");
	    			out.print("location.href='MemoList';");
	    		out.print("</script>");
	    		}else{
	    		   //int resultrow=0;
	    		   //DAO insert >> 예외 >> return 0
	    		   //의미 : int resultrow = 0;
                   //여기 처리 말고
	    		
	    		      out.print("<script>");
	    		      out.print("alert('등록실패..');");
	    		      out.print("location.href='memo.html';");
	    		      out.print("</script>");
	    		}
   }catch(Exception e){  //PK , varchar2(10) -> 20Byte ....
      out.print("<b> 오류 :" +  e.getMessage()  + "</b>");
      /*out.print("<script>");
      out.print("alert('등록실패..');");
      out.print("location.href='memo.html';");
      out.print("</script>");*/
  }
 
 }
 
}
