package com.ming.practise.first;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/first")
public class FirstController {
    @GetMapping()
    public Object firstInfo() {
        System.out.println("12");
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("test", "first");

        return objectObjectHashMap;
    }

    @PostMapping()
    public Object createInfo(@RequestBody @Validated Person person) {
        System.out.println(person);
        return person;
    }
}
