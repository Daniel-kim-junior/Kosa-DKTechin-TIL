package core;

import java.awt.SystemTray;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "myFirstServlet", urlPatterns = "/myfirst")
public class MyFirstServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name") == null ? "GUEST" : request.getParameter("name");
        LocalDateTime lt = LocalDateTime.now();
        DayOfWeek dayOfWeek = lt.getDayOfWeek();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h2>");
        writer.write(name + "님 반가워요.. 오늘은 " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN) + "입니다!!");
        writer.write("</h2>");
    }
}
