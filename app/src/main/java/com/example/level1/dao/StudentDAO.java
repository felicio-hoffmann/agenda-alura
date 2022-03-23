package com.example.level1.dao;

import com.example.level1.model.Student;

import java.util.ArrayList;

public class StudentDAO {
    private static int idCounter = 1;
    private final static ArrayList<Student> students = new ArrayList<>();

    public void save(Student student) {
        student.setId(idCounter);
        students.add(student);
        idCounter++;
    }

    public void edit(Student student){
        for (Student s:
             students) {
            if (s.getId() == student.getId()){
                int pos = students.indexOf(s);
                students.set(pos, student);
                break;
            }
        }
    }

    public ArrayList<Student> getall() {
        return new ArrayList<>(students);
    }

    public void remove(Student clickedStudent) {
        students.remove(clickedStudent);
    }
}
