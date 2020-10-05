import java.util.List;

public interface IDatabase {
    void createGroup(Group group);

    void createStudent(Student student);

    void deleteGroup(GroupId groupId);

    void deleteStudent(StudentId studentId);

    void updateStudent(StudentId studentId, Student student);

    List<Group> readGroups();

    List<Student> readStudents();

    List<Student> readGroupStudents(GroupId groupId);
}
