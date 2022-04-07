package com.example.level1.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.level1.database.AgendaDatabase;
import com.example.level1.database.StudentDAO;
import com.example.level1.model.Student;
import com.example.level1.ui.adapter.StudentAdapter;

public class MainView {

    private final Context context;
    private final StudentAdapter adapter;
    private final StudentDAO dao;

    public MainView(Context context) {
        this.context = context;
        this.adapter = new StudentAdapter(context);
        this.dao = AgendaDatabase.getInstance(context).getRoomDAO();
    }

    public void configureAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }

    public void confirmationRemove(@NonNull MenuItem item) {
        new AlertDialog.Builder(this.context)
                .setTitle("Remover Aluno")
                .setMessage("Deseja Remover o aluno?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student selectedItem = adapter.getItem(menuInfo.position);
                    remove(selectedItem);
                })
                .show();
    }

    public void refresh() {
        adapter.refresh(dao.getall());
    }

    public void remove(Student selectedItem) {
        dao.remove(selectedItem);
        adapter.remove(selectedItem);
        adapter.notifyDataSetChanged();
    }
}
