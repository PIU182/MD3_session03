package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    // Optional thay cho null
    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public void create(Course course) {
        courses.add(course);
    }

    public void update(Integer id, Course course) {

        Course oldCourse = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy khóa học có id = " + id
                        )
                );

        int index = courses.indexOf(oldCourse);

        courses.set(index, course);
    }

    public void deleteById(Integer id) {

        Course course = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy khóa học có id = " + id
                        )
                );

        courses.remove(course);
    }
}