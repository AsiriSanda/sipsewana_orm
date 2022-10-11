package dao.custom.impl;

import dao.custom.RegistrationDao;
import entity.Registration;
import entity.Student;
import entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {


    @Override
    public boolean update(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return  true;
    }

    @Override
    public boolean save(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return  true;
    }



    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Registration> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query registration= session.createQuery("from Registration ");
        List list = registration.list();
        transaction.commit();
        session.close();
        return (ArrayList<Registration>) list;
    }

    @Override
    public Registration search(Integer integer) throws Exception {
        return null;
    }


}
