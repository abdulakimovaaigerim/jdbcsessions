package sessions;

import servise.StudentServiceImpl;
import sessions.models.Student;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {


        StudentServiceImpl service = new StudentServiceImpl();
        System.out.println(service.saveStudents(new Student("Aigerim", (byte) 17)));

        System.out.println(service.findAllStudent());

    }
}
