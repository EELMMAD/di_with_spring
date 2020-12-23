package org.madadi.data_access;

import org.madadi.models.Student;
import org.madadi.service.IdSequencer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoListImpl implements StudentDao{

    private final List<Student> students = new ArrayList<>();
    private IdSequencer sequencer;

    @Autowired
    public StudentDaoListImpl(IdSequencer sequencer) {
        this.sequencer = sequencer;
    }
    @Override
    public Student find(int id) {
        Student result = students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
        if(result == null)
            throw new RuntimeException("Could not find student with id " + id);
        return  result;
    }

    @Override
    public Student save(Student student) {
        try {
            Student studentInArray = find(student.getId());
            studentInArray.setName(student.getName());
            return studentInArray;
        }catch (RuntimeException ex) {

            if (student.getId() <= 0)
                student.setId(sequencer.nextId());

            students.add(student);
            return student;
        }
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void delete(int id) {
        students.removeIf(student -> student.getId() == id);
    }
}