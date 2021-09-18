package ru.job4j.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.job4j.model.Item;
import ru.job4j.store.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CloseTaskServlet extends HttpServlet {

    public final static Logger LOG = Logger.getLogger(TODOServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String[] buff = new Gson().fromJson(req.getParameter("values"), String[].class);
        for (String elem : buff) {
            Item item = HbnStore.instOf().findById(Integer.parseInt(elem));
            HbnStore.instOf().updateItem(item);
        }
    }
}
