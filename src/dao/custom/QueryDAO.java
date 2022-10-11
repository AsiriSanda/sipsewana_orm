package dao.custom;

import entity.Course;
import entity.Registration;
import entity.Student;

public interface QueryDAO {
    public Course getLastCourse()throws Exception;
    public Student getLastStudent()throws Exception;
    public Registration getLastRegistration()throws Exception;
}
