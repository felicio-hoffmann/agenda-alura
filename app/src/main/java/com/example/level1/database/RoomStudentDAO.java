package com.example.level1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.level1.model.Student;

import java.util.List;

@Dao
public interface RoomStudentDAO {
    @Insert
    void save(Student student);

    @Query("SELECT * FROM student")
    List<Student> getall();

    @Delete
    void remove(Student student);

    @Update
    void edit(Student student);
}
