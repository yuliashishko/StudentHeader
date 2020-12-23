import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class PlaintextDatabase implements IDatabase {
    public static int COUNT_TASKS = 3;
    Map<StudentId, Student> students;
    Map<GroupId, Group> groups;

    public PlaintextDatabase(String path) {
        students = new HashMap<>();
        groups = new HashMap<>();
        Scanner sc = null;
        try {
            sc = new Scanner(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String[] curr = sc.nextLine().split(" ");
            if (curr.length == 3) {
                GroupId groupId = new GroupId(Integer.parseInt(curr[2]));
                Group group = new Group(groupId, curr[1]);
                groups.put(groupId, group);
            } else if (curr.length == 7 + COUNT_TASKS) {
                StudentId studentId = new StudentId(Integer.parseInt(curr[3]));
                String name = curr[0] + ' ' + curr[1] + ' ' + curr[2];
                List<Task> tasks = new ArrayList<Task>();
                for (int i = 0; i < COUNT_TASKS; ++i) {
                    Task task = new Task(i + 1, curr[7 + i].equals("null") ? null : curr[7 + i]);
                    tasks.add(task);
                }

                Student student = new Student(studentId, name, Integer.parseInt(curr[4]),
                        new GroupId(Integer.parseInt(curr[5])), curr[6], tasks);
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


    public void updateStudentTask(StudentId studentId, int taskNumber, String date) {
        List<Task> tasks = students.get(studentId).getTasks();
        tasks.get(taskNumber).setDate(date);
    }
}
