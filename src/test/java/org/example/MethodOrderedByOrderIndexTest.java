package org.example;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderedByOrderIndexTest {

    StringBuilder sb = new StringBuilder();

    @AfterEach
    void afterEach() {
        System.out.println(sb);
    }

    @Order(1)
    @Test
    void testB() {
        System.out.println("testB()");
        sb.append("testB");
    }

    @Order(2)
    @Test
    void testC() {
        System.out.println("testC()");
        sb.append("testC");
    }

    @Order(3)
    @Test
    void testD() {
        System.out.println("testD()");
        sb.append("testD");
    }

    @Order(4)
    @Test
    void testA() {
        System.out.println("testA()");
        sb.append("testA");
    }


}
