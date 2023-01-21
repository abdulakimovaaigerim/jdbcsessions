package sessions.doa;

import sessions.config.DatabaseConnection;
import sessions.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private Connection connection;

    public StudentDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void creatTable() {
        String query = """
                create table if not exists students(
                id serial primary key , 
                name varchar (50) not null ,
                age smallint not null);
                """;
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void saveStudents(Student student) {
        String query = """
                insert into students(name, age) 
                values(?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setByte(2, student.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public Student findByStudentId(Long studentsId) {
        String query = """
                select * from students where id = ?;
                """;
        try (Connection connection1 = connection) {
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            Student student = new Student();

            while (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte("age"));
            }

            return student;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Student> findAllStudent() {
        String query = "select * from students;";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            List<Student> studentList = new ArrayList<>();
            while(resultSet.next()){
                studentList.add(new Student(
                        resultSet.getString("name"),
                        resultSet.getByte("age")));
            }
            return studentList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long studentId, Student newStudent) {
        String query = """
                update students
                 set name =?,
                 set age = ?
                 where id =  ?
                 """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Successfully updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteByStudentId(Long studentID) {
        String query = "delete from students where id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, studentID);
            System.out.println("Successfully deleted student by id!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        List<Student> studentList = new ArrayList<>();

        String query = "select * from student age group by desc;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                byte age = resultSet.getByte("age");
                Student student = new Student(name, age);
                studentList.add(student);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public boolean checkByAge() {
        for (Student student : getAllStudentsSortByAge("desc")) {
            if (student.getAge() <= 18) {
                return false;
            }

        }
        return true;
    }


    @Override
    public void deleteAllStudents() {
        String query = "truncate table students;";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("truncate table on database!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}