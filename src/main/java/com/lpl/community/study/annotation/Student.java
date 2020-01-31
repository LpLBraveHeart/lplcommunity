package com.lpl.community.study.annotation;


@LplStudent("tb_student")
public class Student {
    @LplField(columnName = "id", type = "int", length = 10)
    private int id;
    @LplField(columnName = "sname",type = "varchar",length = 20)
    private String userName;
    @LplField(columnName = "age",type = "int",length = 5)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


