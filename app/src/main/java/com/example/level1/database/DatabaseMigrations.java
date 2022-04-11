package com.example.level1.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN surname TEXT");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `New_Student` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `surname` TEXT," +
                    " `phone` TEXT, `email` TEXT, `mobilePhone` TEXT)");
            database.execSQL("INSERT INTO New_Student (id, name, surname, phone, email)" +
                    "SELECT id, name, surname, phone, email FROM Student");
            database.execSQL("DROP TABLE Student");
            database.execSQL("ALTER TABLE New_Student RENAME TO Student");
        }
    };

    public static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3};

}
