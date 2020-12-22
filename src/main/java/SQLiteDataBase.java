import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SQLiteDataBase implements IDatabase {

    private Connection connection;
    private Statement statement;

    public SQLiteDataBase(String url) throws SQLException {
        System.out.println("Testing connection to SQLite JDBC");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("SQLite JDBC successfully connected");

        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            statement = connection.createStatement();
            connection.setAutoCommit(true);
        }
    }

    private ResultSet executeQuery(String sql){
        return executeQuery(sql, false);
    }

    @SneakyThrows
    private ResultSet executeQuery(String sql,  boolean needResult) {
        ResultSet resultSet = null;
        try {
            if (needResult){
                resultSet = statement.executeQuery(sql);
            }
            else
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                e.printStackTrace();
                return null;
            }
            return resultSet;
        }
        return resultSet;
    }

    @Override
    public void createGroup(Group group) {
        executeQuery(String.format("INSERT INTO 'groups' (groupId, name) VALUES (%d, '%s')",
                group.getGroupId().id, group.getName()));
    }

    @Override
    public void createStudent(Student student) {
        executeQuery(String.format("INSERT INTO 'students' (name, yearNumber, groupId, faculty, date1, date2, date3) VALUES ('%s', %d, %d, '%s', '%s', '%s', '%s')",
                student.getName(), student.getYearNumber(), student.getGroupId().id,
                student.getFaculty(), student.getTasks().get(0).getDate(), student.getTasks().get(1).getDate(),
                student.getTasks().get(2).getDate()));
    }

    @Override
    public void deleteGroup(GroupId groupId) {
        executeQuery("DELETE from 'groups' WHERE groupId = " + groupId.id);
    }

    @Override
    public void deleteStudent(StudentId studentId) {
        executeQuery("DELETE from 'students' WHERE studentId = " + studentId.cardNumber);
    }

    @Override
    public void updateStudent(StudentId studentId, Student student) {
        executeQuery(String.format("UPDATE 'students' SET name = '%s', yearNumber = %d, groupId = %d, faculty = '%s', date1 = '%s', date2 = '%s', date3 = '%s' WHERE studentId = %d",
                student.getName(), student.getYearNumber(), student.getGroupId().id,
                student.getFaculty(), student.getTasks().get(0).getDate(), student.getTasks().get(1).getDate(),
                student.getTasks().get(2).getDate(), studentId.cardNumber));
    }

    @Override
    public void updateStudentTask(StudentId studentId, int taskNumber, String date) {
        executeQuery(String.format("UPDATE 'students' SET '%s' = '%s'", "date" + taskNumber + 1, date));
    }

    @SneakyThrows
    @Override
    public List<Group> readGroups() {
        ResultSet resSet = executeQuery("SELECT * FROM groups;", true);
        List<Group> groups = new ArrayList<>();
        if (resSet != null) {
            while (resSet.next()) {
                int groupId = resSet.getInt("groupId");
                String name = resSet.getString("name");
                Group group = new Group(new GroupId(groupId), name);
                groups.add(group);
            }
        }
        return groups;
    }

    @SneakyThrows
    private Student readStudent(ResultSet resSet) {
        int studentId = resSet.getInt("studentId");
        int groupId = resSet.getInt("groupId");
        String name = resSet.getString("name");
        int yearNumber = resSet.getInt("yearNumber");
        String faculty = resSet.getString("faculty");
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i < 4; ++i) {
            String date = resSet.getString("date" + i);
            tasks.add(new Task(i, date));
        }
        return new Student(new StudentId(studentId), name, yearNumber,
                new GroupId(groupId), faculty, tasks);
    }

    @SneakyThrows
    @Override
    public List<Student> readStudents() {
        ResultSet resSet = executeQuery("SELECT * FROM 'students'", true);
        List<Student> students = new ArrayList<>();
        if (resSet != null) {
            while (resSet.next()) {
                students.add(readStudent(resSet));
            }
        }
        return students;
    }

    @SneakyThrows
    @Override
    public List<Student> readGroupStudents(GroupId groupId) {
        ResultSet resSet = executeQuery("SELECT * FROM 'students' WHERE groupId = " + groupId.id, true);
        List<Student> students = new ArrayList<>();
        if (resSet != null) {
            while (resSet.next()) {
                students.add(readStudent(resSet));
            }
        }
        return students;
    }
}
