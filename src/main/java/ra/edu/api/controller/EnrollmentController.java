package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.dto.ApiResponse;
import ra.edu.api.model.Enrollment;
import ra.edu.api.service.EnrollmentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {

        List<Enrollment> enrollments =
                enrollmentService.findAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách đăng ký thành công",
                        enrollments
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getOne(
            @PathVariable Integer id) {

        try {

            Enrollment enrollment =
                    enrollmentService.findById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy thông tin đăng ký thành công",
                            enrollment
                    )
            );

        } catch (NoSuchElementException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(
            @RequestBody Enrollment enrollment) {

        enrollmentService.create(enrollment);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Thêm đăng ký thành công",
                                enrollment
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(
            @PathVariable Integer id,
            @RequestBody Enrollment enrollment) {

        try {

            enrollmentService.update(id, enrollment);

            Enrollment updatedEnrollment =
                    enrollmentService.findById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật đăng ký thành công",
                            updatedEnrollment
                    )
            );

        } catch (NoSuchElementException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {

        try {

            enrollmentService.delete(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa đăng ký thành công",
                            null
                    )
            );

        } catch (NoSuchElementException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }
}