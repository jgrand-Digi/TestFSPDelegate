package com.dgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dgp.nio.DelegatingFileSystemProvider;


@SpringBootApplication
public class Application  {

    public static void main(String[] args) {
        //Sets the default file system property
        System.getProperties().setProperty("java.nio.file.spi.DefaultFileSystemProvider", DelegatingFileSystemProvider.class.getName());
        
        SpringApplication.run(Application.class, args);
    }
}
