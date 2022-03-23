package com.example.level1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.level1.R;
import com.example.level1.dao.StudentDAO;
import com.example.level1.model.Student;
import com.example.level1.ui.MainView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    MainView mainView = new MainView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("lista de alunos");
        configureNewStudentButton();
        configureList();
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
            mainView.confirmationRemove(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configureNewStudentButton() {
        FloatingActionButton fab = findViewById(R.id.activity_main_fab_add);
        fab.setOnClickListener(view -> goToNewStudent());
    }

    private void goToNewStudent() {
        startActivity(new Intent(MainActivity.this, NewStudentActivity.class));
    }

    @Override
    protected void onResume() {

        super.onResume();
        mainView.refresh();

    }

    private void configureList() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        mainView.configureAdapter(listaAlunos);
        registerForContextMenu(listaAlunos);
        configureOnClick(listaAlunos);

    }

    private void configureOnClick(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, pos, id) -> {
            Student selectedItem = (Student) adapterView.getItemAtPosition(pos);
            Intent goToNewStudentActivity = new Intent(MainActivity.this, NewStudentActivity.class);
            goToNewStudentActivity.putExtra("student", selectedItem);
            startActivity(goToNewStudentActivity);

        });
    }
}
