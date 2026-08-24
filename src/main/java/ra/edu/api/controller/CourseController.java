package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.model.Course;
import ra.edu.api.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id){
        Course course = courseService.getCourseById(id);
        if (course == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course course){
        Course updateCourse = courseService.getCourseById(id);
        if (updateCourse == null){
            return ResponseEntity.notFound().build();
        }
        courseService.updateCourse(id, course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Integer id){
        Course deleteCourse = courseService.getCourseById(id);
        if (deleteCourse == null){
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.ok(deleteCourse);
    }
}
