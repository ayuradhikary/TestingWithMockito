package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.service.TodoBusinessImpl;
import com.unitestexample.unittestdemo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImp; // automatically injects the todoServiceMock while creating the object of TodoBusinessImpl since it expects the object of TodoService.

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testUsingMock() {
//        TodoService todoServiceMock =  mock(TodoService.class); we do not need to do this now since we mocked the dependency
        List<String> values = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(values);
//        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceMock); we don't need to do this as we have already created the object and injected todoServiceMock using @InjectMocks
        List<String> filteredToDos = todoBusinessImp.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredToDos.size());
    }

    @Test
    public void testWithEmptyList() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> values = List.of();
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(values);
        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredToDos = todoBusinessImp.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, filteredToDos.size());
    }

    @Test
    public void testDeleteWithArgumentCapture() {
//        TodoService todoServiceMock = mock(TodoService.class);
        List<String> values = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(values);

        //deleting not related to Spring
//        TodoBusinessImpl todoBusinessImp = new TodoBusinessImpl(todoServiceMock);
        todoBusinessImp.deleteTodosNotRelatedToSpring("Dummy");

        //ArgumentCapture
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class); since we added capture above using @Capture annotation we do not need to write this line

        //verifying and capturing argument passed to deleteTodosNotRelatedToSpring()
        verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
    }
}
