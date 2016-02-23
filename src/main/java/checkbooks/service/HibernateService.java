package checkbooks.service;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;


/**
 * Created by pavel on 18.05.15.
 */
public class HibernateService {

    public HibernateService() {
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
