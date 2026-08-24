package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Course;
import ra.edu.api.model.Instructor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDAO {
    private final List<Course> courses = new ArrayList<>(
            List.of(
                    new Course(1, "Java Spring Boot", "ACTIVE", 1),
                    new Course(2, "HTML CSS", "ACTIVE", 2),
                    new Course(3, "JavaScript", "ACTIVE", 3)
            )
    );

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void create(Course course) {
        courses.add(course);
    }

    public void update(Integer id, Course course) {
        Course updatedCourse = findById(id);
        int index = courses.indexOf(updatedCourse);
        if (index == -1) {
            return;
        }
        courses.set(index, course);
    }

    public void delete(Integer id) {
        Course deleteCourse = findById(id);
        if (deleteCourse == null) {
            return;
        }
        courses.remove(deleteCourse);
    }

}
