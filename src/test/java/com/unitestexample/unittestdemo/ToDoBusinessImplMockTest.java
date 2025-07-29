package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.service.TodoBusinessImpl;
import com.unitestexample.unittestdemo.service.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToDoBusinessImplMockTest {

    @Test
    public void testUsingMock() {
        TodoService todoServiceMock =  mock(TodoService.class);
        List<String> values = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(values);
        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredToDos = todoBusinessImp.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredToDos.size());
    }

    @Test
    public void testWithEmptyList() {
        TodoService todoServiceMock =  mock(TodoService.class);
        List<String> values = List.of();
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(values);
        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredToDos = todoBusinessImp.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, filteredToDos.size());
    }
}
