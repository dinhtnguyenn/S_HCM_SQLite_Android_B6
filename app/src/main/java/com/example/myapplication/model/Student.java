package com.example.myapplication.model;

import java.util.Date;

public class Student {

    private int StudentID;
    private String FullName;
    private String Address;
    private String Phone;

    public Student(int studentID, String fullName, String address, String phone) {
        StudentID = studentID;
        FullName = fullName;
        Address = address;
        Phone = phone;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
