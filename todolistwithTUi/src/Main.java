

import controller.Controller;
import database.connectdb;
import model.taskList;
import view.view;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        view view = new view();
        connectdb db = new connectdb();
        taskList taskList = new taskList();
        Controller controller = new Controller(view, db, taskList);
        controller.loginpart();
        controller.start();
    }
}
