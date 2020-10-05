import java.util.List;

public class Student {
    private StudentId studentId;
    private String name;
    private int yearNumber;
    private GroupId groupId;
    private String faculty;
    private List<Task> tasks;


    public GroupId getGroupId() {
        return groupId;
    }

    public Student(StudentId studentId, String name, int yearNumber, GroupId groupId, String faculty, List<Task> tasks) {
        this.studentId = studentId;
        this.name = name;
        this.yearNumber = yearNumber;
        this.groupId = groupId;
        this.faculty = faculty;
        this.tasks = tasks;
    }


    public String getName() {
        return name;
    }


    public StudentId getStudentId() {
        return studentId;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
