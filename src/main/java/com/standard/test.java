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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, -1);

        List<Integer> result = numbers.stream()
                .dropWhile(n -> n < 5) // Giữ lại các phần tử nhỏ hơn 5
                .collect(Collectors.toList());

        System.out.println(result);
    }

    static int add(int a, int b) {
        int c = a + b;
        return c;
    }

    static int minus(int a, int b) {
        int c = a - b;
        return c;
    }

}
