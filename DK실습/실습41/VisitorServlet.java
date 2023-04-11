package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String contents = req.getParameter("contents");
        resp.setContentType("text/html; charset=utf-8");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");


        PrintWriter writer = resp.getWriter();
        writer.write("<h2>");
        writer.write(name + "님이 " + now.format(formatter) + "에 남긴 글입니다.");
        writer.write("</h2>");
        writer.write("<hr>");
        writer.write(contents);
        writer.write("<hr>");
        writer.write("<a href=/edu/visitorForm.html>메인으로</a>");
    }
}
