import java.util.Date;

public class Task {
    private int numberTask;
    private Date date;

    public Task(int numberTask, Date date) {
        this.numberTask = numberTask;
        this.date = date;
    }

    public int getNumberTask() {
        return numberTask;
    }

    public void setNumberTask(int numberTask) {
        this.numberTask = numberTask;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
