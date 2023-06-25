package com.example.insecuredeserialization;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

@SpringBootApplication
public class InsecureDeserializationApplication
   //     implements CommandLineRunner
{

    public static void main(String[] args) {
        SpringApplication.run(InsecureDeserializationApplication.class, args);
    }
    protected RedisTemplate<Serializable, Serializable> redisTemplate;
   // @Override
//    public void run(String... args) throws Exception {
//        System.out.println("kkkkkk");
//        String path="C:\\Users\\user1\\Desktop\\jars\\ss.txt";
//        try (FileInputStream fis = new FileInputStream(path)) {
//            byte[] readBytes = fis.readAllBytes();
//          //  int bytesRead = fis.read(readBytes);
//            System.out.println("Byte array read from file: " + Arrays.toString(readBytes));
//            System.out.println("deserializing payload");
//            final Object objAfter = Deserializer.deserialize(readBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
