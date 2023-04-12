package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/reservation")
public class ReservationServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub

    // 1. 요청 파라미터 조회
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    String date = request.getParameter("date");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    String[] appends = request.getParameterValues("append");
    String password = request.getParameter("password");
    String room = request.getParameter("room");

    // 2. 비즈니스 로직 처리
    String PATH;
    if(name.equals("")) {
      PATH = "/first.html";
      RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
      requestDispatcher.forward(request, response);
    } else {
      if(password.equals("")) {
        PATH = "https://www.daum.net/";
        response.sendRedirect(PATH);
      } else {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        writer.write("<h1>"+ name + "님의 예약내용</h1>");
        writer.write("<hr>");
        writer.write("<ul>");
        writer.write("<li>룸 : "+ room + "</li>");
        writer.write("<li>추가 요청 사항 : "  );
        if(appends == null) {
          writer.write("<span>" + "없음" + "</span>");
        } else {
          for(int i = 0; i < appends.length; i++) {
            if(i == appends.length - 1) {
              writer.write("<span>" + appends[i] + "</span>");
            } else {
              writer.write("<span>" + appends[i] + ", </span>");
            }
          }
        }

        try {
          Date sDate = sdf.parse(date);
          sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
          writer.write("<li>예약날 : "+ sdf.format(sDate) +"</li>");
        } catch (ParseException e) {
          writer.write("<li>예약날 : "+ "지정 안하셨어요 손님!!!!" +"</li>");

        }
        writer.write("</ul>");
        writer.write("<a href=" + request.getHeader("referer") +">" + "예약화면으로" + "</a>");
        writer.close();
      }
    }
  }
}
