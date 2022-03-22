package com.example.level1;

import android.app.Application;

import com.example.level1.dao.StudentDAO;
import com.example.level1.model.Student;

public class level1Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StudentDAO dao = new StudentDAO();
        dao.save(new Student("pedro", "3323232", "teste@gmail.com"));
        dao.save(new Student("joao", "332553232", "teste2@gmail.com"));
    }
}
