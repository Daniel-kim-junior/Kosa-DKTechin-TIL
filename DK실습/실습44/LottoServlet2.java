package controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.domain.TimeVO;

@WebServlet("/lotto2")
public class LottoServlet2 extends HttpServlet {
    private int cnt;

    @Override
    public void init() throws ServletException {
        cnt = 0;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        RequestDispatcher dispatcher;
        if(httpSession.getAttribute("limit") == null || httpSession.isNew()) {
            httpSession.setAttribute("limit", new boolean[3]);
        }
        boolean [] session = (boolean[]) httpSession.getAttribute("limit");
        boolean f = false;
        for(boolean b :session) {
            if(!b) f = true;
        }
        // 3번 다 도전했다면
        if(!f) {
            dispatcher = req.getRequestDispatcher("/jspexam/impossible.jsp");
            dispatcher.forward(req, resp);
            cnt = 0;
            httpSession.invalidate();
            return;
        }


        String lotto = req.getParameter("lotto");
        LocalTime time = LocalTime.now();
        int rand = (int) (Math.random() * 6) + 1;
        System.out.println("전달된 값 : " + lotto + " 추출된 값 : " + rand);

        TimeVO timeVO = new TimeVO(time.getHour(), time.getMinute());
        if(Integer.parseInt(lotto) == rand) {
            dispatcher = req.getRequestDispatcher("/jspexam/impossible.jsp");
            dispatcher.forward(req, resp);
            httpSession.invalidate();
            cnt = 0;
        } else {
            req.setAttribute("result", timeVO);
            session[cnt++] = true;
            dispatcher = req.getRequestDispatcher("/jspexam/fail.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
