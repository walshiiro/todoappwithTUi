

package model;

public class Task {
    private int id;
    private String taskDes;
    private String duedate;
    private String status;
    private String username;

    public Task(int id, String taskDes, String status, String duedate, String username) {
        this.id = id;
        this.taskDes = taskDes;
        this.duedate = duedate;
        this.status = status;
        this.username = username;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDes() {
        return this.taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getDuedate() {
        return this.duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return this.username;
    }

    public String toString() {
        return "Task [id=" + this.id + ", taskDes=" + this.taskDes + ", duedate=" + this.duedate + ", status=" + this.status + "]";
    }
}
