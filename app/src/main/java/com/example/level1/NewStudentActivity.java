package com.example.level1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.level1.dao.StudentDAO;
import com.example.level1.model.Student;

public class NewStudentActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private Student studentedit;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final StudentDAO dao = new StudentDAO();
        setTitle("Adicione novo aluno");
        setContentView(R.layout.activity_new_student);
        getFields();
        configureSaveButton(dao);

        data = getIntent();
        Log.i("teste", "onCreate: " + data.getExtras());
        if (data.getExtras() != null) {
            studentedit = (Student) data.getSerializableExtra("student");
            nameField.setText(studentedit.getName());
            phoneField.setText(studentedit.getPhone());
            emailField.setText(studentedit.getEmail());
        }

    }

    private void configureSaveButton(StudentDAO dao) {
        Button saveButton = findViewById(R.id.activity_new_student_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("teste", "onClick: " + data.getData());
                if (data.getExtras() != null) {
                    editStudent();
                    dao.edit(studentedit);
                    finish();
                }
                else{
                    Student student = new Student(nameField.getText().toString(), phoneField.getText().toString(), emailField.getText().toString());
                    dao.save(student);
                    finish();
                }
            }
        });
    }

    private void editStudent() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();
        studentedit.setEmail(email);
        studentedit.setPhone(phone);
        studentedit.setName(name);
    }

    private void getFields() {
        nameField = findViewById(R.id.activity_new_student_name);
        phoneField = findViewById(R.id.activity_new_student_phone);
        emailField = findViewById(R.id.activity_new_student_mail);
    }
}