import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Group {
    private int groupNumber;
    private List<Student> students;

    public Group(int groupNumber, ArrayList<Student> students, TreeMap<Student, ArrayList<Task>> studentTasks) {
        this.groupNumber = groupNumber;
        this.students = students;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

}
