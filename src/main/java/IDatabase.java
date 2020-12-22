import java.sql.Date;
import java.util.List;

public interface IDatabase {
    void createGroup(Group group);

    void createStudent(Student student);

    void deleteGroup(GroupId groupId);

    void deleteStudent(StudentId studentId);

    void updateStudent(StudentId studentId, Student student);

    void updateStudentTask(StudentId studentId, int taskNumber, String date);

    List<Group> readGroups();

    List<Student> readStudents();

    List<Student> readGroupStudents(GroupId groupId);
}
