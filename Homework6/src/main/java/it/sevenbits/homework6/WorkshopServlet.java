package it.sevenbits.homework6;

import it.sevenbits.homework6.Repository.SessionRepository;
import it.sevenbits.homework6.Repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class WorkshopServlet extends HttpServlet { // /tasks

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sessionId = request.getParameter("Authorization");

            if (request.getCookies() != null) {
                boolean IsAuthorized = false;
                for (Cookie cookie : request.getCookies()) {
                    if ("sessionId".equals(cookie.getName()) && sessionId.equals(cookie.getValue())) {
                        IsAuthorized = true;
                    }
                }
                if (!IsAuthorized) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            TaskRepository repository = TaskRepository.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            Set<String> keys = repository.getIdList();
            for (String key : keys) {
                sb.append(String.format("{\n\"id\":%s,\n\"name\":\"%s\"\n}, \n",
                        key, repository.get(key)));
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("]");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().write(sb.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sessionId = request.getParameter("Authorization");
            if (request.getCookies() != null) {
                boolean IsAuthorized = false;
                for (Cookie cookie : request.getCookies()) {
                    if ("sessionId".equals(cookie.getName()) && sessionId.equals(cookie.getValue())) {
                        IsAuthorized = true;
                    }
                }
                if (!IsAuthorized) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            TaskRepository repository = TaskRepository.getInstance();
            String name = request.getParameter("name");
            String id = repository.add(name);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.addHeader("Location", String.format("/item?id=%s", id));

            response.getWriter().write(String.format("{\n\"id\":%s,\n\"name\":%s\n}",
                    id, repository.get(id)));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
