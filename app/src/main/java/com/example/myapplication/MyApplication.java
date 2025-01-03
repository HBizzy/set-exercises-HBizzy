package com.example.myapplication;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UserDAO userDAO = new UserDAO(this);
        userDAO.open();
        // Add a user (username: user, password: user)
        if (!userDAO.checkUser("1", "1")) {
            userDAO.addUser("1", "1","Martin","Clunes","martinclunes@gmail.com","IT","30000.00",30);
        }
        userDAO.close();

        AdminDAO adminDAO = new AdminDAO(this);
        adminDAO.open();
        // Add an admin user (username: admin, password: admin)
        if (!adminDAO.checkAdmin("admin", "admin")) {
            adminDAO.addAdmin("admin", "admin");
        }
        adminDAO.close();
    }
}
