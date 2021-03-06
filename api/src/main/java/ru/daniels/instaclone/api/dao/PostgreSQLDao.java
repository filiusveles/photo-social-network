package ru.daniels.instaclone.api.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.instaclone.api.model.dbentity.DBEntity;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.model.dbentity.Tag;
import ru.daniels.instaclone.api.model.dbentity.User;
import ru.daniels.instaclone.api.security.SecUser;

import java.util.List;


@Repository("dao")
public class PostgreSQLDao<T extends DBEntity> implements Dao<T> {

    private final SessionFactory sessionFactory;

    public PostgreSQLDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        return entity;
    }

    @Override
    @Transactional
    public T read(long id, Class<T> entity) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(entity, id);
    }

    @Override
    @Transactional
    public T readByName(String columnName, String value, Class<T> entity) {
        Session session = sessionFactory.getCurrentSession();
        String SQL;
        if(entity.isAssignableFrom(User.class) || entity.isAssignableFrom(SecUser.class)){
            SQL = "SELECT * FROM data.users WHERE " + columnName + "=" + "'" + value + "'";
        }else if (entity.isAssignableFrom(Tag.class)){
            SQL = "SELECT * FROM data.tag WHERE " + columnName + "=" + "'" + value + "'";
        }else {
            throw new IllegalArgumentException();
        }
        Query query = session.createSQLQuery(SQL).addEntity(entity);
        if(query.list().size() > 0) {
            return (T) query.list().get(0);
        }
        return null;
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
