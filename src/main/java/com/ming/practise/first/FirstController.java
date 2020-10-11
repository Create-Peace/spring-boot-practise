package com.ming.practise.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class FirstController {
    @GetMapping()
    public Object firstInfo() {
        System.out.println("12");
        return "fist";
    }
}
