package it.sevenbits.homework6;

import it.sevenbits.homework6.Repository.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessServlet extends HttpServlet { // register

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            SessionRepository sessionRepository = SessionRepository.getInstance();
            String id = sessionRepository.add(name);
            if (!"".equals(id)) {
                resp.addCookie(new Cookie("sessionId", id));
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() != null) {
            StringBuilder sb = new StringBuilder();
            SessionRepository sessionRepository = SessionRepository.getInstance();
            sb.append("<html><body>");
            for (Cookie cookie : req.getCookies()) {
                if ("sessionId".equals(cookie.getName())) {
                    String name = sessionRepository.get(cookie.getValue());
                    sb.append(String.format("Current User is %s", name));
                }
            }
            sb.append("</body></html>");
            resp.getWriter().write(sb.toString());
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
