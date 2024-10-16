//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.User;

public class connectdb {
    SQLServerDataSource ds = new SQLServerDataSource();

    public connectdb() {
        this.ds.setUser("sa1");
        this.ds.setPassword("123");
        this.ds.setServerName("tm14");
        this.ds.setPortNumber(1433);
        this.ds.setDatabaseName("todolistwithoutaccount");
        this.ds.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    public void addnewuser(String username, String password) {
        String add = "INSERT INTO users(username,password) VALUES(?,?)";

        try {
            PreparedStatement psmt = this.getConnection().prepareStatement(add);
            psmt.setString(1, username);
            psmt.setString(2, password);
            int rowsAffected = psmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        } catch (SQLException var6) {
            SQLException e = var6;
            e.printStackTrace();
        }

    }

    public List<User> returnuser() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList();

        try {
            PreparedStatement psmt = this.getConnection().prepareStatement(query);
            ResultSet rs = psmt.executeQuery();

            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username, password));
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            e.printStackTrace();
        }

        return users;
    }

    public void addtask(Task task) {
        String query = "INSERT INTO task(taskid,taskdes,Status,createat,username) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            pstmt.setInt(1, task.getId());
            pstmt.setString(2, task.getTaskDes());
            pstmt.setString(3, task.getStatus());
            pstmt.setString(4, task.getDuedate());
            pstmt.setString(5, task.getUsername());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Task inserted: " + rowsAffected);
        } catch (SQLException var5) {
            SQLException e = var5;
            e.printStackTrace();
        }

    }

    public List<Task> returntable() {
        String query = "SELECT taskid,taskdes,Status,createat,username FROM task";
        List<Task> tasks = new ArrayList();

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("taskid");
                String des = rs.getString("taskdes");
                String status = rs.getString("Status");
                String duedate = rs.getString("createat");
                String username = rs.getString("username");
                tasks.add(new Task(id, des, status, duedate, username));
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            e.printStackTrace();
        }

        return tasks;
    }

    public void deleteTask(int id) {
        String query = "DELETE FROM task WHERE taskid =" + id;

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Task deleted: " + rowsAffected);
        } catch (SQLException var5) {
            SQLException e = var5;
            e.printStackTrace();
        }

    }

    public void changeStatus(int id, String status) {
        String query = "UPDATE task SET Status = ? WHERE taskid = ?";

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Task updated: " + rowsAffected);
        } catch (SQLException var6) {
            SQLException e = var6;
            e.printStackTrace();
        }

    }
}
