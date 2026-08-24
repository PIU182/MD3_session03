package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.model.Enrollment;
import ra.edu.api.service.EnrollmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAll() {
        enrollmentService.findAll();
        return ResponseEntity.ok().body(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getOne(@PathVariable Integer id) {
        if  (enrollmentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(enrollmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Enrollment> create(@RequestBody Enrollment enrollment) {
        enrollmentService.create(enrollment);
        return ResponseEntity.ok().body(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Integer id, @RequestBody Enrollment enrollment) {
        Enrollment updateEnrollment = enrollmentService.findById(id);
        if (updateEnrollment == null) {
            return ResponseEntity.notFound().build();
        }
        enrollmentService.update(id, enrollment);
        return ResponseEntity.ok().body(updateEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enrollment> delete(@PathVariable Integer id) {
        Enrollment deleteEnrollment = enrollmentService.findById(id);
        if (deleteEnrollment == null) {
            return ResponseEntity.notFound().build();
        }
        enrollmentService.delete(id);
        return ResponseEntity.ok().body(deleteEnrollment);
    }
}
