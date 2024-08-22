package org.example.classesorderdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
class OrderServiceTest {

    @BeforeAll
    static void setup() {
        System.out.println("OrderServiceTest :: setup()");
    }

    @Test
    void test() {

    }

}
