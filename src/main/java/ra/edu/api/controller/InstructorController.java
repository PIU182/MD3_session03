package ra.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.api.model.Instructor;
import ra.edu.api.service.InstructorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {
        return ResponseEntity.ok(instructorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable int id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor){
        instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(@PathVariable int id, @RequestBody Instructor instructor){
        Instructor updateInstructor = instructorService.getInstructorById(id);
        if(updateInstructor == null){
            return ResponseEntity.notFound().build();
        }
        instructorService.updateInstructor(id, instructor);
        return ResponseEntity.ok(updateInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> delete(@PathVariable int id){
        Instructor deleteInstructor = instructorService.getInstructorById(id);
        if(deleteInstructor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        instructorService.deleteInstructorById(id);
        return ResponseEntity.ok(deleteInstructor);
    }
}
