import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class PlaintextDatabase implements IDatabase {
    public static int COUNT_TASKS = 3;
    TreeMap<StudentId, Student> students;
    TreeMap<GroupId, Group> groups;

    public PlaintextDatabase(String path) {
        Scanner sc = new Scanner(path);
        while (sc.hasNext()) {
            String[] curr = sc.nextLine().split(" ");
            if (curr.length == 2) {
                GroupId groupId = new GroupId(curr[1]);
                Group group = new Group(groupId);
                groups.put(groupId, group);
            } else if (curr.length == 7 + COUNT_TASKS) {
                StudentId studentId = new StudentId(Integer.parseInt(curr[3]));
                String name = curr[0] + curr[1] + curr[2];
                List<Task> tasks = new ArrayList<Task>();
                for (int i = 0; i < COUNT_TASKS; ++i) {
                    Task task = new Task(i + 1, curr[7 + i] == "null" ? null : Date.valueOf(curr[7 + i]));
                    tasks.add(task);
                }

                Student student = new Student(studentId, name, Integer.parseInt(curr[4]), new GroupId(curr[5]),
                        curr[6], tasks);
                students.put(studentId, student);
            }
        }
    }

    @Override
    public List<Group> readGroups() {
        return new ArrayList<>(groups.values());
    }

    public List<Student> readStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> readGroupStudents(GroupId groupId) {
        List<Student> result = new ArrayList<>();
        for (Student curr : students.values()) {
            if (groupId.equals(curr.getGroupId())) {
                result.add(curr);
            }
        }
        return result;
    }

    @Override
    public void createGroup(Group group) {
        groups.put(group.getGroupId(), group);
    }

    public void createStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void deleteGroup(GroupId groupId) {
        groups.remove(groupId);
    }

    public void deleteStudent(StudentId studentId) {
        students.remove(studentId);
    }

    public void updateStudent(StudentId studentId, Student student) {
        students.put(studentId, student);
    }

    public void updateStudentTasks(StudentId studentId, List<Task> tasks) {
        students.get(studentId).setTasks(tasks);
    }
}
