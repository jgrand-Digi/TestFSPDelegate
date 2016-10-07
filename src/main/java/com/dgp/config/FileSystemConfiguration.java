package com.dgp.config;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dgp.nio.MyFileSystem;

@Configuration
@ConditionalOnProperty(prefix="file_system",name="enabled",havingValue="true")
public class FileSystemConfiguration {
    
    @Bean(name="defaultFileSystem", destroyMethod="")
    @ConfigurationProperties(prefix="file_system.config")
    public FileSystem fileSystem(){
        return FileSystems.getDefault();
    }
    
    
    @Autowired
    private void configure(FileSystem in_fs){
        /*
         * Here should happen some configuration or checks on the file system 
         */
        if(!(in_fs instanceof MyFileSystem)){
            throw new RuntimeException("I did not get the file system I wanted!");
        }
        System.out.println(in_fs.toString());
    }

}
