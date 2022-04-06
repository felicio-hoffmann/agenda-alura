package com.example.level1.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.level1.R;
import com.example.level1.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private final Context context;

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
        View createdView = createView(viewGroup);
        Student selectedStudent = students.get(i);
        setFields(createdView, selectedStudent);
        return createdView;
    }

    private void setFields(View createdView, Student selectedStudent) {
        TextView name = createdView.findViewById(R.id.student_item_name);
        name.setText(selectedStudent.getName());
        TextView phone = createdView.findViewById(R.id.student_item_phone);
        phone.setText(selectedStudent.getPhone());
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.student_list_item, viewGroup, false);
    }

    public void remove(Student selectedItem) {
        students.remove(selectedItem);
        notifyDataSetChanged();
    }

    public void refresh(List<Student> getall){
        students.clear();
        students.addAll(getall);
    }

}
