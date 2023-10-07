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
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5);
        list = list.stream().filter(i -> i > 3).map(i -> i + 1).toList();
        list.forEach(System.out::println);
    }
}
