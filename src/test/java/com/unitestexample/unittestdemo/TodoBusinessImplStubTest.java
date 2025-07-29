package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.service.TodoBusinessImpl;
import com.unitestexample.unittestdemo.service.TodoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoBusinessImplStubTest {

    //to improve dynamic conditions

    @Test
    public void test() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceStub);
        List<String> filteredToDos = todoBusinessImp.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredToDos.size());
    }


}
