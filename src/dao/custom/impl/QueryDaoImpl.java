package dao.custom.impl;

import dao.custom.QueryDAO;
import entity.Course;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;


public class QueryDaoImpl implements QueryDAO {



    @Override
    public Registration getLastRegistration() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query registration1 = session.createQuery("from Registration ");
        List list = registration1.list();
        Registration registration= (Registration) list.get(list.size() - 1);
        transaction.commit();
        session.close();
        return registration;
    }

    @Override
    public Student getLastStudent() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query student1 = session.createQuery("from Student ");
        List list = student1.list();
        Student student= (Student) list.get(list.size() - 1);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public Course getLastCourse() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query course1 = session.createQuery("from Course");
        List list = course1.list();
        Course course = (Course) list.get(list.size() - 1);
        transaction.commit();
        session.close();
        return course;
    }

}
