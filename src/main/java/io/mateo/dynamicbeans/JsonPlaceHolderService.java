package io.mateo.dynamicbeans;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonPlaceHolderService {
    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    public JsonPlaceHolderService(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    public List<Todo> getAllTodos() {
        return jsonPlaceHolderApi.getAllTodos();
    }
}
