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

public class CloseTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameterValues("check") == null) {
            resp.sendRedirect(req.getContextPath() + "/task");
            return;
        }
        String[] values = req.getParameterValues("check");
        for (String elem : values) {
            Item item = HbnStore.instOf().findById(Integer.parseInt(elem));
            HbnStore.instOf().updateItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/task");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findAllItemCheckOff(userId)));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }
}
