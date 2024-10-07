package br.com.alura.ProjetoAlura.course;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @PostMapping("/course/new")
    public ResponseEntity<Void> createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        // TODO: Implementar a Questão 1 - Cadastro de Cursos aqui...
        if (!isValidCourseCode(newCourse.getCode())) {
            return ResponseEntity.badRequest().build(); 
        }

        if (courseService.existsByCode(newCourse.getCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
        }

        Course course = new Course();
        course.setName(newCourse.getName());
        course.setCode(newCourse.getCode());
        course.setInstructor(newCourse.getInstructor());
        course.setDescription(newCourse.getDescription());
        course.setStatus(CourseStatus.ACTIVE); 

        courseService.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    private boolean isValidCourseCode(String code) {
        return code.matches("^[a-zA-Z\\-]{4,10}$");
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {
        // TODO: Implementar a Questão 2 - Inativação de Curso aqui...

        return ResponseEntity.ok().build();
    }

}