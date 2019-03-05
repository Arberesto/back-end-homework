package it.sevenbits.homework6;

import it.sevenbits.homework6.Repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskGetAndDeleteServlet extends HttpServlet { // /task

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sessionId = request.getHeader("Authorization");
            if (sessionId == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
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
            String id = request.getParameter("taskId");
            TaskRepository repository = TaskRepository.getInstance();
            if (repository.contains(id)) {
                response.getWriter().write(String.format("{\n\"id\": %s,\n\"name\":%s\n}",
                        id, repository.get(id)));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sessionId = request.getHeader("Authorization");
            if (sessionId == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
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
            String id = request.getParameter("taskId");
            TaskRepository repository = TaskRepository.getInstance();
            if (repository.contains(id)) {
                String result = String.format("{\n\"id\": %s,\n\"name\":%s\n}",
                        id, repository.get(id));
                repository.delete(id);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(result);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
