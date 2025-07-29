package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.service.TodoService;

import java.util.Arrays;
import java.util.List;

//Dummpy implementation of TodoService
public class TodoServiceStub implements TodoService {

    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
    }

    public void deleteTodo(String todo) {

    }
}
