package com.isa;

import com.isa.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IsaApplicationTests {

    @Autowired
    private FeedbackService feedbackService;
    @Test
    void contextLoads() {
    }

    @Test
    public void averageTest() {

    }

}
