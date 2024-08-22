package org.example.classesorderdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(1)
class UserServiceTest {

    @BeforeAll
    static void setup() {
        System.out.println("UserServiceTest :: setup()");
    }

    @Test
    void test() {

    }

}
