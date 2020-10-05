//public class SQLDatabase : IDatabase {
//}

//public class GUICrudEditor {
//}

import com.sun.source.util.SourcePositions;

import java.net.SocketOption;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CUICrudEditor implements ICRUDEditor {
    IDatabase database;

    public CUICrudEditor(IDatabase database) {
        this.database = database;
    }

    private void outputGroup(Group curr) {
        System.out.println("Группа " + curr.getGroupId().name);
    }

    private void outputStudent(Student curr) {
        System.out.printf("id = %d %s %d курс %s группа %s факультет \n", curr.getStudentId().cardNumber, curr.getName(),
                curr.getYearNumber(), curr.getGroupId().name, curr.getFaculty());
    }

    private void outputStudentTask(Student curr) {
        System.out.printf("id = %d %s \n", curr.getStudentId().cardNumber, curr.getName());
        int i = 1;
        for (Task task : curr.getTasks()) {
            System.out.printf("Задание № %d ", i++);
            if (task.getDate() != null) {
                System.out.format("Сдана %tD%n\n", task.getDate());
            } else {
                System.out.print("не сдана\n");
            }
        }
    }

    private boolean groupExist(String idGroup) {
        List<Group> groups = database.readGroups();
        for (Group curr : groups) {
            if (curr.getGroupId().name.equals(idGroup)) {
                return true;
            }
        }
        return false;
    }

    private boolean studentExist(StudentId studentId) {
        List<Student> students = database.readStudents();
        for (Student student : students) {
            if (student.getStudentId().cardNumber == studentId.cardNumber)
                return true;
        }
        return false;
    }

    private void showMainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать. Для работы с системой используйте клавиатуру.");
        System.out.println("Введите 1 для работы с группами");
        System.out.println("Введите 2 для работы со студентами");
        System.out.println("Введите 3 для сдачи задач");
        System.out.println("Введите 4 для завершения работы");
        int command = sc.nextInt();
        switch (command) {
            case 1:
                for (Group curr : this.database.readGroups()) {
                    outputGroup(curr);
                }
                showCRUDGroupForm();
                break;
            case 2:
                for (Student curr : this.database.readStudents()) {
                    outputStudent(curr);
                }
                showCRUDStudentForm();
                break;
            case 3:
                showCheckTaskMenu();
                break;
            case 4:
                break;
        }

    }

    private void showCheckTaskMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите группу для отображения информации о сдаче задач");
        String groupId = sc.next();
        if (groupExist(groupId)) {
            List<Student> students = database.readGroupStudents(new GroupId(groupId));
            for (Student curr : students) {
                outputStudentTask(curr);
            }
            System.out.println("Введите id студента для изменения ведомости по сдаче задач");
            StudentId studentId = new StudentId(sc.nextInt());
            if (!studentExist(studentId)) {
                System.out.println("Студент не существует");
            } else {
                System.out.println("Введите номер задания для изменения");
                int number = sc.nextInt();
                if (number > PlaintextDatabase.COUNT_TASKS) {
                    System.out.println("Некорректный номер задания");
                    showCheckTaskMenu();
                }
                number -= 1;
                System.out.println("Введите дату сдачи в формате yyyy/mm/dd");
                Date date = Date.valueOf(sc.next());


            }
        } else {
            System.out.println("Такой группы не существует");
        }
        showMainMenu();
    }

    private void showCRUDStudentForm() {
        System.out.println("Введите 1 для добавления нового студента");
        System.out.println("Введите 2 для редактирования студента");
        System.out.println("Введите 3 для удаления существующего студента");
        System.out.println("Введите 4 для выхода в главное меню");
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        switch (command) {
            case 1 -> showCreateStudentForm();
            case 2 -> showEditStudentForm();
            case 3 -> {
                System.out.println("Введите id студента");
                StudentId deleteId = new StudentId(sc.nextInt());
                if (!studentExist(deleteId)) {
                    System.out.println("Студент с таким id не существует");
                } else {
                    database.deleteStudent(deleteId);
                    System.out.println("Студент успешно удален");
                }
                showCRUDStudentForm();
            }
            case 4 -> showMainMenu();
        }
    }

    private void showEditStudentForm() {
        System.out.println("Введите id студента");
        Scanner sc = new Scanner(System.in);
        StudentId createId = new StudentId(sc.nextInt());
        if (!studentExist(createId)) {
            System.out.println("Студент с таким id не существует");
            showCRUDStudentForm();
        } else {
            Student newStudent = createStudent(createId);
            this.database.createStudent(newStudent);
        }
    }

    private void showCRUDGroupForm() {
        System.out.println("Введите 1 для добавления новой группы");
        System.out.println("Введите 2 для удаления существующей группы. Удалять можно только пустые группы.");
        System.out.println("Введите 3 для выхода в главное меню");
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        switch (command) {
            case 1 -> {
                System.out.println("Введите id новой группы");
                String newName = sc.next();
                if (groupExist(newName)) {
                    System.out.println("Группа с таким id уже существует. Возвращение в меню редактирования групп");
                    showCRUDGroupForm();
                }
                Group group = new Group(new GroupId(newName));
                database.createGroup(group);
            }
            case 2 -> {
                System.out.println("Введите id группы");
                String idDelete = sc.next();
                if (!groupExist(idDelete)) {
                    System.out.println("Группа с таким id не существует");
                    showCRUDGroupForm();
                }
                List<Student> students = database.readGroupStudents(new GroupId(idDelete));
                if (students.size() != 0) {
                    System.out.println("Невозможно удалить группу, в которой есть студенты. Возвращение в меню редактирования групп");
                } else {
                    database.deleteGroup(new GroupId(idDelete));
                    System.out.println("Группа успешно удалена. Возвращение в меню редактирования групп");
                }
                showCRUDGroupForm();
            }
            case 3 -> showMainMenu();
        }
    }

    private void showCreateStudentForm() {
        System.out.println("Введите id студента");
        Scanner sc = new Scanner(System.in);
        StudentId createId = new StudentId(sc.nextInt());
        if (studentExist(createId)) {
            System.out.println("Студент с таким id уже существует");
            showCRUDStudentForm();
        } else {
            Student newStudent = createStudent(createId);
            this.database.createStudent(newStudent);
        }

    }

    private Student createStudent(StudentId studentId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя студента");
        String name = sc.next();
        System.out.println("Введите курс");
        int year = sc.nextInt();
        String groupId;
        while (true) {
            System.out.println("Введите id группы");
            groupId = sc.next();
            if (groupExist(groupId)) break;
            System.out.println("Не существует такой группы");
        }
        System.out.println("Введите имя факультета");
        String faculty = sc.next();
        List<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < PlaintextDatabase.COUNT_TASKS; ++i) {
            Task task = new Task(i + 1, null);
            tasks.add(task);
        }
        Student newStudent = new Student(studentId, name, year, new GroupId(groupId), faculty, tasks);
        return newStudent;
    }


    public void run() {
        showMainMenu();
    }

}
