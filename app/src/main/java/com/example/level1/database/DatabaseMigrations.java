package com.example.level1.database;

import static com.example.level1.model.PhoneType.HOUSE;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.level1.model.PhoneType;

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

    private static final Migration MIGRATION_3_4 =new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `New_Student` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `surname` TEXT," +
                    " `email` TEXT)");
            database.execSQL("INSERT INTO New_Student (id, name, surname, email)" +
                    "SELECT id, name, surname, email FROM Student");


            database.execSQL("CREATE TABLE IF NOT EXISTS `Phones` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `number` TEXT, `type` TEXT," +
                    " `studentId` INTEGER NOT NULL)");
            database.execSQL("INSERT INTO Phones (number, studentId)" +
                    "SELECT phone, id FROM Student");

            database.execSQL("UPDATE Phones SET type = ?", new PhoneType[] {HOUSE});

            database.execSQL("DROP TABLE Student");
            database.execSQL("ALTER TABLE New_Student RENAME TO Student");

        }
    } ;
    public static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};

}
