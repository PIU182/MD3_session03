package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Instructor;

import java.util.ArrayList;
import java.util.List;

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

    public Instructor findById(Integer id) {
        return  instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void  create(Instructor instructor) {
        instructors.add(instructor);
    }

    public void  update(Integer id, Instructor instructor) {
        Instructor updateInstructor = findById(id);
        int index = instructors.indexOf(updateInstructor);

        if (index == -1){
            return;
        }
        instructors.set(index, instructor);
    }

    public void delete(Integer id) {
        instructors.remove(findById(id));
    }
}
