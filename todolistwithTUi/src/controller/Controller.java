//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import database.connectdb;
import java.util.Iterator;
import model.Task;
import model.User;
import model.taskList;
import view.view;

public class Controller {
    view view = new view();
    connectdb db = new connectdb();
    taskList taskList = new taskList();
    private String username;

    public Controller(view view, connectdb db, taskList taskList) {
    }

    public void loginpart() {
        boolean t = true;

        while(t) {
            int choice = this.view.getLoginMenu();
            switch (choice) {
                case 1:
                    if (this.login()) {
                        t = false;
                    }
                    break;
                case 2:
                    if (this.addnewuser()) {
                        t = false;
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        }

    }

    public void start() {
        if (this.username != null) {
            while(true) {
                while(true) {
                    int choice = this.view.getMenuChoice();
                    switch (choice) {
                        case 1:
                            this.addtask();
                            break;
                        case 2:
                            this.displayAll();
                            break;
                        case 3:
                            this.changeTaskStatus();
                            break;
                        case 4:
                            this.deletetask();
                            break;
                        case 5:
                            this.loginpart();
                    }
                }
            }
        }

    }

    public boolean addnewuser() {
        String userdata = this.view.useradd();
        String[] parts = userdata.split("-");
        boolean check = true;
        Iterator var4 = this.db.returnuser().iterator();

        while(var4.hasNext()) {
            User us = (User)var4.next();
            if (parts[0].equals(us.getUsername())) {
                check = false;
                break;
            }
        }

        if (!check) {
            System.out.println("Duplicate Username!");
            return false;
        } else {
            this.db.addnewuser(parts[0], parts[1]);
            System.out.println("Register successful!");
            return true;
        }
    }

    public boolean login() {
        User u = this.view.getUserLogin();
        boolean check = false;
        Iterator var3 = this.db.returnuser().iterator();

        while(var3.hasNext()) {
            User us = (User)var3.next();
            if (u.getUsername().equals(us.getUsername()) && u.getPassword().equals(us.getPassword())) {
                this.username = us.getUsername();
                check = true;
                break;
            }
        }

        if (check) {
            System.out.println("Login Successful");
            this.username = u.getUsername();
            return true;
        } else {
            System.out.println("Wrong Username or Password");
            return false;
        }
    }

    public void addtask() {
        Task task = this.view.getTask(this.username);
        this.db.addtask(task);
    }

    public void displayAll() {
        this.view.displayAll(this.db.returntable(), this.username);
    }

    public void deletetask() {
        this.displayAll();
        this.db.deleteTask(this.view.getTaskID());
    }

    public void changeTaskStatus() {
        this.displayAll();
        int id = this.view.getTaskID();
        boolean check = false;
        Iterator var3 = this.db.returntable().iterator();

        while(var3.hasNext()) {
            Task task = (Task)var3.next();
            if (task.getId() == id && task.getUsername().equals(this.username)) {
                if (task.getStatus().equals("Pending")) {
                    this.db.changeStatus(id, "Completed");
                } else if (task.getStatus().equals("Completed")) {
                    this.db.changeStatus(id, "Pending");
                }

                check = true;
                break;
            }
        }

        if (!check) {
            System.out.println("No task found");
        } else {
            System.out.println("Changed!");
        }

    }
}
