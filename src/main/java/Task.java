import java.util.Date;

public class Task {
    private int numberTask;
    private boolean isDone;
    private Date date;

    public Task(int numberTask, boolean isDone, Date date) {
        this.numberTask = numberTask;
        this.isDone = isDone;
        this.date = date;
    }

    public int getNumberTask() {
        return numberTask;
    }

    public void setNumberTask(int numberTask) {
        this.numberTask = numberTask;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
