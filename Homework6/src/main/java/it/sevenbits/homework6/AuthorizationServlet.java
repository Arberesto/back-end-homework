package it.sevenbits.homework6;

import it.sevenbits.homework6.Repository.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet { // register

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            SessionRepository sessionRepository = SessionRepository.getInstance();
            String id = sessionRepository.add(name);
            if (!"".equals(id)) {
                response.addCookie(new Cookie("sessionId", id));
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getCookies() != null) {
                StringBuilder sb = new StringBuilder();
                SessionRepository sessionRepository = SessionRepository.getInstance();
                sb.append("<html><body>");
                for (Cookie cookie : request.getCookies()) {
                    if ("sessionId".equals(cookie.getName())) {
                        String name = sessionRepository.get(cookie.getValue());
                        sb.append(String.format("Current User is %s", name));
                    }
                }
                sb.append("</body></html>");
                response.getWriter().write(sb.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
