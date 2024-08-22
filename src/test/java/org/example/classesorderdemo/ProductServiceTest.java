package org.example.classesorderdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
class ProductServiceTest {

    @BeforeAll
    static void setup() {
        System.out.println("ProductServiceTest :: setup()");
    }

    @Test
    void test() {

    }

}
