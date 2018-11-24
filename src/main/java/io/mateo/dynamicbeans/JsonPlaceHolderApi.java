package io.mateo.dynamicbeans;

import feign.RequestLine;

import java.util.List;

public interface JsonPlaceHolderApi {
    @RequestLine("GET /")
    List<Todo> getAllTodos();
}
