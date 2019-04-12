package com.design.learn.oom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestTomcat {

    @RequestMapping("/testTomcat")
    public void testTomcat() {
        ArrayList list = new ArrayList();
        while (true) {
            list.add(new TestTomcat());

        }
    }
}
