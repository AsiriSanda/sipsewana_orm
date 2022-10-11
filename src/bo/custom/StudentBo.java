package bo.custom;

import bo.SuperBO;
import dto.CourseDto;
import dto.StudentDto;

import java.util.ArrayList;

public interface StudentBo extends SuperBO {
    boolean save(StudentDto studentDto)throws Exception;
    boolean update(StudentDto studentDto)throws Exception;
    boolean delete(String s)throws Exception;
    StudentDto search(String s)throws Exception;
    ArrayList<StudentDto> getAll()throws Exception;
    StudentDto gernarateId() throws Exception;
}
