package servise;

import sessions.doa.StudentDao;
import sessions.doa.StudentDaoImpl;
import sessions.models.Student;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public String creatTable() {
        studentDao.creatTable();
        return "Successfully created!";
    }

    @Override
    public String saveStudents(Student student) {
        this.studentDao.saveStudents(student);
        return "Successfully saved!";
    }

    @Override
    public Student findByStudentId(Long studentsId) {
        return studentDao.findByStudentId(studentsId);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }

    @Override
    public String update(Long studentId, Student newStudent) {
        studentDao.update(studentId, newStudent);
        return "Successfully saved!";
    }

    @Override
    public String deleteByStudentId(Long studentID) {
        studentDao.deleteByStudentId(studentID);
        return "Successfully deleted!";
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        return studentDao.getAllStudentsSortByAge(ascOrDesc);
    }

    @Override
    public boolean checkByAge() {
        studentDao.checkByAge();
        return false;
    }


    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();

    }
}