package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Student;
import com.example.myapplication.others.DBManager;
import com.example.myapplication.others.ItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> alStudent;
    private Context context;
    private DBManager dbManager;

    public StudentAdapter(ArrayList<Student> alStudent, DBManager dbManager, Context context) {
        this.alStudent = alStudent;
        this.dbManager = dbManager;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtStudentID.setText(String.valueOf(alStudent.get(position).getStudentID()));
        holder.txtStudentName.setText(alStudent.get(position).getFullName());
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = dbManager.getStudentByID(alStudent.get(holder.getAdapterPosition()).getStudentID());
                String name = student.getFullName() + student.getAddress();
                Toast.makeText(context, "Đang chọn " + name, Toast.LENGTH_SHORT).show();
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Student student = dbManager.getStudentByID(alStudent.get(holder.getAdapterPosition()).getStudentID());
                String name = student.getFullName() + student.getAddress();
                Toast.makeText(context, "Đang chọn " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alStudent.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtStudentName, txtStudentID;
        public Button btnDetails;
        public ItemClickListener itemClickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStudentName = itemView.findViewById(R.id.txtStudentName);
            txtStudentID = itemView.findViewById(R.id.txtStudentID);
            btnDetails = itemView.findViewById(R.id.btnDetails);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getAdapterPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
}
