package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store {
    List<Item> findAllItemCheckOff();
    List<Item> findAllItemCheckOffAndCheckOn();
    void saveItem(Item item);
    void updateItem(Item item);
    Item findById(int id);
}
