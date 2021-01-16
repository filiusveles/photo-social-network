package ru.daniels.instaclone.api.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.model.dbentity.Tag;
import ru.daniels.instaclone.api.model.dbentity.User;
import ru.daniels.instaclone.api.security.SecUser;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "ru.daniels.instaclone.api")
public class DBConfiguration {
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment){
        this.environment = environment;
    }

    @Bean
    public BasicDataSource basicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("postgresql.driverClassName"));
        basicDataSource.setUrl(environment.getProperty("postgresql.url"));
        basicDataSource.setUsername(environment.getProperty("postgresql.username"));
        basicDataSource.setPassword(environment.getProperty("postgresql.password"));
        return basicDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
        localSessionFactory.setDataSource(basicDataSource());
        localSessionFactory.setAnnotatedClasses(
                User.class,
                Post.class,
                Image.class,
                Tag.class,
                SecUser.class
        );
        Properties props = new Properties();
        props.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.put("format_sql", environment.getProperty("hibernate.show_sql"));
        localSessionFactory.setHibernateProperties(props);
        return localSessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory().getObject());
        return manager;
    }
}
