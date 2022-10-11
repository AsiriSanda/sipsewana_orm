package bo.custom.impl;

import bo.custom.StudentBo;
import dao.DaoFactory;
import dao.custom.QueryDAO;
import dao.custom.StudentDao;
import dto.CourseDto;
import dto.StudentDto;
import entity.Course;
import entity.Student;

import java.util.ArrayList;

public class StudentBoImpl implements StudentBo {
    StudentDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STUDENT);
    QueryDAO queryDAO=DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);


    @Override
    public boolean save(StudentDto studentDto) throws Exception {
        return dao.save(new Student(studentDto.getId(),studentDto.getName(),studentDto.getAddress()
                ,studentDto.getContact(),studentDto.getDate(),studentDto.getGender()));
    }



    @Override
    public boolean delete(String s) throws Exception {
        return dao.delete(s);
    }

    @Override
    public boolean update(StudentDto studentDto) throws Exception {
        return dao.update(new Student(studentDto.getId(),studentDto.getName(),studentDto.getAddress()
                ,studentDto.getContact(),studentDto.getDate(),studentDto.getGender()));
    }



    @Override
    public ArrayList<StudentDto> getAll() throws Exception {
        ArrayList<StudentDto> list = new ArrayList<>();
        ArrayList<Student> all = dao.getAll();
        for (Student student : all) {
            list.add(new StudentDto(student.getId(), student.getName(), student.getAddress()
                    , student.getContact(), student.getDate(), student.getGender()));
        }
        return list;
    }

    @Override
    public StudentDto search(String s) throws Exception {
        Student search = dao.search(s);
        return new StudentDto(search.getId(),search.getName(),search.getAddress(),search.getContact(),search.getDate(),search.getGender());
    }

    @Override
    public StudentDto gernarateId() throws Exception {
        Student lastStudent = queryDAO.getLastStudent();
        return new StudentDto(lastStudent.getId(), lastStudent.getName(), lastStudent.getAddress()
                , lastStudent.getContact(), lastStudent.getDate(),lastStudent.getGender());


    }
}
