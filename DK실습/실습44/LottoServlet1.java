package controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.TimeVO;

@WebServlet("/lotto1")
public class LottoServlet1 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lotto = req.getParameter("lotto");
        LocalTime time = LocalTime.now();
        int rand = (int) (Math.random() * 6) + 1;
        System.out.println("전달된 값 : " + lotto + " 추출된 값 : " + rand);
        TimeVO timeVO = new TimeVO(time.getHour(), time.getMinute());
        if(Integer.parseInt(lotto) == rand) {
            req.setAttribute("result", timeVO);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jspexam/success.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("result", timeVO);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jspexam/fail.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
