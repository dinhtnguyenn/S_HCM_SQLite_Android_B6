package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.model.Student;
import com.example.myapplication.others.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rclStudentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rclStudentList = findViewById(R.id.rclStudentList);

        //data mẫu
        //        for (int i = 1; i <= 50; i++) {
//            alStudents.add(new Student(i, "Student " + i, "Address " + i, "098xxx"));
//        }

        DBManager dbManager = new DBManager(this);
//        for (int i = 1; i <= 50; i++) {
//            dbManager.insertStudent(new Student(i, "Student " + i, "Address " + i, "098xxx"));
//        }
        ArrayList<Student> alStudents = new ArrayList<>(dbManager.getAllStudent());

        //thiết lập để cuộn mượt hơn
        rclStudentList.setHasFixedSize(true);

        studentAdapter = new StudentAdapter(alStudents,dbManager, MainActivity.this);

        //==================LINEAR==========================================
        // sử dụng LayoutManager để quy định kiểu hiển thị cho list (ngang/dọc)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Thiết lập phần tử hiển thị mặc định nếu muốn
        linearLayoutManager.scrollToPosition(10);

        rclStudentList.setLayoutManager(linearLayoutManager);
        rclStudentList.setAdapter(studentAdapter);


//=========================GRID==========================================
        //2: Số cột nếu thiết lập lưới đứng, số dòng nếu lưới ngang
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//
//        //GridLayoutManager.VERTICAL: lưới dọc | GridLayoutManager.HORIZONTAL: lưới ngang
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        rclStudentList.setLayoutManager(gridLayoutManager);
//        rclStudentList.setAdapter(studentAdapter);

//==================STAGGER GRID==========================================
//        StaggeredGridLayoutManager gridLayoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        rclStudentList.setLayoutManager(gridLayoutManager);
//        rclStudentList.setAdapter(studentAdapter);

    }
}