package com.example.btn7.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btn7.R;
import com.example.btn7.model.Student;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> students = new ArrayList<>();
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentAdapter.ViewHolder holder, int position) {
        holder.txtName.setText("Id: "+students.get(position).getId()+" - "+students.get(position).getNameStudent());
        holder.txtClass.setText(students.get(position).getNameClass());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtClass;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtClass = itemView.findViewById(R.id.txtClass);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
