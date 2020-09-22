public class Student {
    private String name;
    private int cardNumber;
    private int yearNumber;
    private int groupNumber;
    private String faculty;
    private Task[] tasks;


    public Student(String name, int cardNumber, int yearNumber, int groupNumber, String faculty, Task[] tasks) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.yearNumber = yearNumber;
        this.groupNumber = groupNumber;
        this.faculty = faculty;
        this.tasks = tasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
