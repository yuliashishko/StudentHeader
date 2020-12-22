import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SQLiteDataBase implements IDatabase {

    private Connection connection;

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
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    private ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                e.printStackTrace();
                connection.rollback();
                return null;
            }
            return resultSet;
        }
        return resultSet;
    }

    @Override
    public void createGroup(Group group) {

    }

    @Override
    public void createStudent(Student student) {

    }

    @Override
    public void deleteGroup(GroupId groupId) {

    }

    @Override
    public void deleteStudent(StudentId studentId) {

    }

    @Override
    public void updateStudent(StudentId studentId, Student student) {

    }

    @Override
    public void updateStudentTask(StudentId studentId, int taskNumber, String date) {

    }

    @SneakyThrows
    @Override
    public List<Group> readGroups() {
        ResultSet resSet = executeQuery("SELECT * FROM groups");
        List<Group> groups = new ArrayList<>();
        if (resSet != null) {
            while (resSet.next()){
                int id_group = resSet.getInt("id_group");
                String name = resSet.getString("name_group");
                Group group = new Group(new GroupId(id_group), name);
                groups.add(group);
            }
        }
        return groups;
    }

    @Override
    public List<Student> readStudents() {
        return null;
    }

    @Override
    public List<Student> readGroupStudents(GroupId groupId) {
        return null;
    }
}
