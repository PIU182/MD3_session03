package ra.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.api.model.Enrollment;
import ra.edu.api.repository.EnrollmentDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;

    public List<Enrollment> findAll() {
        return enrollmentDAO.findAll();
    }

    public Enrollment findById(Integer id) {
        return enrollmentDAO.findById(id);
    }

    public void create(Enrollment enrollment) {
        enrollmentDAO.create(enrollment);
    }
    public void update(Integer id, Enrollment enrollment) {
        enrollmentDAO.update(id, enrollment);
    }
    public void delete(Integer id) {
        enrollmentDAO.delete(id);
    }
}
