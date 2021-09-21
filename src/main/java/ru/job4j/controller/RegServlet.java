package ru.job4j.controller;

import org.apache.log4j.Logger;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = HbnStore.instOf().findUserByEmail(email);
        if (user == null) {
            HbnStore.instOf().saveUser(new User(username, email, password));
        } else {
            req.setAttribute("error", "This email address is already registered.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
