package com.example.insecuredeserialization;


import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/a")
@AllArgsConstructor
public class Controller {
    protected RedisTemplate<Serializable, Serializable> redisTemplate;


    @PostMapping("/b")
    public String test(@RequestBody testDTO p) throws IOException, ClassNotFoundException {

        System.out.println("kkkkkk");
        String path="C:\\Users\\user1\\Desktop\\jars\\ss.txt";

        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] readBytes = new byte[(int) new File(path).length()];
            int bytesRead = fis.read(readBytes);
            System.out.println("Byte array read from file: " + Arrays.toString(readBytes));
            System.out.println("deserializing payload");
            final Object objAfter = Deserializer.deserialize(readBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";
    }
}
