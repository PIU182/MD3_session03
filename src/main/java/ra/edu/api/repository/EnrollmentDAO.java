package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentDAO {
    private final List<Enrollment> enrollments = new ArrayList<>(
            List.of(
                    new Enrollment(1, "Nguyen Van C", 1),
                    new Enrollment(2, "Le Van D", 2),
                    new Enrollment(3, "Tran Thi B", 3)
            )
    );

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(Integer id) {
        return enrollments.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    public void create(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void update(Integer id, Enrollment enrollment) {
        Enrollment oldEnrollment = findById(id);
        int index = enrollments.indexOf(oldEnrollment);
        if (index == -1) {
            return;
        }
        enrollments.set(index, enrollment);
    }

    public void delete(Integer id) {
        Enrollment oldEnrollment = findById(id);
        if  (oldEnrollment != null) {
            enrollments.remove(oldEnrollment);
        }
    }
}
