package com.example.level1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.level1.R;
import com.example.level1.database.AgendaDatabase;
import com.example.level1.database.StudentDAO;
import com.example.level1.model.Student;

public class NewStudentActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText surnameField;
    private EditText phoneField;
    private EditText emailField;
    private Student studentedit;
    private Intent data;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = AgendaDatabase.getInstance(this).getRoomDAO();
        setTitle("Adicione novo aluno");
        setContentView(R.layout.activity_new_student);
        getFields();

        data = getIntent();
        Log.i("teste", "onCreate: " + data.getExtras());
        if (data.hasExtra("student")) {
            studentedit = (Student) data.getSerializableExtra("student");
            nameField.setText(studentedit.getName());
            surnameField.setText(studentedit.getSurname());
            phoneField.setText(studentedit.getPhone());
            emailField.setText(studentedit.getEmail());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_new_student_menu) {
            saveOrEdit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveOrEdit() {
        if (data.hasExtra("student")) {
            editStudent();
            dao.edit(studentedit);
            finish();
        } else {
            Student student = new Student(nameField.getText().toString(), phoneField.getText().toString(), emailField.getText().toString());
            student.setSurname(surnameField.getText().toString());
            dao.save(student);
            finish();
        }
    }

    private void editStudent() {
        String name = nameField.getText().toString();
        String surname = surnameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();
        studentedit.setEmail(email);
        studentedit.setSurname(surname);
        studentedit.setPhone(phone);
        studentedit.setName(name);
    }

    private void getFields() {
        nameField = findViewById(R.id.activity_new_student_name);
        surnameField = findViewById(R.id.activity_new_student_surname);
        phoneField = findViewById(R.id.activity_new_student_phone);
        emailField = findViewById(R.id.activity_new_student_mail);
    }
}