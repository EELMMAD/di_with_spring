package org.madadi.models;

public class Student {
    private int id;
    private String name;

    public Student(){
        setId(-1);
    }

    public Student(int id,String name){
        setId(id);
        setName(name);
    }

    public Student(String name){
        setId(-1);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
