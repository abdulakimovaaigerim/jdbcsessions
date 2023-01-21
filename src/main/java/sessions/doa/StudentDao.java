package sessions.doa;

import sessions.models.Student;

import java.util.List;

public interface StudentDao {
// create table
    void creatTable();

    //save students
    void saveStudents(Student student);

    //find by studentsId

    Student findByStudentId(Long studentsId);

    //find all

    List<Student> findAllStudent();


    //update students

    void update (Long studentId, Student newStudent);

    //delete

    void deleteByStudentId(Long studentID);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void deleteAllStudents(); //ddl
}
