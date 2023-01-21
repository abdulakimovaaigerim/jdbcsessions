package servise;

import sessions.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    // create table
    String creatTable();

    //save students
    String saveStudents(Student student);

    //find by studentsId

    Student findByStudentId(Long studentsId);

    //find all

    List<Student> findAllStudent();

    //update students

    String update (Long studentId, Student newStudent);

    //delete

    String deleteByStudentId(Long studentID);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void deleteAllStudents(); //ddl
}
