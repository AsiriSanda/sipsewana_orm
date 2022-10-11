package bo.custom.impl;

import bo.custom.RegistrationBo;
import dao.DaoFactory;
import dao.custom.QueryDAO;
import dao.custom.RegistrationDao;
import dto.CourseDto;
import dto.RegistrationDto;
import entity.Registration;

import java.util.ArrayList;

public class RegistrationBoImpl implements RegistrationBo {
    RegistrationDao registrationDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.REGISTRATION);
    QueryDAO queryDAO=DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);


    @Override
    public boolean save(RegistrationDto registrationDto) throws Exception {
        return registrationDao.save(new Registration(registrationDto.getRegno(),registrationDto.getRegdate()
                ,registrationDto.getRegfee(),registrationDto.getStudent(),registrationDto.getCourse()));
    }

    @Override
    public boolean update(RegistrationDto registrationDto) throws Exception {
        return registrationDao.update(new Registration(registrationDto.getRegno(),registrationDto.getRegdate()
                ,registrationDto.getRegfee(),registrationDto.getStudent(),registrationDto.getCourse()));
    }


    @Override
    public ArrayList<RegistrationDto> getAll() throws Exception {
        ArrayList<RegistrationDto> list = new ArrayList<>();
        ArrayList<Registration> all = registrationDao.getAll();
        for (Registration reg : all) {
            list.add(new RegistrationDto(reg.getRegno(),reg.getRegdate(),reg.getRegfee(),reg.getStudent(),reg.getCourse()));
        }
        return list;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }





    @Override
    public RegistrationDto search(String s) throws Exception {
        return null;
    }

    @Override
    public RegistrationDto gernarateId() throws Exception {
        Registration last = queryDAO.getLastRegistration();
        return new RegistrationDto(last.getRegno(),last.getRegdate(),last.getRegfee(),last.getStudent(),last.getCourse());
    }
}
