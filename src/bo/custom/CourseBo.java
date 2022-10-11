package bo.custom;

import bo.SuperBO;
import dto.CourseDto;

import java.util.ArrayList;

public interface CourseBo extends SuperBO {
    public boolean save(CourseDto courseDto)throws Exception;
    public boolean update(CourseDto courseDto)throws Exception;
    public boolean delete(String s)throws Exception;
    public CourseDto search(String s)throws Exception;
    public ArrayList<CourseDto> getAll()throws Exception;
    public CourseDto gernarateId() throws Exception;
}
