package com.ass.firebasedemo;

public class Student {
    public String sid;
    public String sname;
    public String sage;
    public String course;

    public Student()
    {

    }

    public Student(String sid,String sname,String age,String course)
    {
        this.sid=sid;
        this.sname=sname;
        this.sage=age;
        this.course=course;

    }

    public String getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getSage() {
        return sage;
    }

    public String getCourse() {
        return course;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
