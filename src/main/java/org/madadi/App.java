package org.madadi;

import org.madadi.config.ComponentScanConfig;
import org.madadi.models.Student;
import org.madadi.service.StudentManagement;
import org.madadi.util.UserInputService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static StudentManagement management;
    private static UserInputService inputService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        management = context.getBean(StudentManagement.class);
        inputService = context.getBean(UserInputService.class);

        boolean isExited = false;

        while (!isExited) {

            System.out.println("================================================");
            System.out.println("1 >> Add student");
            System.out.println("2 >> Find student");
            System.out.println("3 >> Edit student");
            System.out.println("4 >> Delete student");
            System.out.println("5 >> Find all students");
            System.out.println("0 >> Exit");

            int n = inputService.getInt();

            switch (n) {
                case 1:
                    createNewStudent(management);
                    break;
                case 2:
                    findStudent(management, inputService);
                    break;
                case 3:
                    editStudent(management, inputService);
                    break;
                case 4:
                    deleteStudent(management, inputService);
                    break;
                case 5:
                    findAllStudent(management);
                    break;
                case 0:
                    isExited = true;
                    break;
            }
        }
        context.close();
    }

    private static void findAllStudent(StudentManagement management) {
        System.out.println("***************All Info***************");
        management.findAll().stream().map(Student::toString).forEach(System.out::println);
    }

    private static void deleteStudent(StudentManagement management, UserInputService inputService) {
        System.out.println("Enter Id for delete:");
        boolean remove = management.remove(inputService.getInt());
        if (remove) {
            System.out.println("Student Removed");
        } else {
            System.out.println("Student with this id was not found!!");
        }
    }

    private static void editStudent(StudentManagement management, UserInputService inputService) {
        System.out.println("Enter Id for edit:");
        try{
            Student foundStudentToEdit = management.find(inputService.getInt());
            System.out.println("Enter new name:");
            foundStudentToEdit.setName(inputService.getString());
            management.edit(foundStudentToEdit);
        } catch (RuntimeException ex){
            System.out.println("Student with this id was not found!!");
        }
    }

    private static void findStudent(StudentManagement management, UserInputService inputService) {
        System.out.println("Enter Id for find:");
        try{
            Student foundStudent = management.find(inputService.getInt());
            System.out.println("***************Student Info***************");
            System.out.println(foundStudent);
        } catch (RuntimeException ex){
            System.out.println("Student with this id was not found!!");
        }
    }

    private static void createNewStudent(StudentManagement management) {
        Student student = management.create();
        Student savedStudent = management.save(student);
        System.out.println("***************Saved Info***************");
        System.out.println(savedStudent);
    }

}


