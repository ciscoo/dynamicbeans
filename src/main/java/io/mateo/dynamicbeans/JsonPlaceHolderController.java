package io.mateo.dynamicbeans;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonPlaceHolderController {
    private final JsonPlaceHolderService jsonPlaceHolderService;

    public JsonPlaceHolderController(JsonPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @GetMapping("/")
    public List<Todo> listTodos() {
        return jsonPlaceHolderService.getAllTodos();
    }
}
