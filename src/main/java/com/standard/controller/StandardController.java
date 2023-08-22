package com.standard.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/standard")
public class StandardController {

    @GetMapping
    public void test() {
        Abc abc = new Abc();
        abc.testException();
        System.out.println("tiếp");
    }

}

class Abc {
    public void testException() {
        throw new ArithmeticException("lỗi");
    }
}
