package com.example.level1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.level1.dao.StudentDAO;
import com.example.level1.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentDAO dao = new StudentDAO();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("lista de alunos");

        configureFab();

        dao.save(new Student("pedro", "3323232", "teste@gmail.com"));
        dao.save(new Student("joao", "332553232", "teste2@gmail.com"));

    }

    private void configureFab() {
        FloatingActionButton fab = findViewById(R.id.activity_main_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMainPage();
            }
        });
    }

    private void backToMainPage() {
        startActivity(new Intent(MainActivity.this, NewStudentActivity.class));
    }

    @Override
    protected void onResume() {

        super.onResume();
        configureList();
    }

    private void configureList() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        ArrayList<Student> students = dao.getall();
        listaAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students));
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Student clickedStudent = students.get(pos);
                Intent goToNewStudentActivity = new Intent(MainActivity.this, NewStudentActivity.class);
                goToNewStudentActivity.putExtra("student", clickedStudent);
                startActivity(goToNewStudentActivity);

            }
        });
    }
}
