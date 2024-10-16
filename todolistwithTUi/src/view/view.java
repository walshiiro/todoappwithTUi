

package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import model.Task;
import model.User;

public class view {
    Scanner sc;

    public view() {
        this.sc = new Scanner(System.in);
    }

    public int getLoginMenu() {
        System.out.println("--- To-Do Application ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        while(true) {
            while(true) {
                try {
                    int choice = this.sc.nextInt();
                    this.sc.nextLine();
                    if (choice >= 1 && choice <= 3) {
                        return choice;
                    }

                    System.out.println("Please enter a valid choice");
                } catch (InputMismatchException var3) {
                    System.out.println("Please enter a valid choice");
                    this.sc.nextLine();
                }
            }
        }
    }

    public int getMenuChoice() {
        System.out.println("--- To-Do Application ---");
        System.out.println("1.Add task");
        System.out.println("2.Show all tasks");
        System.out.println("3.Change task status");
        System.out.println("4.Delete task");
        System.out.println("5.Logout");
        System.out.print("Enter your choice: ");

        while(true) {
            while(true) {
                try {
                    int choice = this.sc.nextInt();
                    this.sc.nextLine();
                    if (choice >= 1 && choice <= 5) {
                        return choice;
                    }

                    System.out.println("Please enter a valid choice");
                } catch (InputMismatchException var3) {
                    System.out.println("Please enter a valid choice");
                    this.sc.nextLine();
                }
            }
        }
    }

    public String useradd() {
        System.out.println("--- To-Do Application ---");
        System.out.print("Input username: ");

        String username;
        boolean check;
        do {
            username = this.sc.nextLine();
            check = true;

            for(int i = 0; i < username.length(); ++i) {
                if (username.charAt(i) == '-') {
                    System.out.println("Please enter a valid username");
                    check = false;
                }
            }
        } while(!check);

        System.out.print("Input password: ");

        String password;

        do {
            password = this.sc.nextLine();
            check = true;

            for(int i = 0; i < password.length(); ++i) {
                if (password.charAt(i) == '-') {
                    System.out.println("Please enter a valid password");
                    check = false;
                }
            }
        } while(!check);

        return username + "-" + password;
    }

    public User getUserLogin() {
        System.out.print("Enter username: ");
        String username = this.sc.nextLine();
        System.out.print("Enter password: ");
        String password = this.sc.nextLine();
        return new User(username, password);
    }

    public Task getTask(String username) {
        System.out.println("--- To-Do Application ---");
        System.out.println("Enter task ID");
        int taskID = this.sc.nextInt();
        this.sc.nextLine();
        System.out.println("Enter task Description");
        String taskTitle = this.sc.nextLine();
        new SimpleDateFormat("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = today.format(formatter);
        return new Task(taskID, taskTitle, "Pending", formattedString, username);
    }

    public void displayAll(List<Task> tasks, String username) {
        System.out.println("--- Task list ---");
        Iterator var3 = tasks.iterator();

        while(var3.hasNext()) {
            Task task = (Task)var3.next();
            if (task.getUsername().equals(username)) {
                System.out.println(task.toString());
            }
        }

    }

    public int getTaskID() {
        System.out.print("Input task id: ");
        int taskID = this.sc.nextInt();
        this.sc.nextLine();
        return taskID;
    }
}
