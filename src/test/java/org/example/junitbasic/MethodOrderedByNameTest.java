package org.example.junitbasic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MethodOrderedByNameTest {

    @Test
    void testB() {
        System.out.println("testB()");
    }

    @Test
    void testC() {
        System.out.println("testC()");
    }

    @Test
    void testD() {
        System.out.println("testD()");
    }

    @Test
    void testA() {
        System.out.println("testA()");
    }


}
