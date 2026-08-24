package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class InstructorDAO {

    private final List<Instructor> instructors = new ArrayList<>(
            List.of(
                    new Instructor(1, "Nguyen Van A", "nva@gmail.com"),
                    new Instructor(2, "Tran Thi B", "ttb@gmail.com"),
                    new Instructor(3, "Nguyen Van C", "nvc@gmail.com")
            )
    );

    public List<Instructor> findAll() {
        return instructors;
    }

    // Optional thay cho null
    public Optional<Instructor> findById(Integer id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst();
    }

    public void create(Instructor instructor) {
        instructors.add(instructor);
    }

    public void update(Integer id, Instructor instructor) {

        Instructor oldInstructor = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy giảng viên có id = " + id
                        )
                );

        int index = instructors.indexOf(oldInstructor);

        // Giữ id theo URL
        instructor.setId(id);

        instructors.set(index, instructor);
    }

    public void deleteById(Integer id) {

        Instructor instructor = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy giảng viên có id = " + id
                        )
                );

        instructors.remove(instructor);
    }
}