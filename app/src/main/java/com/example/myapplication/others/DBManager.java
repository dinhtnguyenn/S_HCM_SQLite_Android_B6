package com.example.myapplication.others;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManager";
    private static final int VERSION = 1;


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Student (ID INTEGER primary key, NAME VARCHAR(255), PHONE VARCHAR(10), ADDRESS VARCHAR (255))";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Lấy toàn bộ danh sách
    public ArrayList<Student> getAllStudent() {

        ArrayList<Student> students = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int studentID = cursor.getInt(0);
            String studentName = cursor.getString(1);
            String studentAddress = cursor.getString(2);
            String studentPhone = cursor.getString(3);

            students.add(new Student(studentID, studentName, studentAddress, studentPhone));
            cursor.moveToNext();
        }

        cursor.close();

        return students;
    }


    //Lấy thông tin SV bằng ID
    public Student getStudentByID(int ID) {
        Student students = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student where id = ?", new String[]{ID + ""});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int studentID = cursor.getInt(0);
            String studentName = cursor.getString(1);
            String studentAddress = cursor.getString(2);
            String studentPhone = cursor.getString(3);

            students = new Student(studentID, studentName, studentAddress, studentPhone);
        }
        cursor.close();
        return students;
    }

    //cập nhật
    public void updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE student SET name=?, address = ?, phone = ? where id = ?",
                new String[]{student.getFullName(), student.getAddress(), student.getPhone(), student.getStudentID() + ""});
    }

    //thêm mới
    public void insertStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO student (id, name, phone, address ) VALUES (?,?,?,?)",
                new String[]{student.getStudentID() + "", student.getFullName(), student.getAddress(), student.getPhone()});
    }

    //Xoá SV bằng ID
    public void deleteStudentByID(int studentID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM student where id = ?", new String[]{String.valueOf(studentID)});
    }
}
