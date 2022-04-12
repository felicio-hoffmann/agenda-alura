package com.example.level1.database;

import androidx.room.Dao;
import androidx.room.MapInfo;
import androidx.room.Query;

import com.example.level1.model.Phones;
import com.example.level1.model.Student;

import java.util.Map;

@Dao
public interface PhoneDAO {
    @MapInfo(keyColumn = "id", valueColumn = "number")
    @Query("SELECT Student.id, Phones.number FROM Student JOIN Phones " +
            "ON Student.id = :studentId LIMIT 1")
    Map<Integer, String> getFirstPhone(int studentId);
}
