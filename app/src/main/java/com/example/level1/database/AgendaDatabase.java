package com.example.level1.database;

import static com.example.level1.database.DatabaseMigrations.ALL_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.level1.database.converter.PhoneConverter;
import com.example.level1.model.Phones;
import com.example.level1.model.Student;

@Database(entities = {Student.class, Phones.class}, version = 4, exportSchema = false)
@TypeConverters(PhoneConverter.class)
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String DB_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract StudentDAO getRoomDAO();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(ALL_MIGRATIONS)
                    .build();
        }
        return instance;
    }

    public abstract PhoneDAO getPhoneDAO();
}
