package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.model.Student;
import com.example.myapplication.others.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnChange;
    private ArrayList<Student> alStudents;

    private RecyclerView rclStudentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rclStudentList = findViewById(R.id.rclStudentList);
        btnChange = findViewById(R.id.btnChange);

        DBManager dbManager = new DBManager(this);
//        for (int i = 1; i <= 50; i++) {
//            dbManager.insertStudent(new Student(i, "Student " + i, "Address " + i, "098xxx"));
//        }
        alStudents = new ArrayList<>(dbManager.getAllStudent());

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

        btnChange.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                //thay đổi thông tin SV 2
//                Student student = new Student(2, "Tên mới 3",
//                                            "Address 2", "123xxxx");
//                dbManager.updateStudent(student);

                //thêm mới 1 SV
//                Student student = new Student(100, "SV thêm",
//                        "Address 2", "123xxxx");
//                dbManager.insertStudent(student);

                //Xóa 1 SV
                dbManager.deleteStudentByID(100);

                alStudents.clear();
                alStudents.addAll(dbManager.getAllStudent());

                studentAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Đã thay đổi", Toast.LENGTH_SHORT).show();
            }
        });

    }
}