package com.example.level1;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.level1.dao.StudentDAO;
import com.example.level1.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("lista de alunos");

        configureNewStudentButton();
        configureList();

        dao.save(new Student("pedro", "3323232", "teste@gmail.com"));
        dao.save(new Student("joao", "332553232", "teste2@gmail.com"));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_main_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student selectedItem = adapter.getItem(menuInfo.position);
            dao.remove(selectedItem);
            adapter.remove(selectedItem);
        }
        return super.onContextItemSelected(item);
    }

    private void configureNewStudentButton() {
        FloatingActionButton fab = findViewById(R.id.activity_main_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewStudent();
            }
        });
    }

    private void goToNewStudent() {
        startActivity(new Intent(MainActivity.this, NewStudentActivity.class));
    }

    @Override
    protected void onResume() {

        super.onResume();
        adapter.clear();
        adapter.addAll(dao.getall());

    }

    private void configureList() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
        registerForContextMenu(listaAlunos);
        configureOnClick(listaAlunos);

    }


    private void configureOnClick(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Student selectedItem = (Student) adapterView.getItemAtPosition(pos);
                Intent goToNewStudentActivity = new Intent(MainActivity.this, NewStudentActivity.class);
                goToNewStudentActivity.putExtra("student", selectedItem);
                startActivity(goToNewStudentActivity);

            }
        });
    }
}
