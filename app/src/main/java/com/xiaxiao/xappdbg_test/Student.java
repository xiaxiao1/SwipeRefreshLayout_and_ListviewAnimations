package com.xiaxiao.xappdbg_test;

/**
 * Created by Administrator on 2016/8/31.
 */
public class Student {
    int age;
    String name;
    String phone;
    String addr;
    boolean marry;

    public Student(String name, int age, String phone, String addr, boolean marry) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.addr = addr;
        this.marry = marry;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarry() {
        return marry;
    }

    public void setMarry(boolean marry) {
        this.marry = marry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "addr='" + addr + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", marry=" + marry +
                '}';
    }
}
