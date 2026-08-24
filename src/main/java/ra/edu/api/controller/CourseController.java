package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.dto.ApiResponse;
import ra.edu.api.model.Course;
import ra.edu.api.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {

        List<Course> courses = courseService.getAllCourse();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách khóa học thành công",
                        courses
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(
            @PathVariable Integer id) {

        try {

            Course course = courseService.getCourseById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy khóa học thành công",
                            course
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
    public ResponseEntity<ApiResponse<Course>> createCourse(
            @RequestBody Course course) {

        courseService.createCourse(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Thêm khóa học thành công",
                        course
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(
            @PathVariable Integer id,
            @RequestBody Course course) {

        try {

            courseService.updateCourse(id, course);

            Course updatedCourse =
                    courseService.getCourseById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật khóa học thành công",
                            updatedCourse
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
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @PathVariable Integer id) {

        try {

            courseService.deleteCourse(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa khóa học thành công",
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