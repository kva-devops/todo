package ru.job4j.store;

import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public interface Store {
    List<Category> findAllCategories();
    List<Item> findAllItemCheckOff(int userId);
    List<Item> findAllItemCheckOffAndCheckOn(int userId);
    void saveItem(Item item, String[] categoryListId);
    void updateItem(Item item);
    Item findById(int id);
    User findUserByEmail(String email);
    void saveUser(User user);
}
