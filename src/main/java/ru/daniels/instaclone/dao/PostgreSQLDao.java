package ru.daniels.instaclone.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.instaclone.model.User;




@Repository
public class PostgreSQLDao implements Dao {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User readById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public User readByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.users WHERE email=";
        Query query = session.createSQLQuery(SQL+ "'" + name + "'").addEntity(User.class);
        return (User) query.stream().findFirst().get();
    }

    @Override
    @Transactional
    public User update(long id) {
        return null;
    }

    @Override
    @Transactional
    public long create(User user) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {

    }
}
