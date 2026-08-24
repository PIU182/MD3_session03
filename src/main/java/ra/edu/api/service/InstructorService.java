package ra.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.api.model.Instructor;
import ra.edu.api.repository.InstructorDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorDAO instructorDAO;

    public List<Instructor> getAll(){
         return instructorDAO.findAll();
    }

    public Instructor getInstructorById(Integer id){
        return instructorDAO.findById(id);
    }

    public void createInstructor(Instructor instructor){
        instructorDAO.create(instructor);
    }

    public void updateInstructor(Integer id, Instructor instructor){
        instructorDAO.update(id, instructor);
    }

    public void deleteInstructorById(Integer id){
        instructorDAO.delete(id);
    }
}
