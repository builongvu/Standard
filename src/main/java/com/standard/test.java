package com.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.standard.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.*;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date utilDate = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("utilDate: "+utilDate);
        System.out.println("localDateTime: "+localDateTime);
        System.out.println("localTime: "+localTime);
        System.out.println("localDate: "+localDate);
        System.out.println("instant: "+instant);
        System.out.println("zonedDateTime: "+zonedDateTime);
    }
}

@Getter
@AllArgsConstructor
class Student implements Serializable {
    private int id;
    private String name;
    private int age;
}
