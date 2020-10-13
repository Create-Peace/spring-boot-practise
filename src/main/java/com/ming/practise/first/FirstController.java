package com.ming.practise.first;

import com.ming.practise.common.validateException.BingingResultChecker;
import com.ming.practise.common.validateException.ValidateException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    @BingingResultChecker
    public Object createInfo(@RequestBody @Validated Person person, BindingResult bindingResult) {
        System.out.println(person);
        return person;
    }
}
