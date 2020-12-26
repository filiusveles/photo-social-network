package ru.daniels.instaclone.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.instaclone.model.dbentity.DBEntity;
import ru.daniels.instaclone.model.dbentity.Post;
import ru.daniels.instaclone.model.dbentity.Tag;
import ru.daniels.instaclone.model.dbentity.User;

import java.util.List;


@Repository
public class PostgreSQLDao<T extends DBEntity> implements Dao<T> {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User readByName(String columnName, String value) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.users WHERE " + columnName + "=" + "'" + value + "'";
        Query query = session.createSQLQuery(SQL).addEntity(User.class);
        return (User) query.stream().findFirst().get();
    }
    @Override
    @Transactional
    public Tag findTagByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.tag WHERE name=" + "'" + name + "'";
        Query<Tag> query = session.createSQLQuery(SQL).addEntity(Tag.class);
        if(query.list().size() > 0) {
            return query.list().get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public User update(long id) {
        return null;
    }

    @Override
    @Transactional
    public T create(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        System.out.println("entity: " + entity);
        return entity;
    }

    @Override
    @Transactional
    public T read(long id, Class<T> entity) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("entity: " + entity);
        return session.get(entity, id);
    }

    @Override
    @Transactional
    public List<Post> findPostsByUser(long id){
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.posts WHERE author_id="+id;
        return session.createSQLQuery(SQL).addEntity(Post.class).list();
    }

    @Override
    @Transactional
    public void delete(long id, Class<T> entity) {

    }

    @Override
    @Transactional
    public void addNewRelationPostTag(Tag tag, Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(post);
    }



}
