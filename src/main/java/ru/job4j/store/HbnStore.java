package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;
import java.util.function.Function;

public class HbnStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public List<Category> findAllCategories() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.model.Category")
                .list()
        );
    }

    @Override
    public List<Item> findAllItemCheckOff(int userId) {
        return this.tx(
                session -> session.createQuery("select distinct it from ru.job4j.model.Item it join fetch it.categories where it.done=false and user_id=:id order by it.id asc")
                        .setParameter("id", userId)
                        .list()
        );
    }

    @Override
    public List<Item> findAllItemCheckOffAndCheckOn(int userId) {
        return this.tx(
            session -> session.createQuery("select distinct it from ru.job4j.model.Item it join fetch it.categories where user_id=:id order by it.id asc")
                    .setParameter("id", userId)
                    .list()
        );
    }

    @Override
    public void saveItem(Item item, String[] categoryListId) {
        this.tx(session -> {
            for (String id : categoryListId) {
                Category category = session.find(Category.class, Integer.parseInt(id));
                item.addCategory(category);
            }
            session.save(item);
            return true;
        });
    }

    @Override
    public void updateItem(Item item) {
        this.tx(session -> {
            item.setDone(true);
            session.update(item);
            return null;
        });
    }

    @Override
    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public User findUserByEmail(String emailInput) {
        return (User) this.tx(
                session -> session.createQuery("from ru.job4j.model.User where email=:email")
                        .setParameter("email", emailInput).uniqueResult()
        );
    }

    @Override
    public void saveUser(User user) {
        this.tx(session -> session.save(user));
    }
}
