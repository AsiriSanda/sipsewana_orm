package bo.custom;

import bo.SuperBO;
import dto.CourseDto;
import dto.RegistrationDto;

import java.util.ArrayList;

public interface RegistrationBo extends SuperBO {
    boolean save(RegistrationDto registrationDto)throws Exception;
    boolean update(RegistrationDto registrationDto)throws Exception;
    boolean delete(String s)throws Exception;
    RegistrationDto search(String s)throws Exception;
    ArrayList<RegistrationDto> getAll()throws Exception;
    RegistrationDto gernarateId() throws Exception;
}
