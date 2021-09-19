package ru.job4j.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.Item;
import ru.job4j.store.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

public class TODOServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> itemList = HbnStore.instOf().findAllItemCheckOff();
        final Gson GSON = new GsonBuilder().create();
        resp.setContentType("application/json; charset=urf-8");
        OutputStream outputStream = resp.getOutputStream();
        String json = GSON.toJson(itemList);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HbnStore.instOf().saveItem(
                Item.of(
                    req.getParameter("task"),
                    new Timestamp(System.currentTimeMillis()),
                    false));
    }
}
