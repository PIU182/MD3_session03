package ra.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.api.model.Enrollment;
import ra.edu.api.repository.EnrollmentDAO;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentDAO enrollmentDAO;

    public List<Enrollment> findAll() {
        return enrollmentDAO.findAll();
    }

    public Enrollment findById(Integer id) {
        return enrollmentDAO.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy đăng ký có id = " + id
                        )
                );
    }

    public void create(Enrollment enrollment) {
        enrollmentDAO.create(enrollment);
    }

    public void update(Integer id, Enrollment enrollment) {
        enrollmentDAO.update(id, enrollment);
    }

    public void delete(Integer id) {
        enrollmentDAO.deleteById(id);
    }
}