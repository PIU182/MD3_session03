package ra.edu.api.repository;

import org.springframework.stereotype.Repository;
import ra.edu.api.model.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    // Optional thay cho null
    public Optional<Enrollment> findById(Integer id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst();
    }

    public void create(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void update(Integer id, Enrollment enrollment) {

        Enrollment oldEnrollment = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy đăng ký có id = " + id
                        )
                );

        int index = enrollments.indexOf(oldEnrollment);

        // Giữ id theo URL
        enrollment.setId(id);

        enrollments.set(index, enrollment);
    }

    public void deleteById(Integer id) {

        Enrollment enrollment = findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy đăng ký có id = " + id
                        )
                );

        enrollments.remove(enrollment);
    }
}