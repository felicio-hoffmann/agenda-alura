package com.example.level1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.level1.model.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    public abstract RoomStudentDAO getRoomDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
    }
}
