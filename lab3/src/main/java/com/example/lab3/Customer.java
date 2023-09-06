package com.example.lab3;
public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private  int age;
    public Customer(){
        this("", null, false, 0);
    }
    public Customer(String ID, String n, boolean s, int a){
        this.ID = ID;
        this.name = n;
        this.sex = s;
        this.setAge(a);
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;ฆ
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age < 0){
            this.age = 0;
        }else{
            this.age = age;
        }
    }
}