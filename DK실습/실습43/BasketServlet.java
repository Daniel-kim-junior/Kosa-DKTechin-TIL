package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import org.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    private final HashMap<Integer, String> productID = new HashMap<>();

    @Override
    public void init() throws ServletException {
        for(int i = 1; i < 10; i++) {
            productID.put(i, "p00" + i);
        }
        productID.put(10, "p010");
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession(true);

        if(httpSession.isNew()) {
            httpSession.setAttribute("count", new int[10]);
        }

        String imgId = request.getParameter("id");
        // 문자열이 안들어 왔을때
        if(imgId == null) {
            // {"msg" : "상품이 모두 삭제되었습니다."} json
            JSONObject jsonObject = new JSONObject();
            httpSession.invalidate();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            jsonObject.put("msg", "상품이 모두 삭제되었습니다.");
            writer.write(jsonObject.toString());
            writer.close();

        } else {
            // 문자열이 들어왔을 때
            int[] count = (int []) httpSession.getAttribute("count");
            // id를 Map에서 들고와서 1증가
            count[Integer.parseInt(imgId) - 1]++;

            // imgName과 count로 만들자 json
            JSONObject jsonObject = new JSONObject();
            int cnt = 0;
            for(String name : productID.values()) {
                jsonObject.put(name, count[cnt++]);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.close();
        }
    }
}
