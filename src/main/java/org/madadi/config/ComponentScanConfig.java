package org.madadi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "org.madadi")
public class ComponentScanConfig {
    Scanner scanner = new Scanner(System.in);
    @Bean
    public Scanner getScanner(){
        return scanner;
    }
}
