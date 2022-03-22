package com.example.level1.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.level1.R;
import com.example.level1.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.student_list_item, viewGroup, false);
        return createdView;
    }

    public void remove(Student selectedItem) {
        students.remove(selectedItem);
    }

    public void clear() {
        students.clear();
    }

    public void addAll(ArrayList<Student> getall) {
        students.addAll(getall);
    }
}
