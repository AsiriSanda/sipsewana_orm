package bo.custom.impl;

import bo.custom.CourseBo;
import dao.DaoFactory;
import dao.custom.CourseDao;
import dao.custom.QueryDAO;
import dto.CourseDto;
import entity.Course;

import java.util.ArrayList;

public class CourseBoImpl implements CourseBo {
    CourseDao courseDao=DaoFactory.getInstance().getDao(DaoFactory.DaoType.COURSE);
    QueryDAO queryDAO=DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);
    @Override
    public boolean save(CourseDto courseDto) throws Exception {
        return courseDao.save(new Course(courseDto.getId(),courseDto.getName()
                ,courseDto.getType(),courseDto.getDuration()));
    }

    @Override
    public boolean update(CourseDto courseDto) throws Exception {
        return courseDao.update(new Course(courseDto.getId(),courseDto.getName()
                ,courseDto.getType(),courseDto.getDuration()));

    }

    @Override
    public boolean delete(String s) throws Exception {
        return courseDao.delete(s);
    }



    @Override
    public ArrayList<CourseDto> getAll() throws Exception {
        ArrayList<CourseDto> dto = new ArrayList<>();
        ArrayList<Course> all = courseDao.getAll();
        for (Course course : all) {
            dto.add(new CourseDto(course.getId(),course.getName(),course.getType(),course.getDuration()));
        }
        return dto;
    }
    public CourseDto gernarateId() throws Exception {
        Course lastCourse = queryDAO.getLastCourse();
        return new CourseDto(lastCourse.getId(),lastCourse.getName(),lastCourse.getType(),lastCourse.getDuration());

    }

    @Override
    public CourseDto search(String s) throws Exception {
        Course search = courseDao.search(s);
        return new CourseDto(search.getId(),search.getName(),search.getType(),search.getDuration());
    }
}
