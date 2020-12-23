package org.madadi.service;

import org.madadi.models.Student;

import java.util.List;

public interface StudentManagement {
    Student create();

    Student save(Student student);

    Student find(int id);

    boolean remove(int id);

    Student edit(Student student);

    List<Student> findAll();
}
