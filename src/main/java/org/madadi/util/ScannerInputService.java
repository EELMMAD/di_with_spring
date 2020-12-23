package org.madadi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerInputService implements UserInputService {

    Scanner scanner;

    @Autowired
    public ScannerInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getString() {
        return scanner.nextLine().trim();
    }

    @Override
    public int getInt() {
        boolean invalid = true;
        int number = 0;
        while (invalid) {
            try{
                number = Integer.parseInt(getString());
                invalid = false;
            }catch (NumberFormatException exception){
                System.out.println("Invalid input, could not be parsed to int");
            }
        }
        return number;
    }
}