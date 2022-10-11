package dao;

import dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return (daoFactory==null)?daoFactory=new DaoFactory():daoFactory;
    }
    public enum DaoType {
        QUERY,COURSE,STUDENT,REGISTRATION
    }
    public <T> T getDao(DaoType daoType){
        switch (daoType){
            case QUERY:
                return (T) new QueryDaoImpl();
            case COURSE:
                return (T) new CourseDaoImpl();
            case STUDENT:
                return (T) new StudentDaoImpl();
            case REGISTRATION:
                return (T) new RegistrationDaoImpl();
            default:
                return null;
        }
    }
}
