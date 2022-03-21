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
        Student studentFound = null;

        for (Student s:
             students) {
            if (s.getId() == student.getId()) {

                studentFound = s;
            }
            if (studentFound != null){
                int pos = students.indexOf(studentFound);
                students.set(pos, student);

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
