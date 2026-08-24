package ra.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.api.model.Course;
import ra.edu.api.repository.CourseDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseDAO courseDAO;

    public List<Course> getAllCourse(){
        return courseDAO.findAll();
    }
    public Course getCourseById(int id){
        return courseDAO.findById(id);
    }
    public void createCourse(Course course){
        courseDAO.create(course);
    }
    public void updateCourse(Integer id, Course course){
        courseDAO.update(id, course);
    }
    public void deleteCourse(Integer id){
        courseDAO.delete(id);
    }
}
