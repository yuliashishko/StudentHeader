import java.util.Date;

public class Task {
    private int numberTask;
    private String date;

    public Task(int numberTask, String date) {
        this.numberTask = numberTask;
        this.date = date;
    }

    public int getNumberTask() {
        return numberTask;
    }

    public void setNumberTask(int numberTask) {
        this.numberTask = numberTask;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
