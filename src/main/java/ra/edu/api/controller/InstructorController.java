package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.dto.ApiResponse;
import ra.edu.api.model.Instructor;
import ra.edu.api.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> findAll() {

        List<Instructor> instructors = instructorService.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách giảng viên thành công",
                        instructors
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> findById(
            @PathVariable Integer id) {

        try {

            Instructor instructor =
                    instructorService.getInstructorById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy thông tin giảng viên thành công",
                            instructor
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
    public ResponseEntity<ApiResponse<Instructor>> create(
            @RequestBody Instructor instructor) {

        instructorService.createInstructor(instructor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Thêm giảng viên thành công",
                                instructor
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(
            @PathVariable Integer id,
            @RequestBody Instructor instructor) {

        try {

            instructorService.updateInstructor(id, instructor);

            Instructor updatedInstructor =
                    instructorService.getInstructorById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật giảng viên thành công",
                            updatedInstructor
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

            instructorService.deleteInstructorById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa giảng viên thành công",
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