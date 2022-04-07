package com.example.level1.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.level1.model.Student;

@Database(entities = {Student.class}, version = 2, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String DB_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract StudentDAO getRoomDAO();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(new Migration(1, 2) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL("ALTER TABLE student ADD COLUMN surname TEXT");
                        }
                    })
                    .build();
        }
        return instance;
    }
}
