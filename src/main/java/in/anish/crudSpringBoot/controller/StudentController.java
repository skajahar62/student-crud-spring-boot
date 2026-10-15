package in.anish.crudSpringBoot.controller;

import in.anish.crudSpringBoot.entity.Student;
import in.anish.crudSpringBoot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
class StudentController {


    private StudentService studentService;

    public  StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    //create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        Student createStudent= studentService.createStudent(student);
       return ResponseEntity
                 .status(HttpStatus.CREATED)
               .body(createStudent);
    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student studentResp= studentService.getStudent(id);

        if(studentResp ==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentList= studentService.getAllStudent();


        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,
                                                  @RequestBody Student studentReq){
        Student studentResp= studentService.updateStudent(id,studentReq);

        if(studentResp ==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }

    @DeleteMapping("/delete")

    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isDelete=studentService.deleteStudent(id);

        if(!isDelete){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record deleted");
    }

    @PatchMapping("/delete-soft")
    public  ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){
        Boolean isDeleted=studentService.deleteStudentSoftly(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record deleted");
    }


}
