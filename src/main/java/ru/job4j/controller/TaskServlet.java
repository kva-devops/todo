package ru.job4j.controller;

import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        if ("yes".equals(req.getParameter("show"))) {
            req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findAllItemCheckOffAndCheckOn(userId)));
        } else {
            req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findAllItemCheckOff(userId)));
        }
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        HbnStore.instOf().saveItem(
                new Item(
                        req.getParameter("descriptionOfTask"),
                        false,
                        user
                )
        );
        resp.sendRedirect(req.getContextPath() + "/task");
    }
}
