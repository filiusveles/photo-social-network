package ru.daniels.instaclone.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.instaclone.model.Image;
import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.Tag;
import ru.daniels.instaclone.model.User;

import java.util.List;


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
    public User readByName(String columnName, String value) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.users WHERE " + columnName + "=" + "'" + value + "'";
        Query query = session.createSQLQuery(SQL).addEntity(User.class);
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
    public long createPost(Post post){
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(post);
    }

    @Override
    @Transactional
    public Post getPost(long postId) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.posts WHERE id="+postId;
        Query<Post> query = session.createSQLQuery(SQL).addEntity(Post.class);
        return query.stream().findFirst().get();
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
    public Long createImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(image);
    }

    @Override
    @Transactional
    public Image getImage(long id) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.images WHERE id=" + id;
        Query<Image> query = session.createSQLQuery(SQL).addEntity(Image.class);
        return query.stream().findFirst().get();
    }

    @Override
    @Transactional
    public void delete(long id) {

    }

    @Override
    @Transactional
    public Long createTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long)session.save(tag);
        Post post = tag.getPosts().stream().findFirst().get();
        session.createSQLQuery("INSERT INTO data.tagmap VALUES (" + post.getId() + "," + id + ");");
        return id;
    }

    @Override
    @Transactional
    public Tag getTag(long id) {
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT * FROM data.tag WHERE id=" + id;
        Query<Tag> query = session.createSQLQuery(SQL).addEntity(Tag.class);
        return query.stream().findFirst().get();
    }
}
