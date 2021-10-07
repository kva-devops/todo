package ru.job4j.controller;

import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Category> categoryList = HbnStore.instOf().findAllCategories();
        Map<Integer, List<Category>> categoryMap = new HashMap<>();
        int userId = user.getId();
        List<Item> itemList;
        if ("yes".equals(req.getParameter("show"))) {
            itemList = new ArrayList<>(HbnStore.instOf().findAllItemCheckOffAndCheckOn(userId));
        } else {
            itemList = new ArrayList<>(HbnStore.instOf().findAllItemCheckOff(userId));
        }
        for (Item elem : itemList) {
            categoryMap.put(elem.getId(), elem.getCategoryList());
        }
        req.setAttribute("categoryItemMap", categoryMap);
        req.setAttribute("tasks", itemList);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("allCategories", categoryList);
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String[] cIds = req.getParameterValues("cIds");
        User user = (User) req.getSession().getAttribute("user");
        HbnStore.instOf().saveItem(
                new Item(
                        req.getParameter("descriptionOfTask"),
                        false,
                        user
                ),
                cIds
        );
        resp.sendRedirect(req.getContextPath() + "/task");
    }
}
