package org.madadi.service;

import org.madadi.data_access.StudentDao;
import org.madadi.models.Student;
import org.madadi.util.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentManagementConsoleImpl implements StudentManagement {
    private final UserInputService userInputService;
    private final StudentDao studentDao;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService userInputService,
                                        StudentDao studentDao){
        this.userInputService = userInputService;
        this.studentDao = studentDao;
    }

    private String getStringFromUser(String msg){
        System.out.print(msg);
        return userInputService.getString();
    }

    private int getIntegerFromUser(String msg){
        System.out.print(msg);
        return userInputService.getInt();
    }

    @Override
    public Student create() {
        Student student = new Student(getStringFromUser("Input your name :"));
        return student;
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student find(int id) throws RuntimeException{
        return studentDao.find(id);
    }

    @Override
    public boolean remove(int id) {
        try{
            studentDao.find(id);
            studentDao.delete(id);
            return true;
        }catch (RuntimeException ex){
            return false;
        }
    }

    @Override
    public Student edit(Student student) throws RuntimeException {
        studentDao.find(student.getId());
        studentDao.save(student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
